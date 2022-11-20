package Modele;

import file.Log;

public class GameOver {

	/**
	 *  Si le Zen est en vie renvoie les coordonée du zen
	 * sinon cherche la position d'un pion appartenant à la couleur aColor
	 * Si aucun pion appartenant au joueur aColor n'est en vie, return null
	 * @return les coordonée du pion t[0] = ligne t[1] = colonne
	 */
	private static int[] mStarterPion(int pColor, Zen pZen, int[][] pPlateau)
	{
		int ret[] = new int[2];
		
		
		if ( pZen.getIsAlive() ) // Si le Zen est en vie retourne la position du Zen.
		{
			ret[0] = pZen.getLine();
			ret[1] = pZen.getColumn();
			return ret;
		}
		else // Sinon, renvoie le premier pion de la couleur aColor trouver sur le plateau.
		{
			for(int l = 0; l < 11; l ++)
			{
				for(int c = 0; c < 11; c++)
				{
					if( pPlateau[l][c] == pColor )
					{
						ret[0] = l;
						ret[1] = c;
						return ret;
					}
				}
			}
		}
		
		return null;
		
	}
	
	/**
	 * Copie le tableau envoyer en parametre
	 * @param pTab le tableau a copier
	 * @return le tableau copier
	 */
	private static int[][] mCopyTab(int[][] pTab)
	{
		if (pTab != null)
		{
			int width = 0;
			int height = 0;
			int tab[][] = null;
				width = pTab.length;
				if (width > 0)
				{
					height = pTab[0].length;				
				}
				tab = new int[width][height];
				
				for (int l = 0; l < width; l++)
				{
					for (int c = 0; c < height; c++)
					{
						tab[l][c] = pTab[l][c];
					}
				}
			return tab;
		}
		else
		{
			Log.mLog("Error : GameOver -> mCopyTab(int[][] pTab) erreur de parametres");
		}
		return null;
	}
	
	/**
	 * Methode recursive:
	 * met la valeur aPlateau[pLine][pColumn] a 10
	 * puis appel cette methode pour les 8 cases autour de la case en pLine pColonne si il y a un pion
	 * de la couleur aColor a cette indice.
	 * (Avec cette methode, le graphe conexe donc le pion pLine pColonne appartient se retrouvera à l'indice 10)
	 *  
	 * @param pLine la ligne du pion
	 * @param pColumn la colonne du pion
	 */
	private static void isConexe(int pLine, int pColumn, int pColor, int[][] pPlateau)
	{
		if (pLine >= 0 && pLine < 11 && pColumn >= 0 && pColumn <= 11)
		{
			pPlateau[pLine][pColumn] = 10;
			for (int l = pLine - 1; l < pLine + 2; l++) // Pour chaque cases a cote du pion
			{
				for (int c = pColumn - 1; c < pColumn + 2; c++)
				{
					if ( l < 11 && l >= 0 && c < 11 && c >= 0) // Si la case teste n'est pas en dehors du plateau
					{
						if ( pPlateau[l][c] == pColor )
						{
							isConexe(l, c, pColor, pPlateau);
						}
					}
				}
			}
		}
		else
		{
			Log.mLog("Error : GameOver -> isConexe(int pLine, int pColumn) erreur de parametres");
		}
		
	}
	
	/**
	 * Cherche dans tout le plateau si il y a des pions de la couleur pColor
	 * Si il n'y en a plus alors pColor a gagner, sinon, pas encore
	 * @return renvoit vrais si il a gagner, false sinon
	 */
	public static boolean mIsOver(int pColor, Zen pZen, int[][] pPlateau)
	{
		if ( (pColor == 1 || pColor == -1) && pZen != null && pPlateau != null && pPlateau.length == 11 && pPlateau[0].length == 11)
		{

			int[][] plateau = mCopyTab(pPlateau);
			int[] startPion = mStarterPion(pColor, pZen, plateau);
			
			isConexe(startPion[0], startPion[1], pColor, plateau);
		
			
			for(int l = 0; l < 11; l ++)
			{
				for(int c = 0; c < 11; c++)
				{
					if ( plateau[l][c] == pColor)
					{
						return false;
					}
				}
			}	
			return true;
		}
		else
		{
			Log.mLog("Error : GameOver -> Constructeur erreur de parametres");
		}
		return false;
	}
	
}
