package tableau;

import entity.Etudiant;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class EtudiantTableModel extends AbstractTableModel{
    private List<Etudiant> etudiantData = new ArrayList<Etudiant>();
    private String[] columnNames = {"Numero", "Nom", "Niveau"};
    

    public EtudiantTableModel() {
    }
    
    public EtudiantTableModel(List<Etudiant> etudiantData){
        this.etudiantData = etudiantData;
    }
    

    
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    
    @Override
    public int getRowCount() {
        return etudiantData.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Object etudinatAttribute = null;
        Etudiant etudiantObject = etudiantData.get(row);
        switch(column) {
            case 0: etudinatAttribute = etudiantObject.getNumEt(); break;
            case 1: etudinatAttribute = etudiantObject.getNomEt(); break;
            case 2: etudinatAttribute = etudiantObject.getNiveauEt(); break;
            default: break;
        }
        return etudinatAttribute;
    }
    
    
    
     public void addEtudiant(Etudiant etudiant) {
        etudiantData.add(etudiant);
        fireTableDataChanged();
    }
    
}
