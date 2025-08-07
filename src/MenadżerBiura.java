import java.util.EnumSet;

public class MenadżerBiura extends Osoba {

    private boolean wyższeWykształcenie;

    public MenadżerBiura(String imie, String nazwisko, Adres adres, String numerTelefonu, String email, int id, EnumSet<Typ> typ, boolean wyższeWykształcenie) {
        super(imie, nazwisko, adres, numerTelefonu, email, id, typ);
        this.wyższeWykształcenie = wyższeWykształcenie;
    }
}
