package Modele;

import java.util.Scanner;

import file.Load;
import file.Log;
import file.Save;

/**
 * Cette classe ne contient que des méthodes statique et donc n'a aucune raison d'etre instancier.
 * Toutes ces méthodes sont utiliser par différentes classe dans tout le projet et tous les package.
 * @author Bernier Allan
 *
 */
public class Utile {

	private static Scanner sc = new Scanner(System.in);;

	/**
	 * Renvoit le plateau du jeu initialise au tour 1. 
	 * @return le plateau de int a 2 dimensions
	 */
	public static int[][] mInitPlateau()
	{
		int ret[][] =  {{1 ,0 ,0 ,0 ,0 ,1 ,0 ,0 ,0 ,0 ,-1},//0
						{0 ,0 ,0 ,0 ,-1,0 ,-1,0 ,0 ,0 ,0 },//1
						{0 ,0 ,0 ,1 ,0 ,0 ,0 ,1 ,0 ,0 ,0 },//2
						{0 ,0 ,-1,0 ,0 ,0 ,0 ,0 ,-1,0 ,0 },//3
						{0 ,1 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,1 ,0 },//4
						{-1,0 ,0 ,0 ,0 ,2 ,0 ,0 ,0 ,0 ,-1},//5
						{0 ,1 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,1 ,0 },//6
						{0 ,0 ,-1,0 ,0 ,0 ,0 ,0 ,-1,0 ,0 },//7 c 3 l 8
						{0 ,0 ,0 ,1 ,0 ,0 ,0 ,1 ,0 ,0 ,0 },//8
						{0 ,0 ,0 ,0 ,-1,0 ,-1,0 ,0 ,0 ,0 },//9
						{-1,0 ,0 ,0 ,0 ,1 ,0 ,0 ,0 ,0 ,1 } //10
					}; //0 ,1 ,2 ,3 ,4 ,5 ,6 ,7 ,8 ,9 ,10	
		return ret;
		
	}

	/**
	 * Affiche les credits, demande a l'uttilisateur de rentrer 2 pour retourner au menu
	 */
	public static void mCredits()
	{

		System.out.println("Jeux programmé sous Java \n"
				+ "Par Allan Bernier dans le cadre du projet de programmation\n"
				+ "du DUT informatique de Vannes.\n");

		System.out.println("2 : Retour");
		int choix = -1;
		do 
		{
			try
			{
				choix = Integer.parseInt( sc .nextLine() );				
			}
			catch (NumberFormatException e)	{
				Log.mLog("mCredits : tryCatch - NumberFormatException");
			}				
		}while ( choix != 2);

	}
	
	/**
	 * Regles du jeux:
	 * @return Renvoie un tableau contenant chaqu'une des regles du jeux
	 */
	public static String[] mGetRulesPrint()
	{
		String[] rules = {
				"Debut de la partie: \n"
				+ "Chaque joueur choisit une couleur blanc ou noir\\n"
				+ "Le pion Zen est au centre du plateau\\n"
				+ "Le joueur qui commence est tiré au sort.\\n"
			,				
				"But du jeu: \n"
				+ "Le vainqueur est le premier joueur qui reussit a former une chaine continue\n"
				+ "avec la totalité de ses pions y compris le Zen si celui-ci est encore en jeu.\n"
			,				
				"Deroulement de la partie: \n"
				+ "Chaque joueur deplace a son tour un pion de sa couleur ou le Zen en respectant 4\n"
				+ "regles tres simple."
			,				
				"Regle 1: \n"
				+ "Les pions se deplacent en ligne droite dans nimporte quelle\n"
				+ "direction (ligne, colonne, diagonales)\n"
				+ "Tout pions doit toujour se deplacer d'autant de cases qu'il y a de pions sur la ligne de\n"
				+ "deplacement choisie\n"
				+ "(Tout les pions y compris le pion deplace"
			,				
				"Regle 2: \n"
				+ "Tout pion peut passer par dessus un ou plusieur pions de sa couleur mais \n"
				+ "jamais par dessus ceux de son adversaire !"
			,				
				"Regle 3: \n"
				+ "Tout pion peut capturer un pion adverse en se placant sur la case occupe par le pion en respectant la regle 1"
			,				
				"Regle 4: \n"
				+ "a chaque coup, le Zen, pion commun a tous les joueurs peut être soit blanc, soit noir\n"
				+ "selon l'interet de celui qui joue "
			,				
				"Particularite autour du Zen:"
				+ "\ta) Lorcequ'il est deplacer par un joueur, son adversaire n'a pas\n"
				+ "le droit de le replacer sur la meme case au tour suivant\n"
				+ "\n"
				+ "\tb) Il est interdit de le deplacer si il ne se trouve pas en contact avec\n"
				+ "au moins un autre pion (blanc ou noir)"
			,				
				"Match Null: \n"
				+ "Si en fin de partie un joueur finis au meme coup, son graph et celui de son adversaire"				
			};
		
		
		return rules;
	}
	
