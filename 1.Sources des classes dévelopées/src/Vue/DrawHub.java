package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import Controleur.MasterIHM;
import Modele.Utile;


public class DrawHub {

	private static Color COLOR_DARK_GREY = new Color(45,45,45);

	
	public static JPanel getHubPanel(MasterIHM pControleur)
	{
		JPanel panHub = new JPanel ( new BorderLayout());
		
		
		JPanel panWest = new JPanel( new GridLayout(7,1,0,20) );
		panWest.setBackground(COLOR_DARK_GREY);

		
		Button bPvP = new Button("Joueur vs Joueur",350,40, 20);
		Button bPvIA = new Button("Joueur vs IA",350,40, 20);
		Button b2Pv2P = new Button("2 Joueurs vs 2 Joueurs",350,40, 20);
		Button bSaves = new Button("Sauvegardes",350,40, 20);
		Button bRules = new Button("Regles",350,40, 20);
		Button bCredits = new Button("Credits",350,40, 20);
		Button bQuit = new Button("Quitter",350,40, 20);
		 

		
		panHub.add(panWest, BorderLayout.WEST);
		
		panWest.add(bPvP);
		panWest.add(bPvIA);
		panWest.add(b2Pv2P);
		panWest.add(bSaves);
		panWest.add(bRules);
		panWest.add(bCredits);
		panWest.add(bQuit);
		
		
		imagePanel imgPlateau[][] = new imagePanel[11][11];
		String[][] strPlateau = Utile.PlateauToString( Utile.mInitPlateau() );
		
		JPanel panCenter = new JPanel( new GridLayout(11,11) );
		panCenter.setBackground(COLOR_DARK_GREY);

		
		for (int l = 0; l < 11; l++)
		{
			for (int c = 0; c < 11; c++)
			{
				imgPlateau[l][c] = new imagePanel(strPlateau[l][c]);
				panCenter.add( imgPlateau[l][c] );
			}
		}
		
		
		
		panHub.add(panCenter, BorderLayout.CENTER);
		
		
		
		
		bPvP.addMouseListener( new java.awt.event.MouseAdapter()
		{
	         @Override
	         public void mouseReleased(java.awt.event.MouseEvent evt) 
	         {
	        	 pControleur.mMenu_PlayerVsPlayer();
	         }
		});
		
		bPvIA.addMouseListener( new java.awt.event.MouseAdapter()
		{
	         @Override
	         public void mouseReleased(java.awt.event.MouseEvent evt) 
	         {
	        	 pControleur.mMenu_PlayerVsIA();
	         }
		});
		
		b2Pv2P.addMouseListener( new java.awt.event.MouseAdapter()
		{
	         @Override
	         public void mouseReleased(java.awt.event.MouseEvent evt) 
	         {
	        	 pControleur.mMenu_2PlayersVs2Players();
	         }
		});
		
		bSaves.addMouseListener( new java.awt.event.MouseAdapter()
		{
	         @Override
	         public void mouseReleased(java.awt.event.MouseEvent evt) 
	         {
	        	 pControleur.mMenu_Saves();
	         }
		});
		
		bRules.addMouseListener( new java.awt.event.MouseAdapter()
		{
	         @Override
	         public void mouseReleased(java.awt.event.MouseEvent evt) 
	         {
	        	 pControleur.mMenu_Rules();
	         }
		});
		
		bCredits.addMouseListener( new java.awt.event.MouseAdapter()
		{
	         @Override
	         public void mouseReleased(java.awt.event.MouseEvent evt) 
	         {
	        	 pControleur.mMenu_Credits();
	         }
		});
		
		bQuit.addMouseListener( new java.awt.event.MouseAdapter()
		{
	         @Override
	         public void mouseReleased(java.awt.event.MouseEvent evt) 
	         {
	        	 pControleur.mMenu_Quitter();
	         }
		});
		
		
		return panHub;
	}
}
