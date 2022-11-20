package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Controleur.MasterIHM;
import Modele.Utile;
import file.Load;

/**
 * Toutes ces méthodes sont statique et donc il n'y a pas d'interer a instancier cette classe.
 * Ces méthodes permettent de récuperer le JPanel correspondant à l'affichage du jeux.
 * @author Bernier Allan
 */
public class DrawGames 
{
	private static Color COLOR_DARK_GREY = new Color(45,45,45);
	
	
	/**
	 * Renvoie le JPanel correspondant à l'affichage d'une fin de partie
	 * @param pControleur Le controleur des boutons de cette page 
	 * @param pStrGrid La grille à afficher
	 * @param pRulesClicked attribut servant à connaitre l'etat d'affichage de "Rules" et "Saves"
	 * @param pWinner L'indice du gagnant
	 * @return Le JPanel correspondant à l'affichage d'une fin de partie
	 */
	public static JPanel getGameOverPanel(MasterIHM pControleur, String[][] pStrGrid, boolean pRulesClicked, int pWinner)
	{
		
		
		JPanel gamePan = new JPanel(new BorderLayout());
		JPanel gridPan =mDrawGridGameOver(pStrGrid);
		JPanel westPan = new JPanel(new BorderLayout() );
		JPanel bouton = mDrawButtons( pControleur, pRulesClicked);
		
		String winner;
		if ( pWinner == 1)
		{
			winner = "Blanc";
		}
		else if (pWinner == -1 )
		{
			winner = "Noir";
		}
		else 
		{
			winner = "Egalité";
		}
		JPanel toolGame = DrawGameOverToolGame( pControleur, winner );
		
		gamePan.add(gridPan, BorderLayout.CENTER);
		westPan.add(bouton, BorderLayout.NORTH);
		westPan.add(toolGame, BorderLayout.SOUTH);
		gamePan.add(westPan, BorderLayout.WEST);
		
		gamePan.setBackground(COLOR_DARK_GREY);
		westPan.setBackground(COLOR_DARK_GREY);
		gridPan.setBackground(COLOR_DARK_GREY);
		bouton.setBackground(COLOR_DARK_GREY);
		toolGame.setBackground(COLOR_DARK_GREY);

		
		return gamePan;
		
	}

	/**
	 * Affiche le jeux
	 * @param pControleur Le controleur des boutons de cette page 
	 * @param pStrGrid La grille à afficher
	 * @param pRulesClicked attribut servant à connaitre l'etat d'affichage de "Rules" et "Saves"
	 * @return Le JPanel correspondant à l'affichage du jeu
	 */
	public static JPanel getGamePanel(MasterIHM pControleur, String[][] pStrGrid, boolean pRulesClicked)
	{

		
		JPanel gamePan = new JPanel(new BorderLayout());
		JPanel gridPan = mDrawGrid( pStrGrid, pControleur);
		JPanel westPan = new JPanel(new BorderLayout() );
		JPanel bouton = mDrawButtons( pControleur, pRulesClicked);
		JPanel toolGame = mDrawToolGame( pControleur);
		
		gamePan.add(gridPan, BorderLayout.CENTER);
		westPan.add(bouton, BorderLayout.NORTH);
		westPan.add(toolGame, BorderLayout.SOUTH);
		gamePan.add(westPan, BorderLayout.WEST);
		
		gamePan.setBackground(COLOR_DARK_GREY);
		westPan.setBackground(COLOR_DARK_GREY);
		gridPan.setBackground(COLOR_DARK_GREY);
		bouton.setBackground(COLOR_DARK_GREY);
		toolGame.setBackground(COLOR_DARK_GREY);

		
		return gamePan;
	}
	