	/**
	 * Sert a afficher les regles dans des JLabel, ces String sont ecrit avec des balises html
	 * @param pNoRule le numero de la regle que l'on veut en retour ( entre 0 et 9)
	 * @return Le string correspondant à la regle
	 */
	public static String mGetRulesJLabel(int pNoRule)
	{
		String[] rules = {
				"<html>Debut de la partie: <br>"
				+ "Chaque joueur choisit une couleur blanc ou<br> noir"
				+ "Le pion Zen est au centre du plateau<br>"
				+ "Le joueur qui commence est tiré au sort.<br></html>"
			,				
				"<html>But du jeu: <br>"
				+ "Le vainqueur est le premier joueur qui<br> reussit a former une chaine continue<br>"
				+ "avec la totalité de ses pions y compris<br> le Zen si celui-ci est encore en jeu.<br></html>"
			,				
				"<html>Deroulement de la partie: <br>"
				+ "Chaque joueur deplace a son tour<br> un pion de sa couleur ou le Zen en<br> respectant 4"
				+ "regles tres simple.<br></html>"
			,				
				"<html>Regle 1: <br>"
				+ "Les pions se deplacent en<br> ligne droite dans nimporte quelle<br>"
				+ "direction (ligne, colonne, diagonales)<br>"
				+ "Tout pions doit toujour se deplacer<br> d'autant de cases qu'il y a de pions<br> sur la ligne de<br>"
				+ "deplacement choisie<br>"
				+ "(Tout les pions y compris le pion deplace<br></html>"
			,				
				"<html>Regle 2: <br>"
				+ "Tout pion peut passer par dessus un<br> ou plusieur pions de sa couleur mais <br>"
				+ "jamais par dessus ceux<br> de son adversaire !<br></html>"
			,				
				"<html>Regle 3: <br>"
				+ "Tout pion peut capturer un pion<br> adverse en se placant sur la case occupe<br> par le pion en respectant la regle 1<br></html>"
			,				
				"<html>Regle 4: <br>"
				+ "a chaque coup, le Zen, pion commun <br>a tous les joueurs peut être soit blanc<br>, soit noir"
				+ "selon l'interet de celui qui joue <br></html>"
			,				
				"<html>Particularite autour du Zen:<br>"
				+ "\t<br>a) Lorcequ'il est deplacer<br> par un joueur, son adversaire n'a pas<br>"
				+ "le droit de le replacer sur <br>la meme case au tour suivant<br>"
				+ "<br>"
				+ "\tb) Il est interdit de le deplacer<br> si il ne se trouve pas en contact avec<br>"
				+ "au moins un autre pion (blanc ou noir)<br></html>"
			,				
				"<html>Match Null: <br>"
				+ "Si en fin de partie un joueur finis au meme<br> coup, son graph et celui de son adversaire<br></html>"				
			};
		
		if (pNoRule >= 0 && pNoRule <= rules.length )
		{
			return rules[pNoRule];
		}
		return rules[0];
	}
	
	
	/**
	 * affiche les règles 1 par 1 avec un scaner pour passer de l'une a l'autre
	 * Si on envoit 1 au scaner il lit la règle precedent
	 * 2 il retourne a la dernière fenetre d'ou il a ete appeler
	 * 3 il lit la regle suivante.
	 */
	public static void mRules()
	{

		String[] sRules = Utile.mGetRulesPrint();
		
		
		int actualRule = 0;
		int choix = -1;

		do 
		{
			if (choix == 1) 
			{
				actualRule --;
			}
			else if ( choix == 3)
			{
				actualRule ++;
			}
			if (actualRule >= sRules.length )
			{
				actualRule = 0; 
			}
			else if (actualRule < 0)
			{
				actualRule = sRules.length - 1 ; 
			}
			System.out.println(sRules[actualRule] + "\n\n" + " 1: Precedent \t 2: Retour \t 3: Suivant");

			try
			{
				choix = Integer.parseInt( sc.nextLine() );				
			}
			catch (NumberFormatException e)
			{
				Log.mLog("mRules : tryCatch - NumberFormatException");

			}				
		}while ( choix != 2 );
		// Si le choix est egale a 2, la fonction s'arette et retourne a son lieux d'appel		
	}

