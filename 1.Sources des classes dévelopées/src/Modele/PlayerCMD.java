package Modele;

import java.util.Scanner;

import file.Log;

public class PlayerCMD extends Player{
	
	Mode aMode;
	boolean aLeaveGame;
	int aTurnToPlay; 	// Indice du joueur qui dois jouer ( -1 = NOIR ou 1 = BLANC )
	Scanner sc;
	
	public PlayerCMD(int pColor, Zen pZen, Mode pMode)
	{
		super(pColor,pZen);
		this.aLeaveGame = false;
		this.aMode = pMode;
		this.sc = new Scanner(System.in);
	}
	
	/**
	 * Realise toutes les actions d'un tour pour le joueur,
	 * Demande au joueur le pion qui dois bouger, ensuite l'endrois ou il dois bouger, puis bouge ce pions
	 * @param pZen le zen actuelle de la partie.
	 * @param pPlateau le plateau de jeu actuelle.
	 * @param pNoTurn le tour de jeu.
	 * @param pMode le mode de jeux.
	 * @return Si le joueur decide de quitter la partie, alors le return est egale a -2.
	 */
	public void mPlay(Zen pZen, int[][] pPlateau,int pNoTurn)
	{
		if (pZen != null && pPlateau != null && pPlateau.length == 11 && pPlateau[0].length == 11 && pNoTurn >= 0)
		{
			

			// Mettre a jour les attribut du joueur;
			this.aZen = pZen;
			this.aPlateau = pPlateau;
			this.aNoTurn = pNoTurn;
			
			// Demander le pion a deplacer et la case ou il dois aller
			int retourMenu = -2;
			int[] move = new int[2];
			int[] pion;
			do
			{
				pion = mPionToMove();
				
				if (pion[0] != retourMenu)
				{		
					move = mWhere(pion);
				}
				else
				{
					this.aLeaveGame = true;
				}

			} while ( move[0] == -1 );
			
			// Si le joueur veux quitter la partie
			if (pion[0] == -2 || pion[1] == -2 || move[0] == -2 || pion[1] == -2)
			{
				this.aLeaveGame = true;
			}
			
			if ( !this.aLeaveGame )
			{
				deplacerPion(pion, move);
			}
		}
		else
		{
			Log.mLog("error : PlayerHumanTerminal -> mPlay erreur de parametres");
		}
	}
	
	/**
	 * Une fois que le joueur a donner une position pour se deplacer, verifier que cette position correspond à un de ses pion ou au Zen.
	 * @return La position ou le joueur veux se deplacer
	 */
	public int[] mPionToMove()
	{
		int retourMenu = -2;
		int[] pos;
		do 
		{
			pos = mAskPos();				
		}while( pos[0] != retourMenu
			 && this.aPlateau[ pos[0] ][ pos[1] ] != this.aZen.getZenColor() 
			 && this.aPlateau[ pos[0] ][ pos[1] ] != this.aColor); 
		// Continuer de demander une nouvelle position tant que :
		// La couleur du pion selectionnée par le joueur n'est pas la sienne
		// OU La couleur du pion selectionnée par le joueur n'est pas celle du Zen
		// OU que le pion selectionnee renvoit l'action retour au menu
		

		return pos; 
	}
	
	/**
	 * Demande la position ou bouger le pion selectionnée
	 * @param pPos les coordonee du pions selectionee
	 * @return
	 */
	
	public int[] mWhere(int pPos[])
	{
		// Recupere un tableau de deplacement possible pour ce pion
		int[][] move = Move.getMove(this.aPlateau, pPos[0], pPos[1], this.aColor );


		
		if (aPlateau[ pPos[0] ][ pPos[1] ] == aZen.getZenColor() ) // Si le pion deplacer est le zen 
		{
			aZen.setLine(pPos[0]);
			aZen.setColumn(pPos[1]);
			move = aZen.mZenMove(this.aPlateau, move , this.aNoTurn); // On verifit à nouveau les deplacement mais concernant le zen
		}

		Utile.mAfficherPlateau(this.aPlateau , move, pPos);
		
		// Demande la position ou deplacer le pion
		int[] where = mAskMove(move);
			
		return where;
	}
	
