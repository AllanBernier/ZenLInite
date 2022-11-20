package Modele;

public class PlayerIA extends Player 
{

	public PlayerIA(int pColor, Zen pZen)
	{
		super(pColor, pZen);
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
	
		int[][] posPions = mGetPosPions(); // Recupere les coordonee de chaque pions
		double[] cloudP = mCloudPoint(posPions); // Recupere le nuage de points grace au tableau de pion	
		
		 //recupere les coodronee du pion le plus loin du nuage et les met dans "this.aPion"
		double dist = 0;
		double pionDist;
		
		for (int i = 0; i < posPions.length; i++)
		{
			pionDist = Utile.mGetDist( posPions[i][0] , posPions[i][1]  , cloudP[0] , cloudP[1] );
			if ( pionDist > dist )
			{
				dist = pionDist;
				this.aPion[0] = posPions[i][0];
				this.aPion[1] = posPions[i][1];
			}
		}
		
		// recupere les coodronee rapprochant le plus du nuage de points dans les deplacements possible de aPion
		dist = 100;
		int[][] move = Move.getMove(this.aPlateau, this.aPion[0], this.aPion[1], this.aColor );
		
		for (int i = 0; i < move.length; i++)
		{
			if (move[i][2] == 1 || move[i][2] == 3) // Si le pion peux bouger
			{

				pionDist = Utile.mGetDist( move[i][0] , move[i][1]  , cloudP[0] , cloudP[1] );
				if ( pionDist < dist )
				{
					dist = pionDist;
					this.aWhere[0] = move[i][0];
					this.aWhere[1] = move[i][1];
				}
			}
		}
		
		
				
	}
	
	/**
	 * Retourne le nuage de points correspondant au millieu entre tout les pions du joueur present sur le plateau
	 * @param pTab Le plateau de jeu
	 * @param pionPos La position de chaque pion en jeu.
	 * @return Double, la valeur du nuage de points
	 */
	private double[] mCloudPoint(int[][] pPionPos)
	{
		int line = 0;
		int column = 0;
		int nbPion = 0;
		

		for (int i = 0; i < pPionPos.length; i ++)
		{
			line += pPionPos[i][0];
			column += pPionPos[i][1];
			nbPion ++;
		}
			
		double[] cloud = new double[2];
		cloud[0] =  ( (double) (line) / (double) (nbPion) );
		cloud[1] = (  (double) (column)  / (double) (nbPion) );
		
		return cloud;
	}
	
	/**
	 * Cherche la position de tout mes pions sur le plateau.
	 * ret.length est compris entre 0 et 12 valeurs en fonction du nombre de pions encore en vie. 
	 * @return tableau de int avec tab[x][0] = line du pion et tab[x][1] = colonne du pion.
	 */
	private int[][] mGetPosPions()
	{
		int[][] pions = new int[12][2];
		int index = 0;
		
		for( int l = 0; l < this.aPlateau.length; l++)
		{
			for( int c = 0; c < this.aPlateau[l].length; c++)
			{
				if (this.aPlateau[l][c] == this.aColor && index < 12)
				{
					pions[index][0] = l;
					pions[index][1] = c;

					index ++;

				}
			}
		}
		
		int[][] ret = new int[index][2];
		for (int i = 0; i < index ; i ++)
		{
			ret[i][0] = pions[i][0];
			ret[i][1] = pions[i][1];
		}
		
		return ret;
	}
	
}		
		
	
	
	







