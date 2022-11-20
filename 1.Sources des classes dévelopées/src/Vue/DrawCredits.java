package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Controleur.MasterIHM;

/**
 * Cette classe permet d'obtenir le JPanel de la page de crédits
 * @author Bernier Allan
 */
public class DrawCredits {
	private static Color COLOR_DARK_GREY = new Color(45,45,45);

	/**
	 * Ce JPanel contient toute la page credits
	 * @param pControleur Le controleur de chaque boutons de la page
	 * @return La page Credits
	 */
	public static JPanel getCreditsPanel(MasterIHM pControleur)
	{
		JPanel credits = new JPanel(new BorderLayout() );
		
		JPanel center = mCenterCredits();
		JPanel south = mBackBar( pControleur );
		
		credits.add(center, BorderLayout.CENTER );
		credits.add(south, BorderLayout.SOUTH);
		
		return credits;
	}
	
	
	/**
	 * Affiche un bouton dans un JPanel et sont action listener, le bouton permet de retourner au menu
	 * @param pControleur Le controleur permetant de gerer les action sur les boutons
	 * @return Le JPanel contenant ce bouton
	 */
	private static JPanel mBackBar(MasterIHM pControleur)
	{
		JPanel panBack = new JPanel(new FlowLayout() );
		Button bBack = new Button("Retour", 900,40,20);
		
		bBack.addMouseListener( new java.awt.event.MouseAdapter()
		{
	         @Override
	         public void mouseReleased(java.awt.event.MouseEvent evt) 
	         {
	        	 pControleur.mCredits_Back();
	         }
		});
		
		panBack.add(bBack);
		
		
		return panBack;
	}
	

	/**
	 * Affiche deux JLabel dans le JPanel envoyer en retour un contenant Credits et l'autre contenant les credits
	 * @return Le JPanel à afficher au centre du la page Credits
	 */
	private static JPanel mCenterCredits()
	{
		JPanel center = new JPanel( new GridLayout(4,1) );
		center.setBackground(COLOR_DARK_GREY);

		
		JLabel title = new JLabel("Credits");
		title.setFont(new Font("Trebuchet MS", Font.BOLD, 40));

		JLabel credits = new JLabel("<html> Jeux programmé sous Java <br>"
				   + "Par Allan Bernier dans le cadre du projet de programmation <br>"
				   + "du DUT informatique de Vannes. <br></html>");
		credits.setFont(new Font("Trebuchet MS", Font.BOLD, 35));
		credits.setForeground(new Color(255,255,255));
		
		title.setForeground(new Color(255,255,255));
		
		center.add(title);
		center.add(credits);
		
		return center;
	}
	
	
}