	/**
	 * Affiche les regles
	 * @param pControleur Le controleur des boutons de cette page 
	 * @param pStrGrid La grille à afficher
	 * @param pNoRule le numero de la regle a afficher
	 * @param pRulesClicked attribut servant à connaitre l'etat d'affichage de "Rules" et "Saves"
	 * @return Le JPanel correspondant à l'affichage des regles durant une partie
	 */
	public static JPanel getGamePanel_Rule(MasterIHM pControleur, String[][] pStrGrid, int pNoRule, boolean pRulesClicked)
	{

		
		JPanel gamePan = new JPanel(new BorderLayout());
		JPanel gridPan = mDrawGrid( pStrGrid, pControleur);
		JPanel westPan = new JPanel(new BorderLayout() );
		JPanel bouton = mDrawButtons( pControleur, pRulesClicked);
		
		JPanel toolGame = mDrawRules(pControleur, pNoRule);
		
		gamePan.add(gridPan, BorderLayout.CENTER);
		westPan.add(bouton, BorderLayout.NORTH);
		westPan.add(toolGame, BorderLayout.CENTER);
		gamePan.add(westPan, BorderLayout.WEST);
		
		gamePan.setBackground(COLOR_DARK_GREY);
		westPan.setBackground(COLOR_DARK_GREY);
		gridPan.setBackground(COLOR_DARK_GREY);
		bouton.setBackground(COLOR_DARK_GREY);
		toolGame.setBackground(COLOR_DARK_GREY);

		
		return gamePan;
	}
	
	/**
	 * Affiche les sauvegarde
	 * @param pControleur Le controleur des boutons de cette page 
	 * @param pStrGrid La grille à afficher
	 * @param pNoRule le numero de la sauvegarde a afficher
	 * @param pRulesClicked attribut servant à connaitre l'etat d'affichage de "Rules" et "Saves"
	 * @return Le JPanel correspondant à l'affichage des sauvegarde durant une partie
	 */
	public static JPanel getGamePanel_Save(MasterIHM pControleur, String[][] pStrGrid, int pNoSave, boolean pRulesClicked)
	{
		
		JPanel gamePan = new JPanel(new BorderLayout());
		JPanel gridPan = mDrawGrid( pStrGrid, pControleur);
		JPanel westPan = new JPanel(new BorderLayout() );
		JPanel bouton = mDrawButtons( pControleur, pRulesClicked);
		
		JPanel toolGame = mDrawSave(pControleur, pNoSave);
		
		gamePan.add(gridPan, BorderLayout.CENTER);
		westPan.add(bouton, BorderLayout.NORTH);
		westPan.add(toolGame, BorderLayout.CENTER);
		gamePan.add(westPan, BorderLayout.WEST);
		
		gamePan.setBackground(COLOR_DARK_GREY);
		westPan.setBackground(COLOR_DARK_GREY);
		gridPan.setBackground(COLOR_DARK_GREY);
		bouton.setBackground(COLOR_DARK_GREY);
		toolGame.setBackground(COLOR_DARK_GREY);

		
		return gamePan;
	}

	/**
	 * Affiche une grille de jeux
	 * @param pStrGrid La grille à afficher
	 * @param pControleur Le controleur des boutons de cette page 
	 * @return Le JPanel correspondant a une grille afficher
	 */
	private static JPanel mDrawGrid(String[][] pStrGrid, MasterIHM pControleur) 
	{
		imagePanel[][] imgGrid = new imagePanel[11][11];
		JPanel grid = new JPanel( new GridLayout(11,11) );
		
		for (int l = 0; l < 11; l++)
		{
			for (int c = 0; c < 11; c++)
			{
				imgGrid[l][c] = new imagePanel(pStrGrid[l][c]);
				grid.add( imgGrid[l][c] ); 
				
				int line = l;
				int column = c;
				
				imgGrid[l][c].addMouseListener( new java.awt.event.MouseAdapter()
				{
			         @Override
			         public void mouseReleased(java.awt.event.MouseEvent evt) 
			         {
			        	 pControleur.mCaseClicked(line, column);
			         }
				});
			}
		}
		
		return grid;
	}
	
	/**
	 * Affiche une grille de jeux lors d'une fin de partie
	 * @param pStrGrid La grille à afficher
	 * @return Le JPanel correspondant a une grille de fin de partie 
	 */
	private static JPanel mDrawGridGameOver(String[][] pStrGrid) 
	{
		
		imagePanel[][] imgGrid = new imagePanel[11][11];
		JPanel grid = new JPanel( new GridLayout(11,11) );
		
		for (int l = 0; l < 11; l++)
		{
			for (int c = 0; c < 11; c++)
			{
				imgGrid[l][c] = new imagePanel(pStrGrid[l][c]);
				grid.add( imgGrid[l][c] ); 
			}
		}
		
		return grid;
	}

