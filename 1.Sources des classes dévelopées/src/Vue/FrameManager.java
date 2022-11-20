package Vue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controleur.MasterIHM;

/**
 * Cette classe sert à gerer tout l'affichage en mode graphique, à l'appel de nimporte la quel de ses méthodes,
 * le JPanel actuel est changer et remis à jour.
 * @author Bernier Allan
 */
public class FrameManager extends JFrame{
	
	private static final long serialVersionUID = -3608560151397303411L;

	private MasterIHM aControleur; 
	private JPanel aCurrentPan;
	private boolean aToolClicked;
	
	/**
	 * Constructeur: au lancement du jeu cette classe est instancier, elle affiche alors la page menu.
	 * @param pTitre
	 */
	public FrameManager(String pTitre)
	{
		super(pTitre);
		this.getDefaultCloseOperation();
		this.pack();
		this.setSize(1200, 600);
		this.setVisible(true);
		this.aControleur = new MasterIHM(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		aToolClicked = false;
				
		this.aCurrentPan = DrawHub.getHubPanel(this.aControleur);
		mUpdate();

	}
	
	/**
	 * Affiche la page credits du jeu
	 */
	public void mDrawCredits()
	{
		this.aCurrentPan = DrawCredits.getCreditsPanel(this.aControleur);
		mUpdate();
	}
	
	/**
	 * Affiche la page sauvegarde du jeu
	 */	
	public void mDrawSave(int pNoSave)
	{

		this.aCurrentPan = DrawSave.getSavePanel(this.aControleur, pNoSave);
		 mUpdate();
	}
	
	
	/**
	 * Change le Jpanel actuel avec le jpanel correspondant au jeu, peut importe le mode (PvP, PvIA ..) 
	 * @param pStrGrid Correspond aux images qu'il faut afficher.
	 */
	public void mDrawGame(String[][] pStrGrid)
	{
		aToolClicked = false;
		aCurrentPan = DrawGames.getGamePanel(this.aControleur, pStrGrid,aToolClicked);
		 mUpdate();
	}
	

	/**
	 * Affiche la page menu du jeu
	 */	
	public void mDrawHub()
	{
		this.aCurrentPan = DrawHub.getHubPanel(this.aControleur);
		mUpdate();
	}
	

	/**
	 * Affiche la page regles du jeu durant une partie
	 * @param pStrGrid, durant une partie même si on regarde les regles, le plateau de jeux est afficher
	 * @param pNoRule le numero de la regle à afficher
	 */	
	public void mDrawGameRules(String[][] pStrGrid, int pNoRule)
	{
		aToolClicked = true;
		aCurrentPan = DrawGames.getGamePanel_Rule(this.aControleur, pStrGrid, pNoRule,aToolClicked);
		mUpdate();
	}
	
	/**
	 * Affiche les sauvegardes du jeu durant une partie
	 * @param pStrGrid, durant une partie même si on regarde les sauvegarde, le plateau de jeux est afficher
	 * @param pNoRule le numero de la sauvegarde à afficher
	 */	
	public void mDrawGameSave(String[][] pStrGrid, int pNoSave)
	{
		aToolClicked = true;
		aCurrentPan = DrawGames.getGamePanel_Save(this.aControleur, pStrGrid, pNoSave,aToolClicked);
		mUpdate();
	}

	/**
	 * Affiche la page fin de partie du jeux
	 * @param pStrGrid Affiche une grille avec tout les pions du gagnant entourer de vert
	 * @param pWinner l'indice correspondant au gagnant avec 1 pour blanc -1 pour noir et 2 pour egaliter
	 */
	public void mDrawGameOver(String[][] pStrGrid, int pWinner)
	{
		aCurrentPan = DrawGames.getGameOverPanel(this.aControleur, pStrGrid, false, pWinner);
		mUpdate();
	}
	
	/**
	 * Affiche la page des regles du jeu
	 */
	public void mDrawRules()
	{
		aCurrentPan = DrawRules.getRulesPanel(this.aControleur);
		mUpdate();
	}
	
	/**
	 * Met a jour l'affichage du jeu avec un nouveau JPanel
	 */
	public void mUpdate()
	{
		this.setContentPane( this.aCurrentPan );
		this.repaint();
		this.revalidate();
	}
	
}









