package Controleur;

import java.util.Scanner;

import Modele.GameOver;
import Modele.IPlayable;
import Modele.Mode;
import Modele.PlayerCMD;
import Modele.PlayerIA;
import Modele.Utile;
import Modele.Zen;
import file.Load;
import file.Log;

/**
 * Cette classe contient tout le controleur et la vue de la partie console.
 * @author badga
 */
public class MasterCMD extends Master{
	private Scanner sc;			// Interaction uttilisateur 

	
	/**
	 * Initialise le Scanner pour les entree clavier
	 * Instencie un TerminalUtile.
	 */
	public MasterCMD()
	{
		super();
		this.sc = new Scanner(System.in);
	}
	
	/**
	 * Initialise les attribut d'une partie en fonction du mode 
	 * @param pMode le mode de jeux avec 1/Joue en Player vs IA, 2/ Joue en Player vs Player 3/ Joue en 2 Player vs 2 Player
	 * 
	 */
	public void mNewGame(Mode pMode)
	{
		this.aPlateau = Utile.mInitPlateau();
		this.aZen = new Zen();
		this.aNoTurn = 0;
		this.aMode = pMode;
		
		
		switch(pMode)
        {
	        case PvP: //Joue en Player vs Player
	        	aWhite = new PlayerCMD(1, this.aZen, pMode);
	        	aBlack = new PlayerCMD(-1, this.aZen, pMode);
	        break;
	        case PvIA: //Joue en Player vs IA
	        	aWhite = new PlayerCMD(1, this.aZen, pMode);
	        	aBlack = new PlayerIA(-1, this.aZen );
	        break;
	        case PvP4Joueurs: // Joue en 2 Player vs 2 Player

	        	aWhite = new PlayerCMD(1, this.aZen, pMode);
	        	aBlack = new PlayerCMD(-1, this.aZen, pMode);
	        break;
        }

		this.aCurrent = mChoiceColor();		

	}

	/**
	 * Charge tout les attribut de cette classe grace a la sauvegarde a l'indice actualSave puis lance la partie.
	 * @param pSave, l'indice de sauvegarde
	 */
	public void loadGame(int pSave)
	{
		Log.mLog("Le joueur a charger la sauvegarde n°" + pSave);
		Load game = new Load(pSave);

		this.aZen = game.getZen();			// instance de Zen contenant toutes les information utile sur lui 
		this.aPlateau = game.getPlateau(); 	// Avancé du plateau de jeu
		this.aNoTurn = game.getNoTurn(); 		// Numero du tour de jeu
		int turnToPlay = game.getNextPlayer(); 	// Indice du joueur qui dois jouer ( -1 = NOIR ou 1 = BLANC )
		this.aMode = game.getMode();
		
		
		switch(this.aMode)
        {
	        case PvP: //Joue en Player vs Player
	        	aWhite = new PlayerCMD(1, this.aZen, this.aMode);
	        	aBlack = new PlayerCMD(-1, this.aZen, this.aMode);
	        break;
	        case PvIA: //Joue en Player vs IA
	        	aWhite = new PlayerCMD(1, this.aZen, this.aMode);
	        	aBlack = new PlayerIA(-1, this.aZen );

	        break;
	        case PvP4Joueurs: // Joue en 2 Player vs 2 Player

	        	aWhite = new PlayerCMD(1, this.aZen, this.aMode);
	        	aBlack = new PlayerCMD(-1, this.aZen, this.aMode);

	        
	        break;
        }
		
		if ( turnToPlay == 1 )
		{
			this.aCurrent = this.aWhite;
		}
		else
		{
			this.aCurrent = this.aBlack;
		}
		
		mStart();
		
	}
	
	/**
	 *	Demande au joueur de choisir une couleur entre eux entre noir et blanc 
	 *	Math.random leurs désigne quel joueur commence 
	 * @return le joueur qui commence 
	 */
	public IPlayable mChoiceColor()
	{
		System.out.println("Choissez entre vous qui est le joueur Blanc et qui est le joueur noir ");
		System.out.println("appuyer sur [entrer] ");
		sc.nextLine();				
		
		System.out.println("1...");
		System.out.println("2...");
		System.out.println("3...");
        
		
		IPlayable playerStart = null;
		int start = (int)(Math.random() * 2) + 1; 
        
        if (start == 1 )
        {
       	System.out.println("Blanc commence !");
       	playerStart = this.aWhite;
        }
        else
        {
    		System.out.println("Noir commence !");
    		playerStart = this.aBlack;
        }
        
		System.out.println("appuyer sur [entrer] ");
		sc.nextLine();
		
		return playerStart;
	}

	/**
	 * Lance le menu puis en retour lance une fenetre comprise entre 1 et 6 
	 * corespondant aux differentes fenetre possible a afficher en fonction du choix utilisateur
	 */
	public void mMenu()
	{
		int choix = -1;
		do 
		{
			System.out.println("1: Player Vs Player");
			System.out.println("2: Player vs IA");
			System.out.println("3: 2 Player Vs 2 Player");
			System.out.println("4: Regles");
			System.out.println("5: Sauvegardes");
			System.out.println("6: Quitter");
			System.out.println("7: Credits ");
			
			try
			{
				choix = Integer.parseInt( sc.nextLine() );				
			}
			catch (NumberFormatException e)
			{
				Log.mLog("mMenu : tryCatch - NumberFormatException");
			}				
		}while ( choix <= 0 || choix > 7);
		
		mRunMenu( choix , 8); // Lance le menu correspondant (8 etant le menu
	}
	