	/**
	 * Fonction a executer en jeux pour ecraser une sauvegarde existante et sauvegarder la partie actuelle à la place
	 * Cette fonction charge les 5 sauvegarde existante ( meme si il n'y a pas de partie)
	 * Les reponses attendu sont entre 0 et 4 pour les sauvegarde et 5 pour annuler
	 * 
	 */
	public static void mSaveIg(int[][] pPlateau, Mode pMode , Zen pZen, int pTurnToPlay , int pNoTurn)
	{

		System.out.println("Choisissez votre point de sauvegarde");

		for (int i = 0; i < 5; i++)
		{
			Load l = new Load(i);
			System.out.println("Save " + i + ": " + l.toString() );
		}
		
		System.out.println("5 : Annuler");
		int choix = -1;
		do 
		{
			try
			{
				choix = Integer.parseInt( sc.nextLine() );				
			}
			catch (NumberFormatException e)
			{
				Log.mLog("mSaveIg : tryCatch - NumberFormatException");
			}				
		}while ( choix < 0 || choix > 5);
		
		if ( choix <= 4) // Si le choix n'est pas annuler, alors sauvegarder la partie
		{
			Save.mSaveGame(pPlateau, pMode , pZen, pTurnToPlay , pNoTurn, choix);
		}// Sauvegarde l'etat actuel de la partie !
	}
		
	/**
	 * Affiche le plateau de jeux avec les pions correspondant
	 * en transformant le tableau de int en parametre en ascii
	 */
	public static void mAfficherPlateau(int[][] pPlateau)// Plus tard les Lettres seront remplacé par des couleurs
	{

		for (int l = 0; l < 11 ; l++)
		{
			for (int c = 0; c < 11; c ++)
			{
				if( pPlateau[l][c] == -1) // Noir
				{
					System.out.print("(Noir)\t");
				}
				else if( pPlateau[l][c] == 0) // Case vide
				{
					System.out.print("(     )\t");
				}
				else if( pPlateau[l][c] == 1 ) // Blanc
				{
					System.out.print("(Blanc)\t");
				}
				else if (pPlateau[l][c] == 2) // Zen l'initié
				{
					System.out.print("( Zen )\t");
				}	
			}
			System.out.println( (11-l) + "\t\n" );
		}
		System.out.println("   A       B       C       D       E       F       G       H       I      J      K    ");
		System.out.println("________________________________________________________________________________________");

	}

