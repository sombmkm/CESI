import iut.algo.Console;
import iut.algo.Clavier;

/**
 * TestRegion
 * 
 * @author Philippe Le pivert
 */

public class TestRegion {
    public static void main(String[] a) {
        Departement[] ensDepartements = new Departement[150]; // on prend un nombre suffisament grand
        Region[] ensRegions = new Region[30]; // " " " " " "
        Departement resultat;

        ensDepartements[0] = new Departement("59", "Nord", 2579208, 5743);
        ensDepartements[1] = new Departement("62", "Pas-de-Calais", 1462807, 6671);
        ensDepartements[2] = new Departement("60", "Oise", 805642, 5860);
        ensDepartements[3] = new Departement("80", "Somme", 571211, 6170);
        ensDepartements[4] = new Departement("27", "Eure", 588111, 6040);
        ensDepartements[5] = new Departement("76", "Seine-Maritime", 1251282, 6278);
        ensDepartements[6] = new Departement("14", "Calvados", 685262, 5548);
        ensDepartements[7] = new Departement("50", "Manche", 499531, 5938);
        ensDepartements[8] = new Departement("61", "Orne", 290891, 6103);

        ensRegions[0] = new Region(32, "Hauts-de-France", 4);
        ensRegions[1] = new Region(28, "Normandie", 5);

        ensRegions[0].ajouterDepartement(ensDepartements[0]);
        ensRegions[0].ajouterDepartement(ensDepartements[1]);
        ensRegions[0].ajouterDepartement(ensDepartements[2]);
        ensRegions[0].ajouterDepartement(ensDepartements[3]);

        ensRegions[1].ajouterDepartement(ensDepartements[4]);
        ensRegions[1].ajouterDepartement(ensDepartements[5]);
        ensRegions[1].ajouterDepartement(ensDepartements[6]);
        ensRegions[1].ajouterDepartement(ensDepartements[7]);
        ensRegions[1].ajouterDepartement(ensDepartements[8]);

        Console.println(ensRegions[0]);
        Console.println(ensRegions[1]);

        Console.println("Quel chiffre batard");
        resultat = TestRegion.rechercherDept(ensDepartements, Clavier.lireString());
        if (resultat == null) {
            Console.print("t'es moche");
        } else {
            Console.print(
                    String.format("%23S %3S %10d Habitants %10d Km2 %5.2f hab/Km2", resultat.getNom(),
                            resultat.getNumero(),
                            resultat.getPopulation(),
                            resultat.getSuperficie(), resultat.densite()));
        }
    }

    private static Departement rechercherDept(Departement[] tab, String numero) {
        for (int cpt = 0; cpt < tab.length; cpt++) {
            if (tab[cpt] != null && tab[cpt].getNumero().equals(numero)) {
                return tab[cpt];
            }
        }
        return null;
    }
}