	/**
	 * Demande une position a l'utilisateur jusqu'a ce que cette position fasse partie des cases du plateau 
	 * Ensuite une 2eme methode testera si cette position correspond a un pion du joueur.
	 * @return tableau de int sous forme tab[0] = ligne tab[1] = column.
	 */
	public int[] mAskPos()
	{
		int[] ret = null;
		
		
		Utile.mAfficherPlateau(this.aPlateau);
		System.out.println("1) Menu \t2) Sauvegarder \t3) Regles \t4)Quitter");
		System.out.println("Entrez une position sous la forme \"A1\" jusqu'a \"K11\" ");
		System.out.println("Au tour du joueur " + (this.aColor ==1 ? "Blanc" : "Noir ") );

		String str;
		int act = 0;
		
		do
		{
			System.out.print("Bouger le pion en [");
			str = sc.nextLine();		
			act = mBoutonIg( str, "ask Pion"); // Dans le cas ou l'entrée clavier utilisateur ne serait pas une position, ni une erreur, alors il peut vouloir acceder à un des menu, sauvegarde ou autre, il faudras ensuite revenir ici 
			ret = mDechiffrePos(str);
			if (act == -2)// Si le joueur veux retourner au menu, retourner -2
			{
				ret[0] = -2;
				ret[1] = -2;
				
				return ret;
			}
		}while ( ret[0] == -1 && ret[1] == -1); // Continue de redemander tant que le programme n'arrive pas à dechiffrer.
		
		return ret;
	}
	
	/**
	 * Lorceque le joueur est en jeux il peut choisir de demander plusieurs information
	 * Sauvegarder, quitter, retourner au menu ou acceder au regles
	 * Cette methode recupere le choix de l'utilisateur et le fais acceder a son emplacement choisi
	 * @param pChoix Recupere en string l'action donner par l'uttilisateur.
	 * @param pFrom Permetant de savoir si le joueur peut annuler son action ou non ce String peut etre egale a "ask Move" ou "ask Pos"
	 * @Return Si le joueur decide d'annuler le pion selectionnee retourn 1, sinon retour -1
	 */
	public int mBoutonIg(String pChoix, String pFrom)
	{
		int choix;
		try 
		{
			choix = Integer.parseInt( pChoix );
			if (choix == 1) // Retour au menu
			{
				System.out.println("== Menu ==");
				return -2;

			}
			else if (choix == 2) // Sauvegarder 
			{
				System.out.println("== Sauvegarder =="); // Bien sur sauvegarder n'est pas codé ! 
				Utile.mSaveIg(this.aPlateau, this.aMode, this.aZen, this.aTurnToPlay, this.aNoTurn);
				Utile.mAfficherPlateau(aPlateau);
			}
			else if (choix == 3) // Acceder au regles
			{
				System.out.println("== Regles ==");
				Utile.mRules();
				Utile.mAfficherPlateau(aPlateau);
			}
			else if (choix == 4) // Quitter le programme
			{
				System.out.println("== Quitter ==");
				System.exit(0); 
			}
			else if (choix == 5)
			{
				if ( pFrom.equals("ask Move") ) // Si on a deja demander une position
				{
					return -1;
				}
			}
			
		}
		catch (NumberFormatException e)
		{	
			Log.mLog("mBoutonIg : tryCatch - NumberFormatException");
		}
		
		return 0;
	}
	
	/**
	 * Recupere un string et le renvoie sous forme d'un tableau de int a deux valeurs comprises entre 0 et 10
	 * Si le string ne correspond pas pour etre transformer alors le tableau renvoyer est {-1,-1}.
	 * @param pPos un string normalement sous forme "A5" pour A = colonne et 5 = ligne
	 * @return les ligne colonne de l'endrois voulue.
	 */
	public int[] mDechiffrePos( String pPos)
	{
		pPos =  pPos.toLowerCase(); // Supprimer les majuscules
		char[] cColonne = {'a','b','c','d','e','f','g','h','i','j','k'};
		int colonne = -1; // Si la colonne n'est pas dans le tableau de colonne return -1
		int ligne = - 1;
	
		
		// La taille de la chaine ne peux etre que de 2 ou 3, si elle ne correspond pas en taille, pas la peine de tester  ( A1 -> 2  //  B11 -> 3 )
		if (pPos.length() == 2 || pPos.length() == 3 )  
		{
			char col = pPos.charAt(0);

			for(int i = 0; i < cColonne.length; i++)
			{
				if ( col == cColonne[i] ) // Si elle correspond à une vraie colonne ! 
				{
					colonne = i;
				}
			}
	
			try
			{
				ligne = Integer.parseInt( pPos.substring(1) ) - 1;  // -1 car les tableaux commencent à 0 
				ligne = 10 - ligne; // 10 - ligne car les lignes sont compté à l'envers sur un echiquier
				if ( !(ligne >= 0 && ligne <= 10) ) // Si la ligne est entre 0 et 10 
				{
					colonne = -1; 
					ligne = - 1;
				}
			}
				catch (NumberFormatException e) 
			{
					colonne = -1; 
					ligne = - 1;
					Log.mLog("mDechiffrePos : tryCatch - NumberFormatException");
			}
	
		}
		int[] ret = {ligne,colonne}; 
		return ret;
	}


