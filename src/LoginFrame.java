import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame{
    private JPanel mainPanel;
    private JTextField imieText;
    private JTextField nazwiskoText;
    private JLabel imieLabel;
    private JLabel nazwiskoLabel;
    private JButton zalogujButton;

    public LoginFrame(String title) {
        super(title);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);
        pack();
        zalogujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Osoba.load();
                //zalogowany użytkownik
                Osoba osoba = Osoba.znajdźOsobę(imieText.getText(), nazwiskoText.getText());
                if (osoba == null) {
                    System.out.println("Nie ma takiego użytkownika");
                } else {
                    dispose();

                    JFrame jFrame = new MainFrame("Zamówienie", osoba);
                    jFrame.setVisible(true);
                }
            }
        });
    }
}
