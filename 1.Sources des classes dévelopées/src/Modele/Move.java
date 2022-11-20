package Modele;

import file.Log;

/**
 * Cette classe plermet de calculer tout les deplacements possible pour un pion en lui envoyant sa couleur, le plateau et sa position
 * @author Allan Bernier
 * @since 18/04/20
 */
public final class Move
{
	/**
	 * Compte le nombre de pions présent dans la ligne et renvoit cette valeur
	 */
	private static int mNbInLigne(int pLine, int pColumn, int[][] pPlateau)
	{
		int ret = 0;
		if (pPlateau != null && pLine >= 0 && pLine < 11 && pColumn >= 0 && pColumn < 11)
		{
	 		
			for (int i = 0; i < 11; i++) // 11 correspond à la taille de la map
			{
				if ( pPlateau[pLine][i] != 0 ) // Si il y à un pion sur la ligne
				{
					ret ++; // Le nombre de pions présent augmente de 1
				}
			}
		}
		else
		{
			Log.mLog("Error : Move -> mNbInLigne() Problemes sur les attributs");
		}
		return ret;
	}
	
	/**
	 * Compte le nombre de pions présent dans la colonne et renvoit cette valeur
	 * @param aPlateau la map ou sont placé les pions
	 * @param aLine le placement y de notre pion ( x et y sont inversé à ligne colonne !)
	 * @param aColumn le placement x de notre pion
	 * @return Le nombre de pions présent dans la colonne
	 */
	private static int mNbInColonne(int pLine, int pColumn, int[][] pPlateau)
	{
		int ret = 0;
		if (pPlateau != null && pLine >= 0 && pLine < 11 && pColumn >= 0 && pColumn < 11)
		{
			
			for (int i = 0; i < 11; i++) // 11 correspond à la taille de la map
			{
				if ( pPlateau[i][pColumn] != 0 ) // Si il y à un pion sur la ligne
				{
					ret ++; // Le nombre de pions présent augmente de 1
				}
			}
		}
		else
		{
			Log.mLog("Error : Move -> mNbInColonne() Problemes sur les attributs");
		}
		return ret;
	}
	
	/**
	 * Compte le nombre de pions présent dans la diagonale de gauche et renvoit cette valeur
	 * (diagonal de gauche CAD de haut gauche jusqu'a bas droite)
	 * @param aPlateau la map ou sont placé les pions
	 * @param aLine le placement y de notre pion ( x et y sont inversé à ligne colonne !)
	 * @param aColumn le placement x de notre pion
	 * @return Le nombre de pions présent dans la diagonal
	 */
	private static int mNbInDiagonalGauche(int pLine, int pColumn, int[][] pPlateau)
	{
		int ret = 0;
		if (pPlateau != null && pLine >= 0 && pLine < 11 && pColumn >= 0 && pColumn < 11)
		{
			int diagonal;
			if (pColumn > pLine)
			{
				diagonal = pColumn - pLine; // Numéro de la diagonal
				int nbVal = 11 - diagonal; // nombre de valeurs dans la diagonal
				for (int i = 0; i < nbVal; i ++) // Pour chaque case de cette diagonal
				{
					if ( pPlateau[i][diagonal + i] != 0 ) // Si il y à un pion dedans
					{
						ret ++;
					}
				}
			}
			else
			{
				diagonal = pLine - pColumn; // Numéro de la diagonal
				int nbVal = 11 - diagonal; // nombre de valeurs dans la diagonal
				for (int i = 0; i < nbVal; i ++) // Pour chaque case de cette diagonal
				{
					if ( pPlateau[diagonal + i][i] != 0 ) // Si il y à un pion dedans
					{
						ret ++;
					}
				}
			}
			
		
		}
		else
		{
			Log.mLog("Error : Move -> mNbInDiagonalGauche() Problemes sur les attributs");
		}
		return ret;
	}
	
	/**
	 * Compte le nombre de pions présent dans la diagonale de gauche et renvoit cette valeur
	 * (diagonal de droite CAD de haut droite jusqu'a bas gauche)
	 * @param aPlateau la map ou sont placé les pions
	 * @param aLine le placement y de notre pion ( x et y sont inversé à ligne colonne !)
	 * @param aColumn le placement x de notre pion
	 * @return Le nombre de pions présent dans la diagonal
	 */
	private static int mNbInDiagonalDroite(int pLine, int pColumn, int[][] pPlateau)
	{
		int ret = 0;
		if (pPlateau != null && pLine >= 0 && pLine < 11 && pColumn >= 0 && pColumn < 11)
		{
			int diagonal;
			if (pLine + pColumn <= 10 )
			{
				diagonal =10 - (pColumn + pLine); // Numéro de la diagonal
				int nbVal =11 - diagonal; // nombre de valeurs dans la diagonal
				for (int i = 0; i < nbVal;i ++)
					{
					if ( pPlateau[i][10-(diagonal + i)] != 0 )
					{
						ret ++;
					}
				}
			}
			else
			{
				diagonal = (pLine + pColumn) - 10 ; // Numéro de la diagonal
				int nbVal =11 - diagonal; // nombre de valeurs dans la diagonal
				for (int i = 0; i < nbVal;i ++)
					{
					if ( pPlateau[(10-(nbVal-1)) + i][10 - i] != 0 )
					{
						ret ++;
					}
				}
			}
		
		}
		else
		{
			Log.mLog("Error : Move -> mNbInDiagonalDroite() Problemes sur les attributs");
		}
		return ret;
	}

