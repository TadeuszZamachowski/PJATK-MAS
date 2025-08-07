import java.util.EnumSet;

public class PracownikBiura extends Osoba{

    private String zmiana;

    public PracownikBiura(String imie, String nazwisko, Adres adres, String numerTelefonu, String email, int id, EnumSet<Typ> typ, String zmiana) {
        super(imie, nazwisko, adres, numerTelefonu, email, id, typ);
        this.zmiana = zmiana;
    }
}
