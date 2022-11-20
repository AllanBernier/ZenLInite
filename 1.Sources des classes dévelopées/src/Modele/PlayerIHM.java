package Modele;

public class PlayerIHM extends Player 
{

	public int aLineClicked;
	public int aColumnClicked;
	
	public PlayerIHM(int pColor, Zen pZen)
	{
		super(pColor, pZen);
	}

	/**
	 * Initialise les attiribut  this.aLineClicked et this.aColumnClicked correspondant aux valeurs envoyer en parametre. 
	 * @param pLine la ligne cliquer
	 * @param pColumn la colonne cliquer
	 */
	public void isClicked( int pLine, int pColumn)
	{
		this.aLineClicked = pLine;
		this.aColumnClicked = pColumn;
	}
	
	/**
	 * Recoit un ligne et une colonne et remplie les tableau aPion et aWhere en fonction de l'avancement du tour
	 * @param pZen
	 * @param pPlateau
	 * @param aNoTurn
	 * @param aMode
	 * @param pLine
	 * @param pColumn
	 * @return
	 */
	public void mPlay(Zen pZen, int[][] pPlateau, int pNoTurn) {
		this.aPlateau = pPlateau;
		this.aZen = pZen;
		this.aNoTurn = pNoTurn;
		
		if ( !this.aPionSelected ) // Si on a pas selectionner de pion
		{
			// Si le pion cliquer est un de mes pions (ou zen)
			if ( this.aPlateau[this.aLineClicked][this.aColumnClicked] == this.aColor || this.aPlateau[this.aLineClicked][this.aColumnClicked] == this.aZen.getZenColor() )
			{
				// Alors aPionSelected est vrais et le tableau a pion est  remplis 
				this.aPionSelected = true;
				this.aPion[0] = this.aLineClicked;
				this.aPion[1] = this.aColumnClicked;
			}
			
		}
		else
		{
			int[][] move = Move.getMove(this.aPlateau, this.aPion[0], this.aPion[1], this.aColor );

			
			if( this.aPlateau[ this.aPion[0] ][ this.aPion[1] ] == this.aZen.getZenColor() ) // Si le deplacement concerne le Zen
			{
				move = this.aZen.mZenMove(this.aPlateau, move , this.aNoTurn);
			}
			
			int index = mBelongsToTab(move,  this.aLineClicked,  this.aColumnClicked);

			if (index != -1 && ( move[index][2] == 1 || move[index][2] == 3) )
			{
				this.aWhere = move[index];
				this.aEndTurn = true;
			}
			else
			{
				this.aPionSelected = false;
			}
		}
		
		//mToString();
	}
	
	/**
	 * Verifit que la position envoyer en parametre appartient au tableau de deplacement.
	 * @param pMove le tableau de position
	 * @param pLine la ligne
	 * @param pColumn la colonne
	 * @return renvoie l'index au quel est la position dans le tableau, -1 si cette position n'est pas dans le tableau.
	 */
	private int mBelongsToTab(int[][] pMove, int pLine, int pColumn)
	{
		int ret = -1;
		for (int i = 0; i < pMove.length; i++)
		{
			if ( pMove[i][0] == pLine && pMove[i][1] == pColumn )
			{
				ret = i;
			}
		}
		
		return ret;
	}
		
	/**
	 * Print les attribut de la classe dans son etat actuel.
	 */
	public void mToString()
	{
		/*
		System.out.println("==PlayerHumanGraphique==");
		System.out.println("aPionSelected: " + this.aPionSelected );
		System.out.println("aEndTurn: " + this.aEndTurn );
		System.out.println("aPion: " + this.aPion[0] + "  " +  this.aPion[1] );
		System.out.println("aWhere: " + this.aWhere[0] + "  " +  this.aWhere[1] );
		System.out.println("aColor: " + this.aColor );
		System.out.println("========================" );
		*/
	}
	
}






