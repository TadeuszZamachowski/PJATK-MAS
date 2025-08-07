import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Danie implements Serializable {

    private final String nazwa;
    private final BigDecimal cena;
    private final double gramatura;
    private final Menu menu;
    private List<SkładZamówienia> składZamówienias = new ArrayList<>();
    private static List<Danie> extent = new ArrayList<>();

    private Danie(Menu _menu, String _nazwa, BigDecimal _cena, double _gramatura) {
        nazwa = _nazwa;
        cena = _cena;
        gramatura = _gramatura;
        menu = _menu;

        extent.add(this);
    }

    public static Danie stworzDanie(Menu menu, String nazwa, BigDecimal cena, double gramatura) {
        if (menu == null) {
            System.out.println("Nie ma takiego menu");
        } else {
            Danie danie = new Danie(menu, nazwa, cena, gramatura);

            menu.dodajDanie(danie);
            return danie;
        }
        return null;
    }

    public BigDecimal getCena() {
        return cena;
    }
    public String getNazwa() {
        return nazwa;
    }

    public Menu getMenu() {
        return menu;
    }

    public String toString() {
        return nazwa + " " + cena + " zł";
    }

    public void setSkładZamówienia(SkładZamówienia sZ) {
        if(!składZamówienias.contains(sZ)) {
            składZamówienias.add(sZ);

            sZ.setDanie(this);
        }
    }

    private static void writeExtent(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    private static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (ArrayList<Danie>) stream.readObject();
    }

    public static void save() {
        final String file = "danieExtent.ser";
        try {
            var out = new ObjectOutputStream(new FileOutputStream(file));
            Danie.writeExtent(out);
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        final String file = "danieExtent.ser";
        try {
            var in = new ObjectInputStream(new FileInputStream(file));
            Danie.readExtent(in);
            in.close();
        } catch (IOException | ClassNotFoundException e) {

        }
    }
}
