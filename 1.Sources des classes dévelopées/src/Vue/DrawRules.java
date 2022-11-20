package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Controleur.MasterIHM;
import Modele.Utile;

public class DrawRules {
	private static Color COLOR_DARK_GREY = new Color(45,45,45);

	public static JPanel getRulesPanel(MasterIHM pControleur)
	{
		JPanel rules = new JPanel(new BorderLayout() );
		
		JPanel center = mCenterRules();
		JPanel south = mBackBar( pControleur );
		
		rules.add(center, BorderLayout.CENTER );
		rules.add(south, BorderLayout.SOUTH);
		
		return rules;
	}
	
	private static JPanel mBackBar(MasterIHM pControleur)
	{
		JPanel panBack = new JPanel(new FlowLayout() );
		Button bBack = new Button("Retour", 900,40,20);
		
		bBack.addMouseListener( new java.awt.event.MouseAdapter()
		{
	         @Override
	         public void mouseReleased(java.awt.event.MouseEvent evt) 
	         {
	        	 pControleur.mRules_Back();
	         }
		});
		
		panBack.add(bBack);
		
		
		return panBack;
	}
	
	
	private static JPanel mCenterRules()
	{
		JPanel center = new JPanel( new GridLayout(5,2) );
		center.setBackground(COLOR_DARK_GREY);

		
		JLabel[] lRules = new JLabel[9];
		
		for (int i = 0; i < 9; i++)
		{
			lRules[i] = new JLabel( Utile.mGetRulesJLabel(i) );
			lRules[i].setBackground( COLOR_DARK_GREY );
			lRules[i].setForeground( new Color(255,255,255) );
			center.add(lRules[i]);
		}
		
		return center;
	}
	
	
}
