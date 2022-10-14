package domein;

import factory.DAOFactory;

import javax.persistence.*;
import java.util.Random;

@Entity
@Table(name = "adres")
public class Adres {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adres_id;

//    @OneToOne(targetEntity=Reiziger.class, cascade=CascadeType.ALL)
    @JoinColumn(name = "reiziger_id")
    @OneToOne()
    private Reiziger reiziger;


    private String postcode;
    private String huisnummer;
    private String straat;
    private String woonplaats;

    @Transient
    DAOFactory df = DAOFactory.newInstance();

    public Adres(Reiziger reiziger, int adres_id, String postcode, String huisnummer, String straat, String woonplaats) {
        this.adres_id = adres_id;
        this.reiziger = reiziger;
        this.postcode = postcode;
        this.huisnummer = huisnummer;
        this.straat = straat;
        this.woonplaats = woonplaats;
    }

    public Adres() {

    }

    public int getAdres_id() {
        return adres_id;
    }

    public void setAdres_id(int adres_id) {
        this.adres_id = adres_id;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
        df.getAdao().update(this);
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
        df.getAdao().update(this);
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
        df.getAdao().update(this);
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
        df.getAdao().update(this);

    }

    @Override
    public String toString() {
        return "Adres{" +
                ", adres_id='" + adres_id + '\'' +
                ", postcode='" + postcode + '\'' +
                ", huisnummer='" + huisnummer + '\'' +
                ", straat='" + straat + '\'' +
                ", woonplaats='" + woonplaats + '\'' +
                '}';
    }

}
