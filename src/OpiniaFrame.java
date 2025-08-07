import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpiniaFrame extends JFrame{
    private JPanel mainPanel;
    private JTextArea opiniaTextArea;
    private JButton btn;
    private JComboBox numerowaOpinia;
    private JLabel ocenaLabel;

    public OpiniaFrame(String title, Zamówienie zamówienie) {
        super(title);

        numerowaOpinia.addItem(Integer.valueOf(1));
        numerowaOpinia.addItem(Integer.valueOf(2));
        numerowaOpinia.addItem(Integer.valueOf(3));
        numerowaOpinia.addItem(Integer.valueOf(4));
        numerowaOpinia.addItem(Integer.valueOf(5));


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);
        pack();
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = numerowaOpinia.getSelectedIndex();
                Opinia opinia = new Opinia(opiniaTextArea.getText(), ++index, zamówienie.getOsoba(), zamówienie);
                zamówienie.setOpinia(opinia);
                dispose();

                JFrame jFrame = new DziekujeFrame("Dziękuje za złożenie zamówienia", zamówienie);
                jFrame.setVisible(true);
            }
        });
    }
}
