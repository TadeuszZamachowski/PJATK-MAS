import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Menu implements Serializable {

    private Restauracja restauracja;
    private List<Danie> dania = new ArrayList<>();
    public static Set<Danie> wszystkieDania = new HashSet<>();
    private static List<Menu> extent = new ArrayList<>();

    public Menu() {
        extent.add(this);
    }

    public void przypiszRestauracje(Restauracja r) {
        if (restauracja == null) {
            restauracja = r;
        }
    }

    public void dodajDanie(Danie danie) {
        if (!dania.contains(danie)) {
            if (wszystkieDania.contains(danie)) {
                System.out.println("To danie należy już do jakiegoś menu");
                return;
            }
            dania.add(danie);
            wszystkieDania.add(danie);
        }
    }

    public List<Danie> pokażDania() {
        return dania;
    }

    public Restauracja getRestauracja() {
        return restauracja;
    }

    private static void writeExtent(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    private static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (ArrayList<Menu>) stream.readObject();
    }

    public static void save() {
        final String file = "menuExtent.ser";
        try {
            var out = new ObjectOutputStream(new FileOutputStream(file));
            Menu.writeExtent(out);
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        final String file = "menuExtent.ser";
        try {
            var in = new ObjectInputStream(new FileInputStream(file));
            Menu.readExtent(in);
            in.close();
        } catch (IOException | ClassNotFoundException e) {

        }
    }

}
