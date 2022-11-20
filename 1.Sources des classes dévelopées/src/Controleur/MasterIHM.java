package Controleur;

import Modele.GameOver;
import Modele.Mode;
import Modele.Move;
import Modele.PlayerIA;
import Modele.PlayerIHM;
import Modele.Utile;
import Modele.Zen;
import Vue.FrameManager;
import file.Load;
import file.Log;
import file.Save;
/**
 * Cette classe correspond au modele du mode graphique, contient tout les listener du chaqu'un des boutons disponible en jeu.
 * @author Bernier Allan
 *
 */
public class MasterIHM extends Master{
	
	private FrameManager aVue; // Graphique
	
	/**
	 * Dans le cas ou je commence un nouvelle partie, utiliser ce constructeur
	 * @param pVue le graphique 
	 * @param pMode le mode de jeu
	 */
	public MasterIHM(FrameManager pVue)
	{
		super();
		
		if (pVue != null)
		{
			this.aVue = pVue;
		}
		else
		{
			Log.mLog("Error : Game -> Constructeur erreur de parametres");
		}
	}
	
	/**
	 * Initialise les attribut d'une partie en fonction du mode 
	 * @param pMode le mode de jeux avec 1/Joue en Player vs IA, 2/ Joue en Player vs Player 3/ Joue en 2 Player vs 2 Player
	 * 
	 */
	public void mNewGame(Mode pMode)
	{
		if ( pMode == Mode.PvIA || pMode == Mode.PvP || pMode == Mode.PvP4Joueurs )
		{
			this.aMode = pMode;
			this.aPlateau = Utile.mInitPlateau();
			this.aZen = new Zen();
			this.aNoTurn = 0;
			switch(pMode)
	        {
		        case PvP: //Joue en Player vs IA
		        	aWhite = new PlayerIHM(1, aZen);
		        	aBlack = new PlayerIHM(-1, aZen);
		        break;
		        case PvIA: //Joue en Player vs Player
		        	aWhite = new PlayerIHM(1, aZen);
		        	aBlack = new PlayerIA(-1, aZen);
	
		        break;
		        case PvP4Joueurs: // Joue en 2 Player vs 2 Player
	
		        	aWhite = new PlayerIHM(1, aZen);
		        	aBlack = new PlayerIHM(-1, aZen);
		        break;
	        }
			
	    	this.aCurrent = aWhite;	
			mDrawGame();

		}
		else
		{
			Log.mLog("Error : Game -> mNewGame(Mode pMode) erreur de parametres");
		}
	}
	
	/**
	 * Initialise les attribut pour reprendre une partie en cour
	 * @param pMode le mode de jeu PvP PvIA ou PvP4Joueurs
	 * @param pPlateau le plateau de jeu
	 * @param pZen le Zen
	 * @param pNoTurn le Numero corespondant au tour de jeu
	 * @param pTurnToPlay Le prochain joueur qui joue.
	 */
	public void mLoadGame(int pSave)
	{
		Load load = new Load(pSave);
		
			this.aMode = load.getMode();
			this.aPlateau = load.getPlateau();
			this.aZen = load.getZen();
			this.aNoTurn = load.getNoTurn();
			
			
			switch( this.aMode )
	        {
		        case PvP: //Joue en Player vs IA
		        	this.aWhite = new PlayerIHM(1, this.aZen);
		        	this.aBlack = new PlayerIHM(-1, this.aZen);
		        break;
		        case PvIA: //Joue en Player vs Player
		        	this.aWhite = new PlayerIHM(1, this.aZen);
		        	this.aBlack = new PlayerIA(-1, this.aZen);
	
		        break;
		        case PvP4Joueurs: // Joue en 2 Player vs 2 Player
	
		        	this.aWhite = new PlayerIHM(1, this.aZen);
		        	this.aBlack = new PlayerIHM(-1, this.aZen);
		        break;
	        }
			
	    	if (load.getNextPlayer() == 1)
	    	{
	    		this.aCurrent = this.aWhite;	
	    	}
	    	else
	    	{
	    		this.aCurrent = this.aBlack;
	    	}
	    			
			mDrawGame();

	}
		
	/**
	 * Effectue le tour du joueur Humain actuel puis, si le mode est player vs ia, alors effectue le tour de l'ia.
	 * Puis avant de finir le tour, verifit que la partie n'est pas finis, si elle est finis, alors affiche l'ecran game over.
	 * @param pLine La ligne cliquer
	 * @param pColumn La column cliquer
	 */
	public void mCaseClicked(int pLine ,int pColumn)
	{
		if ( pLine >= 0 && pLine < 11 && pColumn >= 0 && pColumn < 11)
		{			
			Log.mLog("La case cliqué est : [" + pLine + "][" + pColumn + "]");
	
			// Verifier que le joueur courrant est un Humain
			// Recuperer le dynamique de aCurrent puis terter qu'il soit un newProject.PlayerHumanGraphique
	
			mHumanTurn(pLine, pColumn);
			
			if ( this.aMode == Mode.PvIA )
			{
				if (this.aCurrent.getClass() == this.aIa.getClass())
				{
					mIsWin();
					mIaTurn();
				}
			}
			
			mIsWin();
			
		}
		else
		{
			Log.mLog("Error : Game -> mCaseClicked(int pLine ,int pColumn) erreur de parametres");
		}
	}
	