	/**
	 * 	Affiche les boutons Acceuil, Regles, Sauvegarde
	 * @param pControleur Le controleur des boutons de cette page 
	 * @param pToolClicked sert a savoir si le prochain clique sur un bouton dois afficher ou enlever ce qui est déja présent
	 * @return Le JPanel correspondant à cette affichage
	 */
	private static JPanel mDrawButtons( MasterIHM pControleur , boolean pToolClicked)
	{
		
		JPanel buttons = new JPanel(new GridLayout (4,1,10,10) );
		
		Button bLobby = new Button("Accueil", 300,40, 20);
		Button bRules = new Button("Regles", 300,40, 20);
		Button bSave  = new Button("Sauvegarde", 300,40, 20);
		
		buttons.add(bLobby);
		buttons.add(bRules);
		buttons.add(bSave);
		
		bLobby.addMouseListener( new java.awt.event.MouseAdapter()
		{
	         @Override
	         public void mouseReleased(java.awt.event.MouseEvent evt) 
	         {
	        	 pControleur.mGame_Hub();
	         }
		});
		
		bRules.addMouseListener( new java.awt.event.MouseAdapter()
		{
	         @Override
	         public void mouseReleased(java.awt.event.MouseEvent evt) 
	         {
	        	 if ( !pToolClicked)
	        	 {
	        		 pControleur.mGame_Rules(0);
	        	 }
	        	 else
	        	 {
	        		 pControleur.mDrawGame();
	        	 }
	         }
		});
		
		bSave.addMouseListener( new java.awt.event.MouseAdapter()
		{ 
	         @Override
	         public void mouseReleased(java.awt.event.MouseEvent evt) 
	         {
	        	 if ( !pToolClicked)
	        	 {
	        		 pControleur.mGame_Save(0);
	        	 }
	        	 else
	        	 {
	        		 pControleur.mDrawGame();
	        	 }	         
	        }
		});
		
		return buttons;
	}
	
	/**
	 * Affiche la couleur du joueur qui dois jouer se tour.
	 * @param pControleur Le controleur des boutons de cette page 
	 * @return Le JPanel correspondant à cette affichage
	 */
	private static JPanel mDrawToolGame( MasterIHM pControleur )
	{
		JPanel toolGame = new JPanel( new GridLayout(2,1,0,50) );
		toolGame.add(new Button("C'est au tour du joueur : " , 280,100, 20), BorderLayout.NORTH);
		toolGame.add(new Button(pControleur.mGetCurrentColor() , 280,100, 20), BorderLayout.CENTER);
		
		return toolGame;
	}
	
