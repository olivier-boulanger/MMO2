package main;

public interface Combattant {
	public void subirDegat(int degats);

	public boolean isAlive();

	default void mettreAJourExp(int exp){
	}
}
