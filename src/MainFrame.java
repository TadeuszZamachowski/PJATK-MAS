import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainFrame extends JFrame{
    private JPanel mainPanel;
    private JScrollPane restauracje;
    private JList restauracjeList;
    private JButton zamknijBtn;
    private JButton kontynBtn;

    public MainFrame(String title, Osoba osoba) {
        super(title);
//        JLabel jLabel = new JLabel();
//        jLabel.setIcon(new ImageIcon("1.jpg"));
//        this.add(jLabel);

        DefaultListModel<Restauracja> listModel = getRestaurants();

        restauracjeList.setModel(listModel);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);
        pack();
        zamknijBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        kontynBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = restauracjeList.getSelectedIndex();
                Restauracja restauracja = (Restauracja) listModel.getElementAt(selectedIndex);

                dispose();
                JFrame jFrame = new MenuFrame("Menu", restauracja, osoba);
                jFrame.setVisible(true);
            }
        });
    }

    public static DefaultListModel<Restauracja> getRestaurants() {
        List<Restauracja> restauracje = Restauracja.pokazRestauracje();

        DefaultListModel<Restauracja> listModel = new DefaultListModel<>();
        for (Restauracja r : restauracje) {
            listModel.addElement(r);
        }
        return listModel;
    }
}