	/**
	 * Affiche les regles durant la partie, il y a deux boutons suivant et precedent pour changer de regle
	 * @param pControleur Le controleur des boutons de cette page 
	 * @param pNoRule le numero de la regle a afficher
	 * @return Le JPanel correspondant à cette affichage
	 */
	private static JPanel mDrawRules(MasterIHM pControleur, int pNoRule)
	{
		JPanel rules = new JPanel( new BorderLayout() );
		
		JPanel boutons = new JPanel( new GridLayout(1,2,10,10) );
			boutons.setBackground(COLOR_DARK_GREY);
		
			Button bNext = new Button("Suivante", 120,40, 20);
			Button bPrevious = new Button("Precedente", 120,40, 20);	
			boutons.add(bNext);
			boutons.add(bPrevious);
			
			bNext.addMouseListener( new java.awt.event.MouseAdapter()
			{
		         @Override
		         public void mouseReleased(java.awt.event.MouseEvent evt) 
		         {
		        	 pControleur.mGame_Rules(pNoRule + 1);
		         }
			});
			
			bPrevious.addMouseListener( new java.awt.event.MouseAdapter()
			{
		         @Override
		         public void mouseReleased(java.awt.event.MouseEvent evt) 
		         {
		        	 pControleur.mGame_Rules(pNoRule - 1);
		         }
			});
			
	
		rules.add(boutons, BorderLayout.NORTH);
		
			JLabel labelRule = new JLabel(Utile.mGetRulesJLabel(pNoRule));
			labelRule.setForeground(new Color(255,255,255));
			labelRule.setFont(new Font("Arial", Font.ITALIC, 15));
			
		rules.add(labelRule, BorderLayout.CENTER);
			
		return rules;
	}
	
	
	/**
	 * Affiche les sauvegardes durant la partie, il y a trois boutons suivant, sauvegarder et precedent pour changer de sauvegarde et sauvegarder
	 * @param pControleur Le controleur des boutons de cette page 
	 * @param pNoSave le numero de la sauvegarde a afficher
	 * @return Le JPanel correspondant à cette affichage
	 */
	private static JPanel mDrawSave(MasterIHM pControleur, int pNoSave )
	{
		JPanel save = new JPanel( new BorderLayout() );
		

		
		JPanel boutons = new JPanel( new GridLayout(1,3) );
		
			Button bNext = new Button("Suivante", 90,40, 13);
			Button pSave = new Button("Sauvegarder", 90,40, 13);
			Button bPrevious = new Button("Precedente", 90,40, 13);
			boutons.add(bPrevious);
			boutons.add(pSave);
			boutons.add(bNext);

			bNext.addMouseListener( new java.awt.event.MouseAdapter()
			{
		         @Override
		         public void mouseReleased(java.awt.event.MouseEvent evt) 
		         {
		        	 pControleur.mGame_Save(pNoSave + 1);
		         }
			});
			
			bPrevious.addMouseListener( new java.awt.event.MouseAdapter()
			{
		         @Override
		         public void mouseReleased(java.awt.event.MouseEvent evt) 
		         {
		        	 pControleur.mGame_Save(pNoSave - 1);
		         }
			});
			
			pSave.addMouseListener( new java.awt.event.MouseAdapter()
			{
		         @Override
		         public void mouseReleased(java.awt.event.MouseEvent evt) 
		         {
		        	 pControleur.mSaveCurrentGame( pNoSave);
		        	 pControleur.mGame_Save(pNoSave);
		         }
			});
	
			save.add(boutons, BorderLayout.NORTH);
		
		JPanel infoSave = new JPanel( new BorderLayout() );
		
		JPanel backGroundLabel = new JPanel();
		JLabel lNoSave = new JLabel("Sauvegarde numero : " + pNoSave);
		
		
		backGroundLabel.setBackground(COLOR_DARK_GREY);
		lNoSave.setForeground(new Color(255,255,255));
		lNoSave.setFont(new Font("Arial", Font.ITALIC, 15));
		
		backGroundLabel.add(lNoSave);
		infoSave.add(backGroundLabel, BorderLayout.NORTH);
		
		JPanel grid = new JPanel( new GridLayout(11,11) );
		imagePanel[][] imgGrid = new imagePanel[11][11];
		
		Load myLoad = new Load(pNoSave);
		String[][] strGrid =  Utile.PlateauToString( myLoad.getPlateau() );

		
		for (int l = 0; l < 11; l++)
		{
			for (int c = 0; c < 11; c++)
			{
				imgGrid[l][c] = new imagePanel(strGrid[l][c]);
				grid.add( imgGrid[l][c] ); 
			}
		}
		
		infoSave.add(grid, BorderLayout.CENTER);
		
		save.add(infoSave,BorderLayout.CENTER);
		
		return save;
	}
	
	/**
	 * Affiche le gagnant de la partie, place un boutton rejouer et son action listener.
	 * @param pControleur le controleur ayant la méthode de l'action listener
	 * @param pWinner l'indice du gagnant
	 * @return
	 */
	private static JPanel DrawGameOverToolGame(MasterIHM pControleur, String pWinner )
	{
		JPanel panGameOver = new JPanel( new GridLayout(3,1) );
		
		panGameOver.add(new Button("Le gagnant est le joueur : " , 280,100, 20));
		panGameOver.add(new Button(pWinner , 280,100, 20));
		
		Button bRestart = new Button("Rejouer" , 280,100, 20);
		panGameOver.add(bRestart);

		
		bRestart.addMouseListener( new java.awt.event.MouseAdapter()
		{
	         @Override
	         public void mouseReleased(java.awt.event.MouseEvent evt) 
	         {
	        	 pControleur.mGame_Restart();
	         }
		});
		
		return panGameOver;
	}
	
}
