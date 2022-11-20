package Vue;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Cette classe sert à l'affichage de chaque images du jeux.
 * @author Bernier Allan
 */
public class imagePanel extends JPanel {
	
	private static final long serialVersionUID = 2064791986261931804L;

	ImageIcon aImage;
	
	/**
	 * Initialise l'attribut aImage en tant qu'ImageIcon avec le path envoyer en parametre
	 * @param pPath chemin d'acces de l'image
	 */
	public imagePanel(String pPath)
	{
		super();
		aImage = new ImageIcon( this.getClass().getResource("/" +  pPath + ".png" ) );
	}

	/**
	 * Dessine l'image dans tout l'espace du JPanel.
	 */
	@Override
	protected void paintComponent(Graphics g)
	{
		g.drawImage(aImage.getImage() , 0, 0, getWidth(), getHeight(), null);
		this.setPreferredSize(new Dimension(500,500));
	}
	
}
