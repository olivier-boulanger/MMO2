package main;

import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Mmo  implements Serializable{

    private HashSet<Joueur> joueurs;
    private ArrayList<Monstre> monstres;
    private Carte carte;

    private static Scanner sc = new Scanner(System.in);

    public Mmo() {
        this.joueurs = new HashSet<Joueur>();
        this.monstres = new ArrayList<Monstre>();
        this.carte = new Carte();
    }

    private boolean ajouterJoueur(Joueur j) {
        if (this.joueurs.add(j)) {
            System.out.println("Le joueur " + j + " a été ajouté");
            return true;
        }
        System.out.println("Le joueur " + j + " n'a pas été ajouté (le pseudo existe déja).");
        return false;
    }

    static void sauvegarde(String s, Personnage k) {
        try {
        FileOutputStream fos = new FileOutputStream(s + ".ser");
        //ObjectOutputStream oos = new ObjectOutputStream(fos);
        XMLEncoder oos = new XMLEncoder(fos);
        oos.writeObject("sante : " + k.getSante() + "\nniveau : " +  k.getNiveau() + "\nniveauExp : " + k.getNiveauExp());
        oos.close();
        } catch (Exception e) {
            System.out.println("Erreur " + e);
        }
    }
    

    static Object relecture(String s) {
        try {
            FileInputStream f = new FileInputStream("attaquant.ser");
            ObjectInputStream oos = new ObjectInputStream(f);
            Object o = oos.readObject();
            oos.close();
            return o;
        } catch (Exception e) {
            System.out.println("Erreur " + e);
            return null;
        }
    }

    private void creerJoueur() throws IOException {
        String choixPseudo;
        String choixPersonnage = null;
        Personnage personnage = null;
        Joueur j;
        do {
            System.out.println("Veuillez entrer votre pseudo :");
            choixPseudo = sc.next();
            choixPersonnage = null;
            while (choixPersonnage == null) {
                System.out.println("Quelle personnage voulez-vous jouer ?\n\t1 - Archer\n\t2 - Soldat\n\t3 - Sorcier");
                choixPersonnage = sc.next();
                switch (choixPersonnage) {
                    case "1":
                        personnage = new Archer();
                        break;
                    case "2":
                        personnage = new Soldat();
                        break;
                    case "3":
                        personnage = new Sorcier();
                        break;
                    default:
                        choixPersonnage = null;
                        break;
                }
            }
            j = new Joueur(choixPseudo, personnage);
            sauvegarde(j.getPseudo(),j.getPersonnage());
        } while (!ajouterJoueur(j));
    }

    private void creerMonstre() {
        this.monstres.add(new Troll());
    }

    public void jouer() throws IOException {
        System.out.println("#########################\n");
        System.out.println("LE JEU COMMENCE !");
        creerJoueur();
        creerJoueur();
        creerMonstre();
        Object[] mesJoueurs = this.joueurs.toArray();
        Object[] mesMonstres = this.monstres.toArray();
        try {
            Combat combat = new Combat((Monstre) mesMonstres[0], ((Joueur) mesJoueurs[0]).getPersonnage(), 1000);
            combat.lancerCombat();
        } catch (Exception e) {
            //combat = new Combat(((Joueur) mesJoueurs[1]).getPersonnage(), ((Joueur) mesJoueurs[0]).getPersonnage(), 2000);
            //combat.lancerCombat();
            // ((Joueur) mesJoueurs[0]).lancerCombat((Joueur) mesJoueurs[1]);
            System.out.println("#########################\n");
            System.out.println("FIN DU JEU !");
        }

    }
        public String toString() {
        return  this.getClass().getTypeName();
                
    }

    public static void main(String[] args) throws IOException {
        Mmo mmo = new Mmo();
        mmo.jouer();
    }
}