	/**
	 * Affiche le plateau de jeux avec les pions correspondant et les deplacements possible pour le pion demander
	 * en transformant le tableau de int envoyer en parametre en ascii
	 * @param pDeplacements un tableau de deplacement de 8 par 3 indiquant tout les endrois ou le joueur peut aller
	 * @param pPosPion les coordonee ligne et colonne du pion que le joueur veut bouger
	 */
	public static void mAfficherPlateau(int[][] pPlateau, int[][] pDeplacements,int[] pPosPion)
	{
		String[][] myTab = new String[11][11];
		for (int l = 0; l < 11 ; l++)
		{
			for (int c = 0; c < 11; c ++)
			{				
				if( pPlateau[l][c] == -1) // Noir
				{
					myTab[l][c] = "(Noir )\t";
				}
				else if( pPlateau[l][c] == 0) // Case vide
				{
					myTab[l][c] = "(     )\t";
				}
				else if( pPlateau[l][c] == 1 ) // Blanc
				{
					myTab[l][c] = "(Blanc)\t";
				}
				else if (pPlateau[l][c] == 2) // Zen l'initié
				{
					myTab[l][c] = "( Zen )\t";
				}
				
			}
		}
		
		myTab[ pPosPion[0] ][ pPosPion[1] ] = "[" + myTab[ pPosPion[0] ][ pPosPion[1] ].substring(1,6) + "]\t";

		
		for (int i = 0; i < 8 ; i++)
		{
			if (pDeplacements[i][2] == 1)
			{
				myTab[ pDeplacements[i][0] ][ pDeplacements[i][1] ] = "[[" + myTab[ pDeplacements[i][0] ][ pDeplacements[i][1] ].substring(2,5) + "]]\t";
			}
			else if( pDeplacements[i][2] == 3 )
			{
				myTab[ pDeplacements[i][0] ][ pDeplacements[i][1] ] = "{{eat}}\t";
			}
		}
		
		for (int l = 0; l < 11 ; l++)
		{
			for (int c = 0; c < 11; c ++)
			{	
				System.out.print(myTab[l][c] );
			}
			System.out.println( (11-l) + "\t\n" );
		}
		System.out.println("   A       B       C       D       E       F       G       H       I      J      K    ");
		System.out.println("________________________________________________________________________________________");

	}
	
