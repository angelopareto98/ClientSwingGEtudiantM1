
package entity;

public class Etudiant {
    private int numEt;
    private String nomEt;
    private String niveauEt;

    public Etudiant() {
    }

    public int getNumEt() {
        return numEt;
    }

    public void setNumEt(int numEt) {
        this.numEt = numEt;
    }

    public String getNomEt() {
        return nomEt;
    }

    public void setNomEt(String nomEt) {
        this.nomEt = nomEt;
    }

    public String getNiveauEt() {
        return niveauEt;
    }

    public void setNiveauEt(String niveauEt) {
        this.niveauEt = niveauEt;
    }
    
    public String afficher(){
        return "num etuidant "+this.numEt+" nom: "+this.nomEt;
    }
}
