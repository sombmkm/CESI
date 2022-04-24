import java.awt.GridLayout;

import javax.swing.*;

import java.awt.event.*;

public class PanelGrille extends JPanel implements ActionListener {
	Controleur ctrl;

	JLabel[][] tabLblCase;
	JButton[] tabButton;

	ImageIcon[] tabImage = new ImageIcon[] {
			new ImageIcon("images/fl_haut.gif"),
			new ImageIcon("images/fl_gauche.gif"),
			new ImageIcon("images/fl_droite.gif"),
			new ImageIcon("images/fl_bas.gif")
	};

	public PanelGrille(Controleur ctrl) {
		String[][] modele;

		int cas;
		int cptBtn = 0;

		this.ctrl = ctrl;
		this.setLayout(new GridLayout(this.ctrl.getNbLigne() + 2, this.ctrl.getNbColonne() + 2, 1, 1));

		modele = this.getModele();

		/*------------------------------*/
		/* Création des composants */
		/*------------------------------*/

		// Création des Labels
		this.tabLblCase = new JLabel[this.ctrl.getNbLigne()][this.ctrl.getNbColonne()];

		for (int lig = 0; lig < tabLblCase.length; lig++)
			for (int col = 0; col < tabLblCase[lig].length; col++) {
				this.tabLblCase[lig][col] = new JLabel(modele[lig][col]);
			}

		// Création des Boutons
		this.tabButton = new JButton[2 * this.ctrl.getNbLigne() + 2 * this.ctrl.getNbColonne()];

		cptBtn = 0;

		for (int lig = 0; lig < modele.length; lig++)
			for (int col = 0; col < modele[lig].length; col++)
				if (modele[lig][col] != null && modele[lig][col].startsWith("fl_")) {
					this.tabButton[cptBtn++] = new JButton();
				}

		/*------------------------------*/
		/* Postionnement des composants */
		/*------------------------------*/
		cptBtn = 0;

		for (int lig = 0; lig < modele.length; lig++)
			for (int col = 0; col < modele[lig].length; col++) {
				if (modele[lig][col] == null)
					cas = 0;
				else if (modele[lig][col].startsWith("fl_"))
					cas = 1;
				else
					cas = 2;

				switch (cas) {
					case 0:
						this.add(new JLabel());
						break;
					case 1:

						if ("fl_haut".equals(modele[lig][col])) {
							this.tabButton[cptBtn] = new JButton(this.tabImage[0]);
						}

						if ("fl_gauche".equals(modele[lig][col])) {
							this.tabButton[cptBtn] = new JButton(this.tabImage[1]);
						}

						if ("fl_droite".equals(modele[lig][col])) {
							this.tabButton[cptBtn] = new JButton(this.tabImage[2]);
						}

						if ("fl_bas".equals(modele[lig][col])) {
							this.tabButton[cptBtn] = new JButton(this.tabImage[3]);
						}
						this.tabButton[cptBtn].setActionCommand(lig + ":" + col);

						this.tabButton[cptBtn].addActionListener(this);
						this.add(this.tabButton[cptBtn]);
						break;
					case 2:
						tabLblCase[lig - 1][col - 1] = new JLabel();
						tabLblCase[lig - 1][col - 1].setOpaque(true);
						tabLblCase[lig - 1][col - 1].setBackground(ctrl.getCouleur(lig - 1, col - 1));
						this.add(tabLblCase[lig - 1][col - 1]);
						break;
				}
			}

		/*------------------------------*/
		/* Activation des composants */
		/*------------------------------*/
		/*
		 * for (int cpt=0; cpt<this.tabButton.length; cpt++)
		 * /* A compléter
		 */

	}

	public void majIHM() {
		for (int lig = 0; lig < this.tabLblCase.length; lig++)
			for (int col = 0; col < this.tabLblCase[lig].length; col++) {
				tabLblCase[lig][col].setBackground(ctrl.getCouleur(lig, col));
			}

	}

	public void actionPerformed(ActionEvent e) {
		/* Appel de la méthode permuter de Controleur avec les bons paramètres */

		/* A compléter */
		String s = e.getActionCommand();
		int lig = Integer.parseInt(s.split(":")[0]);
		int col = Integer.parseInt(s.split(":")[1]);
		System.out.println(s);
		if (lig == 0)
			ctrl.permuter('c', '-', col - 1);
		else if (col == 0)
			ctrl.permuter('l', '-', lig - 1);
		else if (col == this.ctrl.getNbColonne() + 1)
			ctrl.permuter('l', '+', lig - 1);
		else if (lig == this.ctrl.getNbLigne() + 1)
			ctrl.permuter('c', '+', col - 1);

	}

	private String[][] getModele() {
		/*
		 * Voici un exemple de Modele généré pour une grille de 6 x 6
		 * 
		 * { {null, "fl_haut", "fl_haut", "fl_haut", "fl_haut", "fl_haut", "fl_haut",
		 * null },
		 * {"fl_gauche", "val", "val", "val", "val", "val", "val", "fl_droite" },
		 * {"fl_gauche", "val", "val", "val", "val", "val", "val", "fl_droite" },
		 * {"fl_gauche", "val", "val", "val", "val", "val", "val", "fl_droite" },
		 * {"fl_gauche", "val", "val", "val", "val", "val", "val", "fl_droite" },
		 * {"fl_gauche", "val", "val", "val", "val", "val", "val", "fl_droite" },
		 * {"fl_gauche", "val", "val", "val", "val", "val", "val", "fl_droite" },
		 * {null, "fl_bas", "fl_bas", "fl_bas", "fl_bas", "fl_bas", "fl_bas", null } };
		 */

		// Construction du Modele correspondant à la taille de notre Grille.
		String[][] tabModele = new String[ctrl.getNbLigne() + 2][ctrl.getNbColonne() + 2];

		for (int lig = 1; lig < tabModele.length - 1; lig++) {
			tabModele[lig][0] = "fl_gauche";
			tabModele[lig][tabModele[lig].length - 1] = "fl_droite";
		}

		for (int col = 1; col < tabModele[0].length - 1; col++) {
			tabModele[0][col] = "fl_haut";
			tabModele[tabModele.length - 1][col] = "fl_bas";
		}

		for (int lig = 1; lig < tabModele.length - 1; lig++)
			for (int col = 1; col < tabModele[0].length - 1; col++)
				tabModele[lig][col] = "val";

		return tabModele;
	}
}
