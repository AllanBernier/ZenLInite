package Controleur;

import Modele.IPlayable;
import Modele.Mode;
import Modele.PlayerIA;
import Modele.Zen;
import file.Log;
/**
 * Cette classe contient les parties du controleur communes à la partie graphique et console
 * @author Allan Bernier
 *
 */
public class Master{

	protected IPlayable aWhite;
	protected IPlayable aBlack;
	protected IPlayable aCurrent;
	protected PlayerIA aIa;
	public Mode aMode;
	protected Zen aZen;		// instance de Zen contenant toutes les information utile sur lui 
	protected int[][] aPlateau; 	// Avancé du plateau de jeu
	protected int aNoTurn; 		// Numero du tour de jeu
	
	/**
	 * Constructeur.
	 */
	public Master()
	{
		this.aIa = new PlayerIA(0, null);
	}

	/**
	 * Effectue le tour de l'IA, selectionne le pion a deplacer puis le deplace, 
	 * L'equivalant pour le joueur humain est la methode mCaseClicked(int pLine ,int pColumn)
	 */
	public void mIaTurn()
	{
		this.aCurrent.mPlay(this.aZen , this.aPlateau, this.aNoTurn );
		int[] pion = this.aCurrent.getPion();
		int[] where = this.aCurrent.getWhere();
		
		this.aPlateau[ where[0] ][ where[1] ] = this.aPlateau[  pion[0] ][  pion[1] ];
		this.aPlateau[  pion[0] ][  pion[1] ] = 0;		
		
		mNewTurn();
		mDrawGame();

	}

	/**
	 * Initialise un nouveau tour,
	 * Augmente le numero de tour de 1,
	 * Change le joueur courant avec le joueur qui dois jouer.
	 */
	public void mNewTurn()
	{
		// Le tour de jeu augente de 1
		this.aNoTurn ++;
		this.aBlack.setNoTurn(this.aNoTurn);
		this.aWhite.setNoTurn(this.aNoTurn);
		
		// On change de joueur courant, puis, on redemande les info que le joueur a changer
		if ( this.aCurrent == this.aBlack)
		{
			this.aCurrent = this.aWhite;
			Log.mLog("Au tour du joueur Blanc");
		}
		else if (this.aCurrent == this.aWhite)
		{
			this.aCurrent = this.aBlack;
			Log.mLog("Au tour du joueur Noir");
		}
	}
	
	/**
	 * Redefinis dans les classes filles
	 */
	public void mDrawGame()
	{
		Log.mLog("Error : Master -> mDrawGame non definit");
	}
	
	/**
	 * Redefinis dans les classes filles
	 */
	public void mDrawGame(int[][] pMove, int pPion[])
	{
		Log.mLog("Error : Master -> mDrawGame non definit");
	}
	
	/**
	 * Renvoie la couleur du joueur courrant
	 * @return "Blanc" ou "Noir"
	 */
	public String mGetCurrentColor()
	{
		return (this.aCurrent.getColor() == 1 ? "Blanc" : "Noir");
	}
	
}
