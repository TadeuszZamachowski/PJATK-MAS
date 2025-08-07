import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Restauracja implements Serializable {

    private String nazwa;
    private Adres adres;
    private String numerTelefonu;
    private String numerKontaBankowego;
    private String email;
    private double kwotaDarmowejDostawy;
    private double maksDystans;
    private BigDecimal cenaDostawy;
    private Menu menu;
    private BufferedImage image;
    private static List<Restauracja> extent = new ArrayList<>();
    public Restauracja(String _nazwa, Adres _adres, String nrTel, String nrKontBank, String _email,
                       double kwotaDD, double _maksDystans, Menu _menu, BigDecimal _cenaDostawy) {
        nazwa = _nazwa;
        adres = _adres;
        numerTelefonu = nrTel;
        numerKontaBankowego = nrKontBank;
        email = _email;
        kwotaDarmowejDostawy = kwotaDD;
        maksDystans = _maksDystans;
        menu = _menu;
        cenaDostawy =  _cenaDostawy;

        extent.add(this);
    }

//    public Restauracja(String _nazwa, Adres _adres, String nrTel, String nrKontBank, String _email,
//                       double kwotaDD, double _maksDystans, Menu _menu, BufferedImage zdjecie) {
//        nazwa = _nazwa;
//        adres = _adres;
//        numerTelefonu = nrTel;
//        numerKontaBankowego = nrKontBank;
//        email = _email;
//        kwotaDarmowejDostawy = kwotaDD;
//        maksDystans = _maksDystans;
//        menu = _menu;
//        image = zdjecie;
//
//        extent.add(this);
//    }

    public BufferedImage getImage() {
        return image;
    }

    public Menu getMenu() {
        return menu;
    }
    public BigDecimal getCenaDostawy() {
        return cenaDostawy;
    }


    public static List<Restauracja> pokazRestauracje() {
        return extent;
    }
    public String getNazwa() {
        return nazwa;
    }

    public Adres getAdres() {
        return adres;
    }

    public String toString() {
        return nazwa;
    }

    private static void writeExtent(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    private static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (ArrayList<Restauracja>) stream.readObject();
    }

    public static void save() {
        final String file = "restauracjaExtent.ser";
        try {
            var out = new ObjectOutputStream(new FileOutputStream(file));
            Restauracja.writeExtent(out);
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        final String file = "restauracjaExtent.ser";
        try {
            var in = new ObjectInputStream(new FileInputStream(file));
            Restauracja.readExtent(in);
            in.close();
        } catch (IOException | ClassNotFoundException e) {

        }
    }
}
