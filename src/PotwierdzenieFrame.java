import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;


public class PotwierdzenieFrame extends JFrame{
    private JPanel mainPanel;;
    private JButton wystawOpinieButton;
    private JButton potwierdzButton;
    private JButton wróćButton;
    private JTextArea zamówioneDaniaText;
    private JTextArea kwotaZaZamowienieText;
    private JTextArea kosztDostawyText;
    private JTextArea kwotaCalkowitaText;
    private JLabel zamowioneDaniaLabel;
    private JLabel kwotaZaZamowienieLabel;
    private JLabel kosztDostawyLabel;
    private JLabel kwotaCalkowitaLabel;


    public PotwierdzenieFrame(String title, Zamówienie zamówienie) {
        super(title);

        List<SkładZamówienia> zamówioneDania = zamówienie.getSkładZamówienia();
        String daniaString = "";
        for (SkładZamówienia s : zamówioneDania) {
            daniaString += s + "\n";
        }
        BigDecimal kosztZamowienia = zamówienie.getKwotaCalkowita();
        BigDecimal kosztDostawy = zamówienie.getSkładZamówienia().get(0).getDanie().getMenu().getRestauracja().getCenaDostawy();
        kosztDostawyLabel.setText("Koszt dostawy (0 zł jeżeli zamówienie jest równe lub wyższe niż " + kosztDostawy + " zł)");
        if (kosztDostawy.compareTo(kosztZamowienia) <= 0) {
            kosztDostawy = BigDecimal.valueOf(0);
        }
        BigDecimal kosztCalkowity = kosztZamowienia.add(kosztDostawy);

        zamówioneDaniaText.setText(daniaString);
        kwotaZaZamowienieText.setText(kosztZamowienia + " zł");
        kosztDostawyText.setText(kosztDostawy+ " zł");
        kwotaCalkowitaText.setText(kosztCalkowity + " zł");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);
        pack();
        wróćButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                JFrame jFrame = new AdresFrame("Adres dostawy", zamówienie, zamówienie.getOsoba());
                jFrame.setVisible(true);
            }
        });
        potwierdzButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame jFrame = new DziekujeFrame("Dziękuje za zamówienie", zamówienie);
                jFrame.setVisible(true);
            }
        });
        wystawOpinieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame jFrame = new OpiniaFrame("Opinia", zamówienie);
                jFrame.setVisible(true);
            }
        });
    }
}
