import java.util.Scanner;
import java.io.FileReader;

import iut.algo.Decomposeur;
import iut.algo.Console;

/**
 * Class Demographie
 * 
 * @author Philippe Le Pivert
 */

public class Demographie {

	public static void main(String[] a) {
		Departement[] ensDepartements = new Departement[100]; // @1
		Region[] ensRegions = new Region[18];

		// Question 4.1
		Console.println("Etape 1");
		Demographie.chargerDept(ensDepartements);
		Demographie.afficherDept(ensDepartements);

		// Question 4.2
		Console.println("Etape 2");
		Demographie.chargerRegion(ensRegions);
		Demographie.afficherRegion(ensRegions);

		// Question 4.3
		Console.println("Etape 3");
		Demographie.lierRegionDept(ensRegions, ensDepartements);
		Demographie.afficherRegion(ensRegions);
		Demographie.afficherRegionAvecDept(ensRegions);

	}

	// Question 4.1
	private static void chargerDept(Departement[] tab) {
		Decomposeur dec;
		String enreg;

		String numDept, nomDept;
		int populationDept, superficieDept;

		try {
			Scanner scDept = new Scanner(new FileReader("departement.data.txt"));

			int i = 0;
			while (scDept.hasNextLine()) {
				enreg = scDept.nextLine();
				dec = new Decomposeur(enreg);

				numDept = dec.getString(0);
				nomDept = dec.getString(1);
				populationDept = dec.getInt(2);
				superficieDept = dec.getInt(3);

				tab[i] = new Departement(numDept, nomDept, populationDept, superficieDept);

				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Question 4.2
	// Attention on ne fait pas encore les liens entre une Region et ses
	// departements
	private static void chargerRegion(Region[] tab) {
		Decomposeur dec;
		String enreg;

		int numRegion, nbDept;
		String nomRegion;

		try {
			Scanner scRegion = new Scanner(new FileReader("region.data.txt"));

			int i = 0;
			while (scRegion.hasNextLine()) {
				enreg = scRegion.nextLine();
				dec = new Decomposeur(enreg);

				numRegion = dec.getInt(0);
				nomRegion = dec.getString(1);
				nbDept = dec.getInt(2);

				tab[i] = new Region(numRegion, nomRegion, nbDept);
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Question 4.3
	public static void lierRegionDept(Region[] tabRegion, Departement[] tabDept) {
		Decomposeur dec;
		String enreg;

		int numRegion, nbDept;
		String nomRegion, numDept;

		Departement dept;

		try {
			Scanner scRegion = new Scanner(new FileReader("Region.data.txt"));

			int i = 0;
			while (scRegion.hasNextLine()) {
				enreg = scRegion.nextLine();
				dec = new Decomposeur(enreg);

				numRegion = dec.getInt(0);
				nomRegion = dec.getString(1);
				nbDept = dec.getInt(2);
				tabRegion[i] = new Region(numRegion, nomRegion, nbDept);
				for (int cpt = 0; cpt < dec.getInt(2); cpt++) { // Pour tous les numéros de départements sur la ligne
					{
						Departement dep = Demographie.rechercherDept(tabDept, dec.getString(3 + cpt));
						tabRegion[i].ajouterDepartement(dep);
					}
				}

				i++;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Question 4.1
	private static void afficherDept(Departement[] tab) {
		String chaine = "+----+-----------------------+----------+----------+--------+\n" +
				"|num |nom                    |population|superficie|densite |\n" +
				"+----+-----------------------+----------+----------+--------+\n";

		for (int cpt = 0; cpt < tab.length; cpt++)
			chaine += String.format(" %3S %23S %10d %10d %7.2f", tab[cpt].getNumero(),
					tab[cpt].getNom(),
					tab[cpt].getPopulation(),
					tab[cpt].getSuperficie(), tab[cpt].densite()) + "\n";
		chaine += "+----+-----------------------+----------+----------+--------+\n";

		Console.println(chaine);
	}

	// Question 4.2
	// Attention a la remarque @1
	private static void afficherRegion(Region[] tab) {
		String chaine = "+----+---------------------------+----------+----------+--------+\n" +
				"|num |nom                        |population|superficie|densite |\n" +
				"+----+---------------------------+----------+----------+--------+\n";

		for (int cpt = 0; cpt < tab.length; cpt++)
			chaine += String.format("%3s %26s %10d %10d %5.2f", tab[cpt].getNumero(),
					tab[cpt].getNom(),
					tab[cpt].getPopulation(),
					tab[cpt].getSuperficie(), tab[cpt].getDensite()) + "\n";
		;

		chaine += "+----+---------------------------+----------+----------+--------+\n";

		Console.println(chaine);

	}

	// Question 4.3
	private static void afficherRegionAvecDept(Region[] tab) {
		String chaine = "";
		for (int cpt = 0; cpt < tab.length; cpt++) {
			chaine = tab[cpt].getNumero() + " " + tab[cpt].getNom() +
					"\n";
			for (int cpt2 = 0; cpt2 < tab[cpt].nbDeptTotal(); cpt2++) {
				chaine += "	" + tab[cpt].getDept(cpt2);
			}
			Console.print(chaine);
			chaine = "";
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

/*----*/
/* @1 */
/*-----------------------------------------------------------------*/
/* Afin de vous faciliter la tache j'affecte en dur */
/* le nombre de departement a 100 et */
/* le nombre de region a 18 */
/*                                                                 */
/* Il aurait ete plus judicieux dans les methodes chargerXxxxx */
/* de parcourir une premiere fois les deux fichiers pour connaitre */
/* le nombre precis de departements et de regions. */
/*                                                                 */
/* Attention si la valeur 101 est bonne la valeur 27, elle ne */
/* l'est pas. Elle est en effet trop grande, mais cela ne doit pas */
/* empecher votre programme de fonctionner, a vous d'etre vigilant */
/* quand vous parcourerez tabRegion de tester qu'un element ne */
/* possede pas la valeur null. */
/*                                                                 */
/*-----------------------------------------------------------------*/