	/**
	 * Lance les differents elements graphique que le joueur voudrais afficher
	 * @param pFrame Le menu a lancer
	 * @param pFrom	Le lieux d'appel de la fonction
	 */
	public void mRunMenu(int pFrame, int pFrom)
	{
		if (pFrame > 0 && pFrame <= 8)
		{
			switch(pFrame)
	        {
	            case 1:
	            	Log.mLog("Menu --- Choix > Player Vs Player");
	        		System.out.println("== Player Vs Player == ");
	        		mNewGame(Mode.PvP);
	        		mStart();
	            break;
	            
	            case 2:
	            	Log.mLog("Menu --- Choix > Player vs IA");
	        		System.out.println("== Player vs IA == ");
	        		mNewGame(Mode.PvIA);
	        		mStart();
	            break;
	            
	            case 3:
	            	Log.mLog("Menu --- Choix > 2 Player Vs 2 Player");
	        		System.out.println("== 2 Player Vs 2 Player == ");
	        		mNewGame(Mode.PvP4Joueurs);
	        		mStart();
	        	break;
	        	
	            case 4:
	            	Log.mLog("Menu --- Choix > Regles");
	        		System.out.println("== Regles == ");
	        		Utile.mRules();
	            break;
	            
	            case 5:
	            	Log.mLog("Menu --- Choix > Sauvegardes");
	        		System.out.println("== Sauvegardes == ");
	        		mSaves();
	            break;
	            
	            case 6:
	            	Log.mLog("Fin du jeux : le programme s'est terminé sans problèmes");
	        		System.out.println("== Quitter == ");
					System.exit(0); 
	            break;
	            
	            case 7:
	            	Log.mLog("Ouverture du menu Credits");
	        		System.out.println("== Credits == ");
	        		Utile.mCredits();
	            break;
	        }
		}
	}
	
	/**
	 * Lance une partie (en player vs player) 
	 * fait jouer chaqu'un des joueur present dans la partie chaqu'un leur tour en appelant
	 * la methode play de player puis appel la methode newTurn() pour changer de tour de jeu.
	 */
	public void mStart()
	{
		boolean leaveGame = false;
		boolean whiteWin;
		boolean blackWin;
		
		do
		{	
			this.aCurrent.mPlay(aZen, this.aPlateau, this.aNoTurn);

			if ( this.aCurrent.getClass() != this.aIa.getClass())
			{
				leaveGame = this.aCurrent.getLeaveGame();
			}
			
			mNewTurn();

			if ( this.aMode == Mode.PvIA )
			{
				if (this.aCurrent.getClass() == this.aIa.getClass())
				{

					mIaTurn();

				}
			}
			
			whiteWin = GameOver.mIsOver(this.aWhite.getColor() , this.aZen, this.aPlateau);
			blackWin = GameOver.mIsOver(this.aBlack.getColor() , this.aZen, this.aPlateau);
		}while( (!whiteWin && !blackWin ) && !leaveGame );
	}

	/** 
	 * Cette methode fonctionne de la même facon que mRules,
	 * Elle affiche la sauvegarde à l'indice i et le joueur peut choisir:
	 * De continuer la partie
	 * De supprimer la sauvegarde
	 * De retourner au menu
	 * De voir la sauvegarde suivante
	 * De voir la sauvegarde precedante
	 */
	public void mSaves()
	{
		String[] save = new String[5];
		int actualSave = 0;
		int choix = -1;

		do 
		{
			// Si le joueur clique sur soivant ou precedant on change l'indice
			if (choix == 1) 
			{
				actualSave --;
			}
			else if ( choix == 3)
			{
				actualSave ++;
			}
			
			// Si l'indice dépasse le nombre de sauvegarde ou est inférieur a 0 alors on l'inverse
			if (actualSave >= save.length )
			{
				actualSave = 0; 
			}
			else if (actualSave < 0)
			{
				actualSave = save.length - 1 ; 
			}
			
			save[actualSave] = Utile.mSaveToString(actualSave);

			System.out.println("Sauvegarde no:" + actualSave + "\n" + save[actualSave] + "\n\n" + " 1: Precedent \t 2: Retour \t 3: Suivant \t 4: supprimer \t 5: continuer");

			// Si le joueur ne rentre pas un nombre, on evite de crash.
			try
			{
				choix = Integer.parseInt( sc.nextLine() );				
			}
			catch (NumberFormatException e){
				Log.mLog("mSaves : tryCatch - NumberFormatException");
			} 
			
		}while ( choix != 2 && choix != 5); // Si le joueur veux faire retour OU jouer la sauvegarde, alors on sort de la boucle
				
		// Si le choix est egale a 2, la fonction s'arette et retourne a son lieux d'appel	
		
		// Si le choix est egale a 5, on charge la partie
		if (choix == 5)
		{
			loadGame(actualSave);
		}
	}

	/** 
	 * Affiche le plateau actuel de jeu
	 */
	public void mDrawGame()
	{
		Utile.mAfficherPlateau(this.aPlateau);
	}
	
	/** 
	 * Affiche le plateau actuel de jeu avec les deplacements
	 * @param pMove les deplacements
	 * @param pPion le pion selecitonner
	 */
	public void mDrawGameSelected(int[][] pMove, int pPion[])
	{
		Utile.mAfficherPlateau(this.aPlateau, pMove, pPion);
	}
	
}
