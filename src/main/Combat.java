package main;

import java.io.*;

public class Combat implements Serializable{

    private Combattant combattant1;
    private Combattant combattant2;
    private Integer expEnJeu;

    public Combat(Combattant combattant1, Combattant combattant2, Integer expEnJeu) throws NegativeExpValueException {
        if (expEnJeu < 0) {
            throw new NegativeExpValueException();
        } else {
            this.combattant1 = combattant1;
            this.combattant2 = combattant2;
            this.expEnJeu = expEnJeu;
        }
    }

    public void lancerCombat() /*throws IOException*/ {
        Combattant victime = this.combattant1;
        Combattant attaquant = this.combattant2;
        do {
            Combattant temp = victime;
            victime = attaquant;
            attaquant = temp;
            System.out.println("#########################################");
            System.out.println(attaquant + " attaque " + victime);
            victime.subirDegat(10);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(1);
            }
        } while (victime.isAlive());
        System.out.println("#########################################");
        System.out.println(victime + " a perdu !");
        attaquant.mettreAJourExp(this.expEnJeu);
        Mmo.sauvegarde("nico",(Personnage)attaquant);
    }
    
    public String toString() {
        return  this.getClass().getTypeName();
                
    }
}
