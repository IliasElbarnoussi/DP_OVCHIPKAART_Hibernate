

import domein.Adres;
import domein.OVChipkaart;
import domein.Product;
import domein.Reiziger;
import factory.DAOFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.postgresql.core.ConnectionFactory;

import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Testklasse - deze klasse test alle andere klassen in deze package.
 *
 * System.out.println() is alleen in deze klasse toegestaan (behalve voor exceptions).
 *
 * @author tijmen.muller@hu.nl
 */
public class Main {
    private static DAOFactory df = DAOFactory.newInstance();

    public static void main(String[] args) throws SQLException {
        testReizigerDAOHibernate();
        testAdresDAOHibernate();
        testOVChipkaartHibernate();

        testProductHibernate();
    }



    /**
     * P6. Haal alle (geannoteerde) entiteiten uit de database.
     */
//    private static void testFetchAll() {
//        Session session = ConnectionFactory.;
//        try {
//            Metamodel metamodel = session.getSessionFactory().getMetamodel();
//            for (EntityType<?> entityType : metamodel.getEntities()) {
//                Query query = session.createQuery("from " + entityType.getName());
//
//                System.out.println("[Test] Alle objecten van type " + entityType.getName() + " uit database:");
//                for (Object o : query.list()) {
//                    System.out.println("  " + o);
//                }
//                System.out.println();
//            }

//    }


    public static void testReizigerDAOHibernate() {
        System.out.println("==============testReizigerDAOHibernate==============");
        List<Reiziger> alleReizigers = df.getRdao().findAll();
        for (Reiziger reiziger : alleReizigers) {
            System.out.println(reiziger);
        }

        Reiziger reiziger = new Reiziger("Ilias", "", "Elbarnoussi", Date.valueOf("2002-07-25"));
        df.getRdao().save(reiziger);
        System.out.println(reiziger);
        reiziger.setVoorletters("I.");

        Reiziger zelfdeReiziger = df.getRdao().findById(reiziger.getId());
        System.out.println("zelfde reiziger alleen opgehaald uit database " + zelfdeReiziger);

        System.out.print("[Test] Eerst " + alleReizigers.size() + " reizigers, na ReizigerDAO.save() \n");

        alleReizigers = df.getRdao().findAll();
        System.out.println("Aantal reizigers in database is voor de delete " + alleReizigers.size());
        df.getRdao().delete(reiziger);
        alleReizigers = df.getRdao().findAll();
        System.out.println("Aantal reizigers in database is na de delete " + alleReizigers.size());
        System.out.println();
    }

    public static void testAdresDAOHibernate() {
        System.out.println("==============testAdresDAOHibernate==============");
        List<Adres> alleAdressen = df.getAdao().findAll();

        for (Adres adres : alleAdressen) {
            System.out.println(adres);
        }

        System.out.println();

        Reiziger reiziger = new Reiziger("Iliassss", "", "Elbarnoussi", Date.valueOf("2002-09-02"));
        df.getRdao().save(reiziger);
        reiziger.createNewAdres("3451Dz", "10", "Kamerheerlaan", "Vleuten");

        reiziger.getAdres().setWoonplaats("Utrecht");

        List<Adres> opgezochtAdres = df.getAdao().findByReiziger(reiziger);
        System.out.println(opgezochtAdres);

        reiziger.deleteAdres();
        opgezochtAdres = df.getAdao().findByReiziger(reiziger);
        System.out.println(opgezochtAdres);

        df.getRdao().delete(reiziger);
        System.out.println();


    }

    public static void testOVChipkaartHibernate() {
        System.out.println("==============testOVChipkaartHibernate==============");
        List<OVChipkaart> alleOv = df.getOvdao().findAll();

        for (OVChipkaart ov : alleOv) {
            System.out.println(ov);
        }
        System.out.println();

        Reiziger reiziger = new Reiziger("Ilias", "", "Barnoussi", Date.valueOf("2002-07-25"));
        df.getRdao().save(reiziger);
        reiziger.createNewOvchipkaart(200000, Date.valueOf("2029-02-02"), 1, 20);

        OVChipkaart ovchipkaart = reiziger.getAlleOVChipkaarten().get(0);
        ovchipkaart.setGeldig_tot(Date.valueOf("2030-03-03"));

        List<OVChipkaart> ovchipkaartVanReiziger = df.getOvdao().findByReiziger(reiziger);
        System.out.println(ovchipkaartVanReiziger);

        alleOv = df.getOvdao().findAll();
        System.out.println("Aantal OVChipkaarten voordat een Ovchipkaart wordt verwijderd is " + alleOv.size());
        reiziger.deleteOvChipkaart(200000);
        alleOv = df.getOvdao().findAll();
        System.out.println("Aantal overgebleven Ovchipkaarten zijn nu " + alleOv.size());

        df.getRdao().delete(reiziger);
        System.out.println();
    }

    public static void testProductHibernate() {
        System.out.println("==============testProductHibernate==============");
        OVChipkaart ov = df.getOvdao().findAll().get(0);
        System.out.println(ov);


        System.out.println("Nieuwe product aangemaakt voor Ovchipkaart");
        Product newProduct = ov.createNewProductAndAdd(7, "Hibernate test", "", 100.8);


        List<Product> alleProducten = df.getPdao().findAll();
        for (Product product : alleProducten) {
            System.out.println(product);
        }
        System.out.println();

        System.out.println("Test | update, beschrijving wordt aangepast");
        newProduct.setBeschrijving("Bewerkte beschrijving");


        alleProducten = df.getPdao().findAll();
        for (Product product : alleProducten) {
            System.out.println(product);
        }

        System.out.println();
        System.out.println("Test | aantal producten voordat een verwijderd wordt " + alleProducten.size());
        ov.deleteProduct(7);
        alleProducten = df.getPdao().findAll();
        System.out.println("Aantal ovchipkaarten over" + alleProducten.size());

    }


}