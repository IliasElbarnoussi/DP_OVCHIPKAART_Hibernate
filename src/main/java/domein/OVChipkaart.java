package domein;


import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ov_chipkaart")
public class OVChipkaart {
    @Id
    @Column(name="kaart_nummer")
    public int kaart_nummer;
    public Date geldig_tot;
    public int klasse;
    public int saldo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="reiziger_id", nullable=false)
    public Reiziger reiziger;

    @ManyToMany
    @JoinTable(name = "ov_chipkaart_product",
            joinColumns = { @JoinColumn(name = "kaart_nummer") },
            inverseJoinColumns = { @JoinColumn(name = "product_nummer") })
    public List<Product> alleProducten = new ArrayList<>();





    public OVChipkaart(int kaart_nummer, Date geldig_tot, int klasse, int saldo, Reiziger reiziger) {
        this.kaart_nummer = kaart_nummer;
        this.geldig_tot = geldig_tot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger = reiziger;
    }

    public OVChipkaart() {

    }

    public void addProduct(Product product) {
        alleProducten.add(product);
        product.voegOVChipkaartToe(this);
    }

    public void removeProduct(int index) {
        Product product = alleProducten.get(index);

        product.verwijderOVChipkaart(this);
        alleProducten.remove(product);

    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public int getKaart_nummer() {
        return kaart_nummer;
    }

    public void setKaart_nummer(int kaart_nummer) {
        this.kaart_nummer = kaart_nummer;
    }

    public Date getGeldig_tot() {
        return geldig_tot;
    }

    public void setGeldig_tot(Date geldig_tot) {
        this.geldig_tot = geldig_tot;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public List<Product> getAlleProducten() {
        return alleProducten;
    }

    public void setAlleProducten(List<Product> alleProducten) {
        this.alleProducten = alleProducten;
    }

    @Override
    public String toString() {
        return " Kaartnummer | " + getKaart_nummer() + " | Klasse " + getKlasse() + " | Saldo €" + getSaldo() + " | Geldig tot: " + getGeldig_tot() + " | Producten: " + alleProducten ;
    }
}
