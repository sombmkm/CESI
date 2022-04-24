public class Departement {

    private String numero;
    private String nom;
    private int population;
    private int superficie;

    public Departement(String numero, String nom, int population, int superficie) {
        this.numero = numero;
        this.nom = nom;
        this.population = population;
        this.superficie = superficie;
    }

    public String getNumero() {
        return numero;
    }

    public String getNom() {
        return nom;
    }

    public int getPopulation() {
        return population;
    }

    public int getSuperficie() {
        return superficie;
    }

    public double densite() {
        return (population / superficie);
    }

    public String toString() {
        return numero + " " + nom + "\n";
    }

}