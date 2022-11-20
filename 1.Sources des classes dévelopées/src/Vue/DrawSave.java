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

public class DrawSave {

	private static Color COLOR_DARK_GREY = new Color(45,45,45);
	



	public static JPanel getSavePanel(MasterIHM pControleur, int pNoSave)
	{
		JPanel panSave = new JPanel( new BorderLayout() );
	
		
		
		JPanel panCenter= drawCenter(pNoSave);
		JPanel panSouth  = drawSouthBar(pControleur, pNoSave);
		
		panSave.add(panCenter, BorderLayout.CENTER);
		panSave.add(panSouth, BorderLayout.SOUTH);
		
		return panSave;
		
	}

	
	
	private static JPanel drawCenter(int pNoSave)
	{
		Load load = new Load(pNoSave);
		
		
		JPanel panCenter = new JPanel( new GridLayout(1,2) );
		
		
		JPanel westPan = new JPanel();
		westPan.setBackground(COLOR_DARK_GREY);
		westPan.setLayout( new GridLayout(3,1) );
		
		JLabel noSave = new JLabel("Sauvegarde numero : " + pNoSave + "        ");
		JLabel mode = new JLabel( "Mode : " + load.getMode() );
		JLabel noTurn = new JLabel( "Tour numero : " + load.getNoTurn() );
		
		noSave.setFont( new Font("Arial",Font.BOLD,22) );
		mode.setFont( new Font("Arial",Font.PLAIN,18) );
		noTurn.setFont( new Font("Arial",Font.PLAIN,18) );

		noSave.setForeground(new Color(255,255,255));
		mode.setForeground(new Color(255,255,255));
		noTurn.setForeground(new Color(255,255,255));
		
		
		westPan.add(noSave);
		westPan.add(mode);
		westPan.add(noTurn);
			
		JPanel eastPan = new JPanel();
		eastPan.setBackground(COLOR_DARK_GREY);

		imagePanel imgPlateau[][] = new imagePanel[11][11];
		String[][] strPlateau = Utile.PlateauToString( load.getPlateau() );
		
		

		GridLayout grid = new GridLayout(11,11);
		eastPan.setLayout( grid );

		for (int l = 0; l < 11; l++)
		{
			for (int c = 0; c < 11; c++)
			{
				imgPlateau[l][c] = new imagePanel(strPlateau[l][c] );
				eastPan.add( imgPlateau[l][c] );
			}
		}
		
		panCenter.add(westPan);
		panCenter.add(eastPan);
		return panCenter;
	}
	
	
	private static JPanel drawSouthBar(MasterIHM pControleur, int pNoSave)
	{
		
		Button bBack = new Button("Retour",400,40, 20);
		Button bNext = new Button("Suivant",600,40, 20);
		Button bPrevious = new Button("Precedent",600,40, 20);
		Button bDelete = new Button("Supprimer",400,40, 20);
		Button bContinue = new Button("Continuer",400,40, 20);
		
		
		JPanel panSouthBar = new JPanel(new GridLayout(2,1,20,20) );
		panSouthBar.setBackground(COLOR_DARK_GREY);

		
		JPanel southPan1 = new JPanel();
		JPanel southPan2 = new JPanel();
		southPan1.setLayout( new GridLayout(1,2,10,10) );
		southPan2.setLayout( new GridLayout(1,3,10,10) );
		southPan1.setBackground( COLOR_DARK_GREY);
		southPan2.setBackground( COLOR_DARK_GREY);

		

		panSouthBar.add(southPan1);
			southPan1.add(bPrevious,BorderLayout.EAST);
			southPan1.add(bNext,BorderLayout.WEST);
		panSouthBar.add(southPan2);
			southPan2.add(bContinue, BorderLayout.EAST);
			southPan2.add(bBack, BorderLayout.CENTER);
			southPan2.add(bDelete, BorderLayout.WEST);
				
				
		
		bBack.addMouseListener( new java.awt.event.MouseAdapter()
		{
	         @Override
	         public void mouseReleased(java.awt.event.MouseEvent evt) 
	         {
	        	 pControleur.mSaves_Back();
	         }
		});
		
		bNext.addMouseListener( new java.awt.event.MouseAdapter()
		{
	         @Override
	         public void mouseReleased(java.awt.event.MouseEvent evt) 
	         {
	        	 pControleur.mSaves_Next( pNoSave );
	         }
		});
		
		bPrevious.addMouseListener( new java.awt.event.MouseAdapter()
		{
	         @Override
	         public void mouseReleased(java.awt.event.MouseEvent evt) 
	         {
	        	 pControleur.mSaves_Previous( pNoSave );
	         }
		});
		
		bDelete.addMouseListener( new java.awt.event.MouseAdapter()
		{
	         @Override
	         public void mouseReleased(java.awt.event.MouseEvent evt) 
	         {
	        	 pControleur.mSaves_Delete( pNoSave );
	         }
		});
		
		bContinue.addMouseListener( new java.awt.event.MouseAdapter()
		{
	         @Override
	         public void mouseReleased(java.awt.event.MouseEvent evt) 
	         {
	        	 pControleur.mSaves_Continue( pNoSave );
	         }
		});
		
		
		return panSouthBar;
	}
	
	
}
