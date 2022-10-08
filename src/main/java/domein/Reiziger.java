package domein;


import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "reiziger")
public class Reiziger {
    @Id
    @Column(name="reiziger_id")
    private int reiziger_id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;

    @JoinColumn(name = "reiziger_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Adres adres;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy= "reiziger")
    private List<OVChipkaart> alleOVChipkaarten = new ArrayList<>();


    public Reiziger(int reiziger_id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.reiziger_id = reiziger_id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;

    }

    public Reiziger() {
    }

    public OVChipkaart createNewOvchipkaart(int kaartnummer, Date datum, int klasse, int saldo) {
        OVChipkaart ov = new OVChipkaart(kaartnummer, datum, klasse, saldo, this);
        alleOVChipkaarten.add(ov);
        return ov;
    }

    public boolean deleteOvChipkaart(int kaart_nummer) {
        for (OVChipkaart ov : alleOVChipkaarten) {
            if (ov.getKaart_nummer() == kaart_nummer) {
                alleOVChipkaarten.remove(ov);
                return true;
            }
        }
        return false;

    }

    public OVChipkaart findByKaartnummer(int kaart_nummer) {
        for (OVChipkaart ov : alleOVChipkaarten) {
            if (ov.getKaart_nummer() == kaart_nummer) {
                return ov;
            }
        }
        return null;
    }

    public Adres createNewAdres(String postcode, String huisnummer, String straat, String woonplaats) {
        Adres adres = new Adres(this, this.getId(), postcode, huisnummer, straat, woonplaats);
        this.adres = adres;
        return adres;
    }

    public void deleteAdres() {
        if (adres != null) {
            this.adres = null;
        }
    }

    public void addOvchipkaart(OVChipkaart ovChipkaart) {
        alleOVChipkaarten.add(ovChipkaart);
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public int getId() {
        return reiziger_id;
    }

    public void setId(int reiziger_id) {
        this.reiziger_id = reiziger_id;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public List<OVChipkaart> getAlleOVChipkaarten() {
        return alleOVChipkaarten;
    }

    public void setAlleOVChipkaarten(ArrayList<OVChipkaart> alleOVChipkaarten) {
        this.alleOVChipkaarten = alleOVChipkaarten;
    }

    @Override
    public String toString() {
        return "Reiziger{" +
                "id=" + reiziger_id +
                ", voorletters='" + voorletters + '\'' +
                ", tussenvoegsel='" + tussenvoegsel + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", geboortedatum=" + geboortedatum +
                ", adres=" + adres +
                ", alleOVChipkaarten=" + alleOVChipkaarten +
                '}';
    }
}
