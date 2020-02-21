package main;

import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Personnage implements Combattant {

    private int niveau;
    private int sante;
    private int niveauExp;
    private int prochainNiveau;
    private HashSet<Sort> sorts;

    private static double facteurNiveauSuivant = 1.5;

    private Personnage() {
        this.niveau = 1;
        this.niveauExp = 0;
        this.prochainNiveau = 1000;
        this.sorts = new HashSet<Sort>();
    }

    protected Personnage(int sante) {
        this();
        this.sante = sante;
    }

    public int getNiveau() {
        return niveau;
    }

    public int getSante() {
        return sante;
    }

    public void subirDegat(int degats) {
        this.sante -= degats;
        if (this.sante < 0) {
            this.sante = 0;
        }
        System.out.println(this.getClass().getSimpleName() + " a perdu " + degats + " points de vie !");
    }

    public boolean isAlive() {
        return this.sante > 0;
    }

    public int getNiveauExp() {
        return niveauExp;
    }

    public void mettreAJourExp(int expGagne) {{
            this.niveauExp += expGagne;
            System.out.println(this + " a gagner " + expGagne + " EXP");
            while (this.niveauExp >= this.prochainNiveau) {
                this.niveauExp -= this.prochainNiveau;
                this.niveau++;
                this.prochainNiveau *= Personnage.facteurNiveauSuivant;

                System.out.println(this.getClass().getSimpleName() + " passe du niveau " + (this.niveau - 1) + " au niveau "
                        + this.niveau);
            }
        }

    }

    public int getProchainNiveau() {
        return prochainNiveau;
    }

    public HashSet<Sort> getSorts() {
        return sorts;
    }

    protected boolean ajouterSort(Sort s) {
        return this.sorts.add(s);
    }

    public String toString() {
        return "'" + this.getClass().getSimpleName() + "' de niveau '" + this.getNiveau() + "' et d'EXP '"
                + this.getNiveauExp() + "/" + this.getProchainNiveau() + "'";
    }
}