	/**
	 * Cette methode renvoit un tableau sous la forme 
	 * {l,c,b},{l,c,b}... des 8 la cases ou le pions peut se prétendument se déplacer en ligne et colonne
	 * Ce tableau ne test pas les conditions de sortie de carte ou autres !!
	 * tab[0] correspond au déplacement en ligne vers la haut
	 * tab[1] correspond au déplacement en ligne vers la bas 
	 * tab[2] correspond au déplacement en colonne vers le droite
	 * tab[3] correspond au déplacement en colonne vers le gauche
	 * tab[4] correspond au déplacements en diagonal en haut a gauche
	 * tab[5] correspond au déplacements en diagonal en bas a droite
	 * tab[6] correspond au déplacements en diagonal en haut a droite
	 * tab[7] correspond au déplacements en diagonal en bas a gauche
	 * @param aPlateau la map servant à récuperer les valeurs
	 * @param aLine la ligne de position du pion
	 * @param aColumn la colonne de position du pions
	 * @return le tableau de deplacements
	 */
	private static int[][] mBrutMovePion(int pLine, int pColumn, int[][] pPlateau)
	{
		int ret[][] = new int [8][2];
		int dpInLigne 			= mNbInLigne(pLine, pColumn, pPlateau);
		int dpInColonne 		= mNbInColonne(pLine, pColumn, pPlateau);
		int dpInDiagonalGauche	= mNbInDiagonalGauche(pLine, pColumn, pPlateau);
		int dpInDiagonalDroite	= mNbInDiagonalDroite(pLine, pColumn, pPlateau);
		
		// Déplacements posible en Ligne pour le pion en aLine, aColumn
		
		ret[0][0] = pLine - dpInColonne;
		ret[0][1] = pColumn;
		
		ret[1][0] = pLine + dpInColonne;
		ret[1][1] = pColumn;

		// Déplacements posible en Colonne pour le pion en aLine, aColumn
		ret[2][0] = pLine;
		ret[2][1] = pColumn + dpInLigne;
		
		ret[3][0] = pLine;
		ret[3][1] = pColumn - dpInLigne;
		
		// Déplacements posible en Diagonal Gauche pour le pion en aLine, aColumn
		ret[4][0] = pLine - dpInDiagonalGauche;
		ret[4][1] = pColumn - dpInDiagonalGauche;
		
		ret[5][0] = pLine + dpInDiagonalGauche;
		ret[5][1] = pColumn + dpInDiagonalGauche;
	
		// Déplacements posible en Diagonal Droite pour le pion en aLine, aColumn	
		ret[6][0] = pLine + dpInDiagonalDroite;
		ret[6][1] = pColumn - dpInDiagonalDroite;
		
		ret[7][0] = pLine - dpInDiagonalDroite;
		ret[7][1] = pColumn + dpInDiagonalDroite;
		
		return ret;
	}

	/** 
	 * Verifie que le deplacement ne saute pas un pion d'une autre couleur 
	 * OU
	 * que le deplacement ne s'arrete pas sur une case de sa couleur !
	 * Renvoie un tableau avec les 8 déplacements differents 
	 * 1 si le deplacement est possible
	 * 2 si le deplacement chevauche un pion ennemie OU si le deplacement est sur un de mes pions
	 * 3 si le deplacement tue un pion ennemie!
	 * 4 si hors de la map
	 */
	private static int[][] mMovePion(int pColor, int pLine, int pColumn, int[][] pPlateau)
	{
		int[][] move = new int[8][2];
		int[] dest = new int[8];
		int[] chemin = new int[8];
		if (pColor == 1 || pColor == -1) // Si la couleur est bonne
		{
			// Recupere les deplacement possible avant verification.
			move = mBrutMovePion(pLine, pColumn, pPlateau);;

			//Verifie si la destination est possible.
			dest = mVerifDestination(move, pPlateau, pColor);
			 
			//Verifie si le chemin est possible.
			chemin = mVerifChemin(move, dest, pLine, pColumn, pPlateau, pColor);
		}
		else
		{
			Log.mLog("Error : Move -> mMovePion() La couleur du joueur n'existe pas !");
		}
		
		//Remplissage du tableau de deplacement
		int[][] ret = new int[8][3];
		for (int i = 0; i < 8; i++)
		{
			ret[i][0] = move[i][0];
			ret[i][1] = move[i][1];
			ret[i][2] = chemin[i];
		}
		
		return ret;
	}
	
