import java.util.ArrayList;

public class Region {

    private int numero;
    private String nom;
    private int nbDeptTotal;
    private int nbDept;
    private ArrayList<Departement> tab;

    public Region(int numero, String nom, int nbDeptTotal) {
        this.numero = numero;
        this.nom = nom;
        this.nbDeptTotal = nbDeptTotal;
        this.nbDept = 0;
        this.tab = new ArrayList<Departement>();
    }

    public int getNumero() {
        return numero;
    }

    public String getNom() {
        return nom;
    }

    public int getNbDept() {
        return nbDept;
    }

    public int nbDeptTotal() {
        return nbDeptTotal;
    }

    public boolean ajouterDepartement(Departement departement) {
        nbDept++;
        return tab.add(departement);
    }

    public Departement getDept(int indice) {
        return tab.get(indice);
    }

    public int getPopulation() {
        int population = 0;
        for (Departement departement : this.tab) {
            population += departement.getPopulation();
        }
        return population;
    }

    public int getSuperficie() {
        int superficie = 0;
        for (Departement departement : this.tab) {
            superficie += departement.getSuperficie();
        }
        return superficie;
    }

    public double getDensite() {
        if (this.getSuperficie() != 0) {
            return this.getPopulation() / this.getSuperficie();
        }
        return 0;

    }

    public String toString() {
        return "# " + numero + " | Nom de la Région " + nom + " | Population : " + this.getPopulation()
                + " | Superficie " + this.getSuperficie() + "Km | Densité" + this.getDensite();
    }

}