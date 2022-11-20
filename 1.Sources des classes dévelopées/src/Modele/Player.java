package Modele;

import file.Log;

public class Player implements IPlayable{

	protected Zen aZen;			// instance de Zen contenant toutes les information utile sur lui 
	protected int[][] aPlateau; 	// Avancé du plateau de jeu
	protected int aNoTurn; 		// Numero du tour de jeu
	
	protected boolean aPionSelected;
	protected boolean aEndTurn;
	protected int[] aPion;
	protected int[] aWhere;
	protected int aColor;

	int aTurnToPlay; 	// Indice du joueur qui dois jouer ( -1 = NOIR ou 1 = BLANC )
	
	/**
	 * Initialise les attribut de chaque joueur
	 * @param pColor La couleur du joueur
	 * @param pZen Le pion Zen du joueur ( le zen peut etre mort à la création du joueur)
	 */
	public Player(int pColor, Zen pZen)
	{
		
		this.aPionSelected = false;
		this.aEndTurn = false;
		this.aColor = pColor;
		this.aZen = pZen;
		this.aPion = new int[2];
		this.aWhere = new int[2];
	}

	/**
	 * Effectue l'action jouer d'un joueur IA,Humain,console ou graphique. 
	 * Cette methode dois obligatoirement etre redefinis dans ses classe filles si elle veux etre utiliser
	 */
	public void mPlay(Zen pZen, int[][] pPlateau, int pNoTurn) 
	{
		// Redéfinis dans certaines de ses classe filles
		Log.mLog("Error : PlayerGraphique mPlay ne peux pas etre acceder ici !");
	} 

	/**
	 * Cette methode dois obligatoirement etre redefinis dans ses classe filles si elle veux etre utiliser
	 * @param pLine, la ligne appuyer par le joueur humain 
	 * @param pColumn, la colonne appuyer par le joueur humain 
	 */
	public void isClicked(int pLine, int pColumn) 
	{
		Log.mLog("Error : PlayerGraphique IsClicked ne peux pas etre acceder ici !");
	}

	/**
	 * Getter de aPionSelected
	 */
	public boolean getSelected() 
	{
		return this.aPionSelected;
	}
	
	/**
	 * Getter de aPion
	 */
	public int[] getPion() 
	{
		return this.aPion;
	}

	/**
	 * Getter de aEndTurn
	 */
	public boolean getEndTurn() 
	{
		return this.aEndTurn;
	}

	/**
	 * Getter de aColor
	 */
	public int getColor() 
	{
		return this.aColor;
	}
	
	/**
	 * Getter de aWhere
	 */
	public int[] getWhere() 
	{
		return this.aWhere;
	}

	
	/**
	 * met aEndTurn et aPionSelected a false
	 */
	public void setNewTurn() 
	{
		this.aEndTurn = false;
		this.aPionSelected = false;		
	}

	/**
	 * Setter de aNoTurn
	 * @param pNoTurn la nouvelle valeur
	 */
	public void setNoTurn(int pNoTurn) 
	{
		this.aNoTurn = pNoTurn;		
	}

	/** Redéfinis dans ses classe filles
	 * 
	 */
	public boolean mEndTurn() 
	{
		return false;
	}
	/**
	 * Getter de aPlateau
	 */
	public int[][] getPlateau() 
	{
		return this.aPlateau;
	}

	/**
	 * Getter de aZen
	 */
	public Zen getZen()
	{
		return this.aZen;
	}

	/** Redéfinis dans ses classe filles
	 * 
	 */
	public boolean getLeaveGame() {
		
		Log.mLog("Error : PlayerGraphique getLeaveGame ne peux pas etre acceder ici !" + this.getClass());
		return false;
	}
		
}