	/**
	 * Regarde pour chaque case entre la position du pion et la destination si il y a un pion ennemie
	 * @param aPlateau le plateau actuelle
	 * @param aLine la ligne du pion
	 * @param aColumn la colonne du pion
	 * @param aColor la couleur du pion
	 * @param pMove tableau sous forme t[0] = ligne t[1] = colonne contenant la destination du pion
	 * @param pDest tableau avec une indication sur chaque destination pour savoir si il faut traité la direction ou non 
	 * @return tableau de int de 1 a 4
	 * 1 si le deplacement est possible
	 * 2 si le deplacement chevauche un pion ennemie OU si le deplacement est sur un de mes pions
	 * 3 si le deplacement tue un pion ennemie!
	 * 4 si hors de la map
	 */
	private static int[] mVerifChemin(int[][] pMove, int[] pDest, int pLine, int pColumn, int[][] pPlateau,int pColor) 
	{
		int[] ret = pDest;
		
		//Haut  -- Fait !
		int haut = 0;
		int zenColor = 2;
		if (ret[haut] != 4 && ret[haut] != 2 ) // Si la destination est possible..
		{
			for (int i = pLine - 1 ; i > pMove[haut][0] ; i--) // Ce for test toute les valeurs entre la position initiale et la destination 
			{
				if (pPlateau[ i ][ pMove[haut][1] ] != 0 && pPlateau[ i ][ pMove[haut][1] ] != pColor && pPlateau[ i ][ pMove[haut][1] ] != zenColor){ // Si la case est ni vide, ni notre pion, alors on chevauche un pion ennemie
					ret[haut] = 2;
				}
			}
		}	
		
		// Bas -- Fait !
		int bas = 1;
		if (ret[bas] != 4 && ret[bas] != 2 ) // Si la destination est possible..
		{
			for (int i = pLine + 1 ; i < pMove[bas][0] ; i++) // Ce for test toute les valeurs entre la position initiale et la destination 
			{
				if (pPlateau[i][ pMove[bas][1] ] != 0 && pPlateau[i][ pMove[bas][1] ] != pColor && pPlateau[ i ][ pMove[haut][1] ] != zenColor){ // Si la case est ni vide, ni notre pion, alors on chevauche un pion ennemie
					ret[bas] = 2;
				}
			}
		}			
			
		// Droite -- Fait !
		int droite = 2;
		if (ret[droite] != 4 && ret[droite] != 2 ) // Si la destination est possible..
		{
			for (int i = pColumn + 1 ; i < pMove[droite][1] ; i++) // Ce for test toute les valeurs entre la position initiale et la destination 
			{
				if (pPlateau[ pMove[droite][0] ][ i ] != 0 && pPlateau[ pMove[droite][0] ][ i ] != pColor && pPlateau[ i ][ pMove[haut][1] ] != zenColor){ // Si la case est ni vide, ni notre pion, alors on chevauche un pion ennemie
					ret[droite] = 2;
				}
			}
		}	
			
		// Gauche -- Fait !
		int gauche = 3;
		if (ret[gauche] != 4 && ret[gauche] != 2 ) // Si la destination est possible..
		{
			for (int i = pColumn - 1 ; i > pMove[gauche][1] ; i--) // Ce for test toute les valeurs entre la position initiale et la destination 
			{
				if (pPlateau[ pMove[gauche][0] ][ i ] != 0 && pPlateau[ pMove[gauche][0] ][ i ] != pColor && pPlateau[ i ][ pMove[haut][1] ] != zenColor){ // Si la case est ni vide, ni notre pion, alors on chevauche un pion ennemie
					ret[gauche] = 2;
				}
			}
		}		
			
		// Haut-Gauche -- Fait !
		int hg = 4; 
		if (ret[hg] != 4 && ret[hg] != 2 ) // Si la destination est possible..
		{
			for (int i = 1 ; i < pLine - pMove[hg][0] ; i++) // Ce for test toute les valeurs entre la position initiale et la destination 
			{
				if ( pPlateau[pLine - i ][pColumn - i] != 0 && pPlateau[pLine - i ][pColumn - i] != pColor && pPlateau[ i ][ pMove[haut][1] ] != zenColor ){ // Si la case est ni vide, ni notre pion, alors on chevauche un pion ennemie
					ret[hg] = 2;
				}
			}
		}			
		
		// Bas-Droite -- Fait !
		int bd = 5; 
		if (ret[bd] != 4 && ret[bd] != 2 ) // Si la destination est possible..
		{
			for (int i = 1 ; i < pMove[bd][0] - pLine  ; i++) // Ce for test toute les valeurs entre la position initiale et la destination 
			{
				if (pPlateau[ pLine + i ][ pColumn + i  ] != 0 && pPlateau[ pLine + i ][ pColumn ] != pColor && pPlateau[ i ][ pMove[haut][1] ] != zenColor){ // Si la case est ni vide, ni notre pion, alors on chevauche un pion ennemie
					ret[bd] = 2;
				}
			}
		}		
			
			
			
		// Bas-Gauche -- Fait
		int bg = 6; 
		if (ret[bg] != 4 && ret[bg] != 2 ) // Si la destination est possible..
		{
			for (int i = 1 ; i < pMove[bg][0] - pLine ; i++) // Ce for test toute les valeurs entre la position initiale et la destination 
			{
				if (pPlateau[pLine + i ][ pColumn - i ] != 0 && pPlateau[pLine + i ][ pColumn - i ] != pColor && pPlateau[ i ][ pMove[haut][1] ] != zenColor){ // Si la case est ni vide, ni notre pion, alors on chevauche un pion ennemie
					ret[bg] = 2;
				}
			}
		}		
		
		// Haut-Droit
		int hd = 7; 
		if (ret[hd] != 4 && ret[hd] != 2 ) // Si la destination est possible..
		{
			for (int i = 1 ; i < pLine - pMove[hd][0] ; i++) // Ce for test toute les valeurs entre la position initiale et la destination 
			{
				if (pPlateau[ pLine - i ][ pColumn + i ] != 0 && pPlateau[ pLine - i ][ pColumn + i ] != pColor && pPlateau[ i ][ pMove[haut][1] ] != zenColor ){ // Si la case est ni vide, ni notre pion, alors on chevauche un pion ennemie
					ret[hd] = 2;
				}
			}
		}	
		return ret;
	}
	
