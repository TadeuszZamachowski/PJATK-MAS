import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SkładZamówienia implements Serializable {

    private int ilosc;
    private Zamówienie zamówienie;
    private Danie danie;
    private static List<SkładZamówienia> extent = new ArrayList<>();

    public SkładZamówienia(Zamówienie zamówienie, Danie danie, int ilosc) {
        setZamówienie(zamówienie);
        setDanie(danie);

        this.ilosc = ilosc;
        extent.add(this);
    }

    public void setZamówienie(Zamówienie z) {
        if (zamówienie == null) {
            zamówienie = z;

            z.setSkładZamówienia(this);
        }
    }

    public void setDanie(Danie d) {
        if (danie == null) {
            danie = d;

            d.setSkładZamówienia(this);
        }
    }

    public Danie getDanie() {
        return danie;
    }

    public String toString() {
        return danie + " - " + ilosc;
    }

    private static void writeExtent(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    private static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (ArrayList<SkładZamówienia>) stream.readObject();
    }

    public static void save() {
        final String file = "skladExtent.ser";
        try {
            var out = new ObjectOutputStream(new FileOutputStream(file));
            SkładZamówienia.writeExtent(out);
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        final String file = "skladExtent.ser";
        try {
            var in = new ObjectInputStream(new FileInputStream(file));
            SkładZamówienia.readExtent(in);
            in.close();
        } catch (IOException | ClassNotFoundException e) {

        }
    }

}