	/**
	 * Demande en boucle a l'utilisateur une position ou bouger son pion
	 * L'uttilisateur peut aussi aller au menu sauvegarder aller au regles quitter ou annuler le pion selectionee
	 * Si le joueur deplace le Zen, cette methode le met a jour
	 * @param pMove le tableau contenant les deplacements possible du pion
	 * @return la coordonnee choisis par le pions
	 */
	public int[] mAskMove(int[][] pMove)
	{
		System.out.println("1) Menu \t2) Sauvegarder \t3) Regles \t4)Quitter \t 5) Annuler");
		System.out.println("Entrez un deplacement sous la forme \"A1\" , \"K11\" ");
		

		
		String ec;
		boolean exitLoop = false;
		int[] ret = new int[2];
		
		do
		{
			System.out.print("Bouger a la case: [");
			ec = sc.nextLine();		// ec pour entrée clavier		

			int act = mBoutonIg( ec, "ask Move"); // Dans le cas ou l'entrée clavier utilisateur ne serait pas une position, ni une erreur, alors il peut vouloir acceder à un des menu, sauvegarde ou autre
			if (act == 0 ) // Si l'action est retourner au menu ou annuler le pion selectioner
			{
				ret = mDechiffrePos(ec);
				
				if ( ret[0] != -1  ) // Si l'entree clavier est une reel position
				{
					for (int i = 0; i< 8; i++) // Pour chaqu'un des deplacement possible pour mon pion
					{
						if ( pMove[i][0] == ret[0] &&  pMove[i][1] == ret[1] ) // Si la position envoyer par l'uttilisateur fait partie des deplacement possible du pion
						{
							if ( pMove[i][2] == 1 ) // Si le deplacement est possible d'apres le tableau de deplacement
							{
								exitLoop = true; // Alors on peut sortir de la boucle (l'utilisateur a rentrer une position valide)
							}
							else if ( pMove[i][2] == 3 ) // Si le pion bouger est le zen
							{
								this.aZen.mRefreshZen(pMove[i][0], pMove[i][1], this.aNoTurn);
								exitLoop = true; // Alors on peut sortir de la boucle (l'utilisateur a rentrer une position valide)
							}
						}
					}
				}
			}
			else
			{
				exitLoop = true; // Le joueur veut annuler son move ou retourner au menu
				ret[0] = act;
				ret[1] = act;
			}
		}
		while ( !exitLoop ); // Continue de redemander tant que l'utilisateur n'entre pas un vrais deplacement

		
		return ret;
	}
	
	/**
	 * Une fois que le joueur a decider le pion a bouger et l'endrois ou le bouger,
	 * cette methode bouge ce pion de place, initialise le Zen dans le cas ou le joueur a bouger le Zen
	 * Met a jour le plateau de jeux
	 * @param pPionToMove les coordonee du pion qui bouge.
	 * @param pWhere les coordonee de deplacements.
	 */
	public void deplacerPion(int[] pPionToMove, int[] pWhere)
	{
		// Si le Zen viens d'etre dépalcer changer les valeurs du Zen
		if ( this.aPlateau[ pPionToMove[0] ][ pPionToMove[1] ]  == this.aZen.getZenColor() )
		{
			this.aZen.mRefreshZen(pWhere[0], pWhere[1], this.aNoTurn );
		}
		
		if (this.aPlateau[ pPionToMove[0] ][ pPionToMove[1] ] == aZen.getZenColor() ) // Si on se deplace sur le Zen, il meurt
		{
			aZen.setIsAlive(false);
		}
		
		Log.mLog("Le joueur veut bouger le pion qui est en position [" + pWhere[0] + "][" + pWhere[1] +"]" );
		Log.mLog("Le joueur a deplacer le pion jusqu'a la case [" + pPionToMove[0] + "][" + pPionToMove[1] +"]" );

		this.aPlateau[ pWhere[0] ][ pWhere[1] ] = this.aPlateau[ pPionToMove[0] ][ pPionToMove[1] ];	//Echanger le pion "pos" a la position "move" dans le plateau

		this.aPlateau[ pPionToMove[0] ][ pPionToMove[1] ] = 0;	// Le pion ayant bouger son ancienne position est maintenant à 0
				

	}

	public boolean mEndTurn() 
	{
		return false;
	}

	public boolean getLeaveGame()
	{
		return this.aLeaveGame;
	}
}