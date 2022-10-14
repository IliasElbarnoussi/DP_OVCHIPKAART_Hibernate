package DAO;

import domein.OVChipkaart;
import domein.Reiziger;

import java.util.ArrayList;
import java.util.List;

public interface OVChipkaartDAO {
    public boolean save(OVChipkaart ovchipkaart);
    public List<OVChipkaart> findByReiziger(Reiziger reiziger);
    public boolean update(OVChipkaart ovchipkaart);
    public boolean delete(OVChipkaart ovchipkaart);

    public List<OVChipkaart> findAll();
}