	/**
	 * Renvoie la distance entre deux position.
	 * @param x1 line of point 1 
	 * @param y1 column of point 1 
	 * @param x2 line of point 2
	 * @param y2 column of point 2
	 * @return double, correspond a la distance.
	 */
	public static double mGetDist(int x1,int y1,int x2,int y2)
	{		
	    return (Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1))); // ~= pytagore
	}
	
	/**
	 * Renvoie la distance entre deux position, une position en int et une en double.
	 * @param x1 line of point 1 
	 * @param y1 column of point 1 
	 * @param x2 line of point 2
	 * @param y2 column of point 2
	 * @return double, correspond a la distance.
	 */
	public static double mGetDist(int x1,int y1,double x2,double y2)
	{		
	    return (double) (Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1))); // ~= pytagore
	}
	
	/**
	 * Charge une sauvegarde grace a la classe Load puis transforme cette sauvegarde en un apercu du cour de la partie 
	 * @param pSave l'indice de la sauvegarde
	 * @return Apercu de la partie
	 */
	public static String mSaveToString(int pSave)
	{
		System.out.println("La save " + pSave);
		Load loadGame = new Load(pSave);
		int[][] plateau = loadGame.getPlateau();
		String ret = "";
		
		// transforme le plateau en un block de caractere de 11 lignes par 11 colonnes 
		for (int l = 0 ; l< plateau.length ; l++)
		{
			for (int c = 0 ; c < plateau[l].length ; c++)
			{
				if (plateau[l][c] == -1 )
				{
					ret = ret + "3  ";
				}
				else
				{
					ret = ret + plateau[l][c] + "  ";
				}
			}	
			ret = ret + "\n";
		}
		
		ret = ret + "\n" + loadGame.toString();
		return ret;
	}
	
	/**
	 * Renvoie le plateau de jeux en un tableau de string sans aucun pion selectionnée.
	 * @param pPlateau l'etat actuel du jeux
	 * @param pColor 1 = blanc ont gagner -1 = noir ont gagner 2 == égaliter  
	 * @return Le plateau de jeux graphiquement beau.
	 */
	public static String[][] PlateauToString(int[][] pPlateau, int pColor)
	{
		if (pPlateau != null && pPlateau.length == 11 && pPlateau[0].length == 11 )
		{
			String[][] myTab = new String[11][11];
			
			for (int l = 0; l < pPlateau.length; l++)
			{
				for (int c = 0; c < pPlateau.length; c++)
				{
					if (pPlateau[l][c] == 0 )
					{
						myTab[l][c] = "Void";
					}

					else if (pPlateau[l][c] == 1 )
					{
						if (pPlateau[l][c] == pColor || pColor == 2)
						{
							myTab[l][c] = "Move_White";
						}
						else
						{
							myTab[l][c] = "White";
						}
					}
					else if (pPlateau[l][c] == -1 )
					{
						if (pPlateau[l][c] == pColor || pColor == 2)
						{
							myTab[l][c] = "Move_Black";
						}
						else
						{
							myTab[l][c] = "Black";
						}
					}
					else if (pPlateau[l][c] == 2 )
					{
						myTab[l][c] = "Move_Zen";
					}

					
					if ( (l+c)%2 > 0 )
					{
						myTab[l][c] = myTab[l][c] + "_Black";
					}
					else
					{
						myTab[l][c] = myTab[l][c] + "_White";
					}
				}			
			}
			return myTab;
		}
		else
		{
			Log.mLog("Error : Game -> PlateauToString(int[][] pPlateau) erreur de parametres");
		}
		return null;
	}	
	
	/**
	 * Renvoie le plateau de jeux en un tableau de string sans aucun pion selectionnée.
	 * @param pPlateau l'etat actuel du jeux
	 * @return Le plateau de jeux graphiquement beau.
	 */
	public static String[][] PlateauToString(int[][] pPlateau)
	{
		if (pPlateau != null && pPlateau.length == 11 && pPlateau[0].length == 11 )
		{
			String[][] myTab = new String[11][11];
			
			for (int l = 0; l < pPlateau.length; l++)
			{
				for (int c = 0; c < pPlateau.length; c++)
				{
					if (pPlateau[l][c] == 0 )
					{
						myTab[l][c] = "Void";
					}
					else if (pPlateau[l][c] == 1 )
					{
						myTab[l][c] = "White";
					}
					else if (pPlateau[l][c] == -1 )
					{
						myTab[l][c] = "Black";
					}
					else if (pPlateau[l][c] == 2 )
					{
						myTab[l][c] = "Zen";
					}

					
					if ( (l+c)%2 > 0 )
					{
						myTab[l][c] = myTab[l][c] + "_Black";
					}
					else
					{
						myTab[l][c] = myTab[l][c] + "_White";
					}
				}			
			}
			return myTab;
		}
		else
		{
			Log.mLog("Error : Game -> PlateauToString(int[][] pPlateau) erreur de parametres");
		}
		return null;
	}	
	
	/**
	 * Renvoie le plateau de jeux en un tableau de string avec le pion selectionnée et les case de déplacement possible affichée
	 * @param pPlateau l'etat actuel du jeux
	 * @return Le plateau de jeux graphiquement beau.
	 */
	public static String[][] PlateauToString(int[][] pPlateau, int[][] pDeplacements,int[] pPosPion)
	{
		if (pPlateau != null && pPlateau.length == 11 && pPlateau[0].length == 11 && pDeplacements != null && pDeplacements.length == 8 && pDeplacements[0].length == 3)
		{
			String[][] myTab = new String[11][11];
			for (int l = 0; l < 11 ; l++)
			{
				for (int c = 0; c < 11; c ++)
				{				
					if( pPlateau[l][c] == -1) // Noir
					{
						myTab[l][c] = "Black";
					}
					else if( pPlateau[l][c] == 0) // Case vide
					{
						myTab[l][c] = "Void";
					}
					else if( pPlateau[l][c] == 1 ) // Blanc
					{
						myTab[l][c] = "White";
					}
					else if (pPlateau[l][c] == 2) // Zen l'initié
					{
						myTab[l][c] = "Zen";
					}
					
					if ( (l+c)%2 > 0 )
					{
						myTab[l][c] = myTab[l][c] + "_Black";
					}
					else
					{
						myTab[l][c] = myTab[l][c] + "_White";
					}
					
				}
			}
			
			myTab[ pPosPion[0] ][ pPosPion[1] ] = "Move_" + myTab[ pPosPion[0] ][ pPosPion[1] ];
	
			
			for (int i = 0; i < 8 ; i++)
			{
				if (pDeplacements[i][2] == 1 || pDeplacements[i][2] == 3 )
				{
					myTab[ pDeplacements[i][0] ][ pDeplacements[i][1] ] = "Move_" + myTab[ pDeplacements[i][0] ][ pDeplacements[i][1] ];
				}
			}
			
			return myTab;
		}
		else
		{
			Log.mLog("Error : Game -> PlateauToString(int[][] pPlateau, int[][] pDeplacements,int[] pPosPion) erreur de parametres");
		}
		return null;
	}


}