	/**
	 * Verifit si quelqu'un a gagner, si oui affiche l'ecran de fin de partie.
	 */
	public void mIsWin()
	{
		boolean whiteWin = GameOver.mIsOver(this.aWhite.getColor() , this.aZen, this.aPlateau);
		boolean blackWin = GameOver.mIsOver(this.aBlack.getColor() , this.aZen, this.aPlateau);
		if ( whiteWin || blackWin )
		{
			Log.mLog("Le joueur blanc a : " + (whiteWin ? "Gagner" : "Perdu" ));
			Log.mLog("Le joueur Noir a : " + (blackWin ? "Gagner" : "Perdu" ));
			
			int winner = 0;
			if (whiteWin && blackWin)
			{
				winner = 2;
			}
			else if ( whiteWin)
			{
				winner = 1;
			}
			else if ( blackWin)
			{
				winner = -1;
			}
			this.aVue.mDrawGameOver(Utile.PlateauToString(aPlateau, winner), winner);
		}
	}
	
	/**
	 * Joue le tour d'un humain.
	 * @param pLine ligne cliquer.
	 * @param pColumn colonne cliquer.
	 */
	public void mHumanTurn(int pLine, int pColumn)
	{
		if ( pLine >= 0 && pLine < 11 && pColumn >= 0 && pColumn < 11)
		{	
			PlayerIHM human = new PlayerIHM(0, null);
			
			if ( aCurrent.getClass() == human.getClass())
			{
				aCurrent.isClicked(pLine, pColumn);
				aCurrent.mPlay(aZen, aPlateau,this.aNoTurn );
				
				
				if (this.aCurrent.getSelected() )
				{
					
					int[] pion = aCurrent.getPion();
		
					if ( !this.aCurrent.getEndTurn() )
					{
						int[][] move = Move.getMove(this.aPlateau, pion[0], pion[1], this.aCurrent.getColor());
						
						if( this.aPlateau[ pion[0] ][ pion[1] ] == this.aZen.getZenColor() ) // Si le deplacement concerne le Zen
						{
							move = this.aZen.mZenMove( this.aPlateau , move , this.aNoTurn);
						}
						
						mDrawGame( move, pion );
					}
					else 
					{
						
						int[] where = aCurrent.getWhere();
						
						this.aPlateau[ where[0] ][ where[1] ] = this.aPlateau[ pion[0] ][ pion[1] ]; // On bouge le pion 
						this.aPlateau[ pion[0] ][ pion[1] ] = 0; // L'ancienne case du pion est vide.

						if( this.aPlateau[ where[0] ][ where[1] ] == this.aZen.getZenColor() ) // Si le deplacement concerne le Zen
						{
							this.aZen.mRefreshZen(where[0], where[1], this.aNoTurn );
						}
						
						Log.mLog("Deplacement du pion " + ( (this.aCurrent.getColor() == 1) ? "Blanc" : "Noir") + " de la case [" + pion[0] + "][" + pion[1] + "] à [" + where[0] + "][" + where[1] + "]" );
						this.aCurrent.setNewTurn();
						mNewTurn();
						mDrawGame();
					}
				}
				else
				{
					mDrawGame();
				}
			}
		}
		else
		{
			Log.mLog("Error : Game -> mHumanTurn(int pLine, int pColumn) erreur de parametres");
		}
	}
		
	/**
	 * Sauvegarde la partie actuelle dans le fichier correspondant au parametre envoyer.
	 * @param pSave valeur comprise entre 0 et 4.
	 */
	public void mSave(int pSave)
	{
		int noSave = pSave - 48;
		if (noSave >= 0 && noSave < 5)
		{		
			Save.mSaveGame(this.aPlateau, this.aMode, this.aZen, this.aCurrent.getColor() , this.aNoTurn, noSave);
		}
		else
		{
			Log.mLog("Error : Game -> mSave(int pSave) erreur de parametres");
		}
	}

	/** 
	 * Affiche le plateau actuel de jeu
	 */
	public void mDrawGame()
	{
		this.aVue.mDrawGame( Utile.PlateauToString( this.aPlateau ) );
	}
	
	/** 
	 * Affiche le plateau actuel de jeu avec les deplacements
	 * @param pMove les deplacements
	 * @param pPion le pion selecitonner
	 */
	public void mDrawGame(int[][] pMove, int pPion[])
	{
		this.aVue.mDrawGame( Utile.PlateauToString( this.aPlateau, pMove , pPion) );
	}
	
	// ========== Hub ========

	/**
	 * Gère l'appuis du bouton player vs player lorceque l'on est au menu;
	 */
	public void mMenu_PlayerVsPlayer()
	{
		mNewGame(Mode.PvP);
		mDrawGame();
	}

