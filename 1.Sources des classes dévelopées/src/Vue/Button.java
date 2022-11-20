package Vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
/**
 * Cette classe est utiliser pour l'ensemble des boutons de ce jeu,
 * Certaint bouton ne sont pas cliquable mais utilise cette classe pour avoir leur aspect.
 * @author Bernier Allan
 */
public class Button extends JComponent {

	private static final long serialVersionUID = 5926358182492695951L;

	private String aText;
    private Font aBubblegum;
    private int aWidth;
    private int aHeight;
    private int aSizeFont;
    private ActionListener aListener;
    private boolean aIsOnButton = false;

    /**
     * Constructeur:
     * Sert à initialiser les attribut grace au valeurs envoyer en parametre, 
     * ajoute des action listener pour changer la valeur de l'attribut aListener.
     * @param text le texte a ecrir dans le bouton
     * @param width la taille du bouton
     * @param height la longueur du bouton
     * @param size la  taille du texte
     */
    public Button(String text,int width, int height, int size)
    {
        this.aSizeFont = size;
        setfont();
        this.aWidth = width;
        this.aHeight = height;
        this.aText = text;
        this.setPreferredSize(new Dimension(this.aWidth,this.aHeight));

        this.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                ActionEvent event = new ActionEvent(Button.this, ActionEvent.ACTION_PERFORMED, "");
                if (Button.this.aListener != null) Button.this.aListener.actionPerformed(event);
            }
            @Override
            public void mouseEntered(MouseEvent e) 
            {
                aIsOnButton = true;
                Button.this.repaint();
            }
            @Override
            public void mouseExited(MouseEvent e) 
            {
                aIsOnButton = false;
                Button.this.repaint();
            }
        });
    }

    /**
     * Redefinit l'apparance de composant pour le rendre plus joli à mon goût
     */
    @Override
    protected void paintComponent(Graphics g) 
    {

        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setStroke((new BasicStroke(1000)));
        g2d.setColor( new Color(243,235,215) );
        g2d.drawRect(10,10,this.getWidth() -20 ,this.getHeight() - 20 );

        g2d.setStroke((new BasicStroke(10)));


        g2d.setColor(aIsOnButton? new Color(206, 117, 10) : new Color(0, 0, 0) );
        g2d.drawRect(0,0,this.getWidth(),this.getHeight());        
        g2d.setFont(this.aBubblegum);

        FontMetrics metrics = g2d.getFontMetrics();
        String text = String.valueOf((this.aText));
        int x = (this.getWidth() - metrics.stringWidth(text))/2;
        int y = (this.getHeight() - metrics.getHeight())/2 +metrics.getAscent()+2;

        g2d.drawString(text,x,y);
    }

    /**
     * Change la font du texte
     */
    public void setfont()
    {
    	
		this.aBubblegum =  new Font("Trebuchet MS", Font.BOLD, this.aSizeFont);
		GraphicsEnvironment 
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(this.aBubblegum);
		
    }

    /**
     * sert à changer la couleur du bouton lorceque l'on passe le curseur dessus (cette méthode puis le constructeur)
     * @param listener
     */
    public void setActionListener(ActionListener listener) 
    {
        this.aListener = listener;
    }
    
}
