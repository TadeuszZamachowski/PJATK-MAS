import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DziekujeFrame extends JFrame{
    private JPanel mainPanel;
    private JTextArea textArea1;
    private JButton anulujZamówienieButton;
    private JLabel statusLabael;
    private JButton wyjdźButton;

    public DziekujeFrame(String title, Zamówienie zamówienie) {
        super(title);

        try {
            zamówienie.setStatus(Status.W_Realizacji);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        Zamówienie.save();

        Restauracja r = zamówienie.getSkładZamówienia().get(0).getDanie().getMenu().getRestauracja();
        String tekst = "Dziękuje za złożenie zamówienia " + zamówienie.getOsoba() + " na adres:" + "\n";
        tekst += zamówienie.getAdres() +"\n";
        tekst += "Twoje zamówienie z restauracji " + r.getNazwa() + "\n";
        tekst += "O adresie: " + "\n" + r.getAdres() + "\n" + "  zostało przyjęte do realizacji" + "\n";

        Opinia opinia = zamówienie.getOsoba().znajdźOpinieDoZamówienia(zamówienie);
        if (opinia == null) {
            tekst += "Brak opinii do wystawionego zamówienia.";
        } else {
            tekst += "Twoja opinia: " + "\n" + opinia;
        }
        textArea1.setText(tekst);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);
        pack();

        anulujZamówienieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    zamówienie.getOsoba().deleteOpinia(zamówienie.getOpinia());
                    zamówienie.getOsoba().deleteZamówienie(zamówienie);
                    zamówienie.setStatus(Status.Anulowane);
                    Zamówienie.save();
                    statusLabael.setText("Zamówienie anulowane!");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        wyjdźButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