	/**
	 * Gère l'appuis du bouton player vs IA lorceque l'on est au menu;
	 */
	public void mMenu_PlayerVsIA()
	{
		mNewGame(Mode.PvIA);
		mDrawGame();
	}
	
	/**
	 * Gère l'appuis du bouton 2 player vs 2 player lorceque l'on est au menu;
	 */
	public void mMenu_2PlayersVs2Players()
	{
		mNewGame(Mode.PvP4Joueurs);
		mDrawGame();
	}
	
	/**
	 * Gère l'appuis du sauvegarde lorceque l'on est au menu;
	 */
	public void mMenu_Saves()
	{
		this.aVue.mDrawSave(0);
	}
	
	
	/**
	 * Gère l'appuis du bouton regles lorceque l'on est au menu;
	 */
	public void mMenu_Rules()
	{
		this.aVue.mDrawRules();

	}
	
	/**
	 * Gère l'appuis du bouton credits lorceque l'on est au menu;
	 */
	public void mMenu_Credits()
	{
		this.aVue.mDrawCredits();
	}
	
	/**
	 * Gère l'appuis du bouton quitter lorceque l'on est au menu;
	 */
	public void mMenu_Quitter()
	{
		System.exit(0);
	}
	
	// ========== Saves ========
	
	/**
	 * Gère l'appuis du bouton retour lorceque l'on est au sauvegarde;
	 */
	public void mSaves_Back()
	{
		this.aVue.mDrawHub();
	}
	
	/**
	 * Gère l'appuis du bouton suivant lorceque l'on est au sauvegarde;
	 */
	public void mSaves_Next(int pSave)
	{
		pSave ++;
		if ( pSave < 0 )
		{
			pSave = 4;
		}
		else if (pSave > 4)
		{
			pSave = 0;
		}
		this.aVue.mDrawSave(pSave);
	}
	
	/**
	 * Gère l'appuis du bouton précédent lorceque l'on est au sauvegarde;
	 */
	public void mSaves_Previous(int pSave)
	{
		pSave --;
		if ( pSave < 0 )
		{
			pSave = 4;
		}
		else if (pSave > 4)
		{
			pSave = 0;
		}
		this.aVue.mDrawSave(pSave);
	}
	
	/**
	 * Gère l'appuis du bouton supprimer lorceque l'on est au sauvegarde;
	 */
	public void mSaves_Delete(int pSave)
	{
		Save.mSaveGame(pSave);
		this.aVue.mDrawSave(pSave);
	}
	
	/**
	 * Gère l'appuis du bouton continuer lorceque l'on est au sauvegarde;
	 */
	public void mSaves_Continue(int pSave)
	{
		mLoadGame( pSave );
		mDrawGame();
	}
	
	// ========== Credits ========
	
	/**
	 * Gère l'appuis du bouton retour lorceque l'on est au crédits;
	 */
	public void mCredits_Back()
	{
		this.aVue.mDrawHub();
	}
	
	// ========== Rules ========
	
	/**
	 * Gère l'appuis du bouton retour lorceque l'on est au regles;
	 */
	public void mRules_Back()
	{
		this.aVue.mDrawHub();
	}
	
	// ========== Game ========

	/**
	 * Gère l'appuis du bouton retour lorceque l'on est en jeu;
	 */
	public void mGame_Hub()
	{
		this.aVue.mDrawHub();
	}
	
	/**
	 * Gère l'appuis du bouton regles lorceque l'on est en jeu;
	 * @param pRule, le numero de la regle a afficher
	 */
	public void mGame_Rules(int pRule)
	{
		int nbRules = 9; // Il y a actuellement 9 regles
		
		if ( pRule < 0 )
		{
			pRule = nbRules -1;
		}
		else if ( pRule >= nbRules )
		{
			pRule = 0;
		}	
		
		this.aVue.mDrawGameRules(Utile.PlateauToString( this.aPlateau ), pRule);
		
	}
		
	/**
	 * Gère l'appuis du bouton Regles lorceque l'on est en jeu;
	 * @param pSave, le numero de la sauvegarde a afficher
	 */
	public void mGame_Save(int pSave)
	{
		int nbSave = 5; // Il y a 5 place de sauvegarde
		
		if ( pSave < 0 )
		{
			pSave = nbSave -1;
		}
		else if ( pSave >= nbSave )
		{
			pSave = 0;
		}
		
		this.aVue.mDrawGameSave(Utile.PlateauToString( this.aPlateau ), pSave);
		
	}
	
	/**
	 * Gère l'appuis du bouton sauvegarder lorceque l'on est en jeu;
	 * @param pSave, le numero de la sauvegarde a ecraser
	 */
	public void mSaveCurrentGame(int pSave)
	{
		Save.mSaveGame(this.aPlateau, this.aMode, this.aZen, this.aCurrent.getColor() , this.aNoTurn, pSave);
	}

	/** 
	 * Gere l'appuis du bouton restart lorceque l'on a finis la partie.
	 */
	public void mGame_Restart() { // TODO
		mNewGame(this.aMode);
		mDrawGame();
	}
	
}






