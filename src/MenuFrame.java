import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class MenuFrame extends JFrame {
    Map<Danie, Integer> daniaMap = new HashMap<>();
    private static BigDecimal cenaCalkowita;
    private JPanel mainPanel;
    private DefaultListModel<Danie> daniaWKoszyku = new DefaultListModel<>();
    private JList daniaList;
    private JList koszykList;
    private JLabel cenaLabel;
    private JButton kontynuujButton;
    private JButton dodajButton;
    private JButton usuńButton;
    private JButton wsteczButton;
    private JScrollPane koszykScrollPane;
    private JScrollPane daniaScrollPane;

    public MenuFrame(String title, Restauracja restauracja, Osoba osoba) {
        super(title);
        cenaCalkowita = BigDecimal.valueOf(0);

        DefaultListModel<Danie> listModel = getDania(restauracja);

        daniaList.setModel(listModel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);
        pack();


        wsteczButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                JFrame jFrame = new MainFrame("Zamówienie", osoba);
                jFrame.setVisible(true);
            }
        });
        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = daniaList.getSelectedIndex();
                Danie d = (Danie) listModel.getElementAt(selectedIndex);

                daniaWKoszyku.addElement(d);
                koszykList.setModel(daniaWKoszyku);


                cenaCalkowita = cenaCalkowita.add(d.getCena());
                cenaLabel.setText("Cena: "+cenaCalkowita+" zł");
            }
        });
        usuńButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = koszykList.getSelectedIndex();
                Danie d = (Danie) daniaWKoszyku.getElementAt(selectedIndex);

                daniaWKoszyku.remove(selectedIndex);
                koszykList.setModel(daniaWKoszyku);

                cenaCalkowita = cenaCalkowita.subtract(d.getCena());
                cenaLabel.setText("Cena: "+cenaCalkowita+" zł");
            }
        });
        kontynuujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Object> obiekty = Arrays.asList(daniaWKoszyku.toArray());
                List<Danie> dania =(List<Danie>)(Object) obiekty;

                for (Danie d : dania) {
                    Integer j = daniaMap.get(d);
                    daniaMap.put(d, (j == null) ? 1 : j + 1);
                }
                Zamówienie.load();
                int id = Zamówienie.getExtent().size();
                Zamówienie zamówienie = new Zamówienie(id, osoba.getAdres(), cenaCalkowita, osoba);
                try {
                    zamówienie.setStatus(Status.Przyjęte);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

                for (Map.Entry<Danie, Integer> val : daniaMap.entrySet()) {
                    SkładZamówienia skład = new SkładZamówienia(zamówienie, val.getKey(), val.getValue());
                }
                SkładZamówienia.save();

                dispose();
                JFrame jFrame = new AdresFrame("Adres dostawy", zamówienie, osoba);
                jFrame.setVisible(true);
            }
        });
    }

    public static DefaultListModel<Danie> getDania(Restauracja r) {
        List<Danie> dania = r.getMenu().pokażDania();

        DefaultListModel<Danie> listModel = new DefaultListModel<>();
        for (Danie d : dania) {
            listModel.addElement(d);
        }
        return listModel;
    }


}
