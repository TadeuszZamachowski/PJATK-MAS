import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdresFrame extends JFrame {

    private JPanel mainPanel;
    private JTextField ulicaField;
    private JTextField kodPocztowyField;
    private JTextField miastoField;
    private JButton potwierdźButton;
    private JButton anulujButton;
    private JLabel ulicaLabel;
    private JLabel miastoLabel;
    private JLabel kodPocztowyLabel;
    private JLabel pytanieLabel;

    public AdresFrame(String title, Zamówienie zamówienie, Osoba osoba) {
        super(title);

        pytanieLabel.setText("Wprowadzone dane to twój adres przypisany do konta. Usuń dane i wprowadź nowy adres jeżeli uległ zmianie.");
        ulicaField.setText(osoba.getAdres().getUlica() + " " + osoba.getAdres().getNumer());
        miastoField.setText(osoba.getAdres().getMiejscowosc());
        kodPocztowyField.setText(osoba.getAdres().getKodPocztowy());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);
        pack();

        anulujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                JFrame jFrame = new MainFrame("Zamówienie", osoba);
                jFrame.setVisible(true);
            }
        });
        potwierdźButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pattern p = Pattern.compile("\\d+");
                Matcher m = p.matcher(ulicaField.getText());
                String numer = "";
                while(m.find()) {
                    numer = m.group();
                }

                Adres adres = new Adres(ulicaField.getText().replaceAll("\\d*$", ""), miastoField.getText(), numer, kodPocztowyField.getText());
                if (adres.compareTo(osoba.getAdres()) != 0) {
                    osoba.setAdres(adres);
                    Osoba.save();
                    zamówienie.setAdres(adres);
                }
                dispose();
                JFrame jFrame = new PotwierdzenieFrame("Potwierdzenie i opinia", zamówienie);
                jFrame.setVisible(true);
            }
        });
    }

}