	/**
	 * Verifie si la destination du pion est possible dans chaqu'une des dirrection ( seulement la destination, pas le chemain pour y aller
	 * 1 si le deplacement est possible
	 * 2 si le deplacement chevauche un pion ennemie OU si le deplacement est sur un de mes pions
	 * 3 si le deplacement tue un pion ennemie!
	 * 4 si hors de la map
	 * @param aPlateau la position actuelle des pions sur le plateau
	 * @param aLine la ligne actuelle du pion
	 * @param aColumn la colonne actuelle du pion
	 * @param aColor la couleur du pion a bouger
	 * @param pDest les destination "possible" du pion
	 * @return Un tableau de int de 8 elements avec chaque cas pour chaque direction.
	 */
	private static int[] mVerifDestination( int[][] pDest, int[][] pPlateau, int pColor)
	{
		int[] ret = new int[8];
			for (int i = 0; i < 8 ; i++) // Pour chaque deplacement possible 
			{
				if ( pDest[i][0] < 11 && pDest[i][0] >= 0 && pDest[i][1] < 11 && pDest[i][1] >= 0 ) // Verifier que la destination de sors pas du plateau 
				{
					if ( pPlateau[ pDest[i][0] ][ pDest[i][1] ] == pColor ) // Si le pion d'arriver est de sa couleur
					{
						ret[i] = 2; // destination impossible
					}
					else if ( pPlateau[ pDest[i][0] ][ pDest[i][1] ] != pColor && pPlateau[ pDest[i][0] ][ pDest[i][1] ] != 0 ) // Sinon si le pion d'arriver est different de sa couleur et different de 0 ( donc un pion ennemie )
					{
						ret[i] = 3; // tue un pion ennemie
					}
					else 
					{
						ret[i] = 1; // destination possible
					}
				}
				else
				{
				 ret[i] = 4; // depacement de carte
				}
			}
		return ret;
	}

	/**
	 * @return the aMove
	 */
	public static int[][] getMove(int[][] pPlateau, int pLine, int pColumn, int pColor)
	{
		if( pPlateau != null && pPlateau.length == 11 && pPlateau[0].length == 11 && pLine >=0 && pLine < 11 && pColumn >= 0 && pColumn < 11 && (pColor == 1 || pColor == -1))
		{
			return mMovePion(pColor, pLine, pColumn, pPlateau);	
		}
		else
		{
			Log.mLog("Error : Move -> getMove erreur de parametres");
		}
		return null;
	}

}



