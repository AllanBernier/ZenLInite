package Modele;

/**
 * Cette classe nous permet de connaitre toutes les information utile à propos du Zen.
 * @author Allan Bernier
 * @since 29/04/20
 */
public class Zen
{
	private int aLine;
	private int aColumn;
	private int aLastLine;
	private int aLastColumn;
	private int aNoTurn; // Tour de jeu du dernier mouvement
	private boolean aIsAlive;
	private final int afZenColor = 2;
	


	/**
	 * Ce constructeur est utiliser pour crée un Zen en cour de partie
	 * Constructeur de la class Zen
	 * Cette class sait, a tout moment de la partie les informations pour le Zen
	 * - Si le Zen est en vie
	 * - Les coordonees du Zen
	 * - La derniere position du Zen
	 * - Le tour de jeu
	 * @param pLine la ligne actuelle du Zen
	 * @param pColumn la colonne actuelle du Zen
	 * @param pLastLine la derniere position du Zen
	 * @param pLastColumn la derniere position du Zen
	 * @param pNoTurn le dernier tour ou le Zen à bouger
	 * @param pIsAlive true si le Zen est en vie sinon false
	 */
	public Zen(int pLine, int pColumn, int pLastLine, int pLastColumn, int pNoTurn, boolean pIsAlive)
	{
		this.aLine = pLine;
		this.aColumn = pColumn;
		this.aLastLine = pLastLine;
		this.aLastColumn = pLastColumn;
		this.aNoTurn = pNoTurn;
		this.aIsAlive = pIsAlive;
	}
	
	/**
	 *  Ce constructeur ne prend pas de parametre car il est utiliser pour crée un Zen au tour 1 d'une partie, au tour 1 le Zen prend des valeurs par defaut
	 * Constructeur de la class Zen
	 * Cette class sait, a tout moment de la partie les informations pour le Zen
	 * - Si le Zen est en vie
	 * - Les coordonees du Zen
	 * - La derniere position du Zen
	 * - Le tour de jeu
	 */
	public Zen()
	{
		this.aLine = 5;
		this.aColumn = 5;
		this.aLastLine = 5;
		this.aLastColumn = 5;
		this.aNoTurn = 0;
		this.aIsAlive = true;
	}
	
// Accesseur des attributs
	/**
	 * @return renvoie la ligne actuelle du Zen
	 */
	public int getLine()
	{
		return this.aLine;
	}
	
	/**
	 * Change la ligne du Zen
	 * @param pLine la nouvelle ligne du Zen
	 */
	public void setLine(int pLine)
	{
		this.aLine = pLine;
	}
	
	/**
	 * @return renvoie la Colonne actuelle du Zen
	 */
	public int getColumn()
	{
		return this.aColumn;
	}
	
	/**
	 * Change la Colonne du Zen
	 * @param pColumn la nouvelle Colonne du Zen
	 */
	public void setColumn(int pColumn)
	{
		this.aColumn = pColumn;
	}
	
// Accesseur de l'ancienne position
	/**
	 * @return renvoie la derniere ligne du Zen
	 */
	public int getLastLine()
	{
		return this.aLastLine;
	}
	
	/**
	 * Change l'ancienne ligne du Zen
	 * @param pLine la nouvelle ligne du Zen
	 */
	public void setLastLine(int pLine)
	{
		this.aLastLine = pLine;
	}
	
	/**
	 * @return renvoie la derniere Colonne du Zen
	 */
	public int getLastColumn()
	{
		return this.aLastColumn;
	}
	
	/**
	 * Change la Colonne du Zen
	 * @param pColumn la nouvelle Colonne du Zen
	 */
	public void setLastColumn(int pColumn)
	{
		this.aLastColumn = pColumn;
	}

	/**
	 * Getter de la variable correspondant au dernier tour ou le Zen a bouger
	 * @return le tour du dernier mouvement du Zen
	 */
	public int getNoTurn()
	{
		return this.aNoTurn;
	}
	/**
	 * Setter de la variable correspondant au dernier tour ou le Zen a bouger
	 * @param pNoTurn le tour du dernier déplacement du zen
	 */ 
	public void setNoTurn(int pNoTurn)
	{
		this.aNoTurn = pNoTurn;
	}

	/**
	 * Renvoie l'info si le Zen est en vie ou non
	 * @return renvoie vrais si le Zen est en vie, faux sinon
	 */
	 public boolean getIsAlive()
	 {
		 return this.aIsAlive;
	 }
	 
	 /**
	  * Setter pour savoir si le Zen est en Vie
	  * verifie sur tout le plateau qu'il n'y ai plus de Zen
	  * @param pIsAlive nouvelle valeur de aIsAlive
	  */
	 public void setIsAlive(boolean pIsAlive)
	 {
		 this.aIsAlive = pIsAlive;
	 }
	 
	/**
	 * @return the aZenColor
	 */
	public int getZenColor() {
		return afZenColor;
	}

	// Methodes du Zen
	/**
	 * Renvoit le tableau de déplacement du Zen contenant ses déplacements possible sous la forme
	 * tab[x][0] = ligne
	 * tab[x][1] = colonne
	 * tab[x][2] = 1 peut se déplacer, 5 n'est colle a personne (ne peut pas se deplacer, position jouer au dernier coup 
	 * 
	 * 
	 * @param pMap le plateau actuel du jeux
	 * @param pMove le tableau des deplacement "possible" pour un pion normal
	 * @param pNoTurn le tour actuelle de la partie
	 * @return un tableau de int a deux dimension contenant les 8 deplacement du Zen et leur possibilité 
	 */
	public int[][] mZenMove(int[][] pMap,int[][] pMove, int pNoTurn)
	{
		boolean zenCanMove = mZenCanMove( pMap);
		
		for (int i = 0; i < 8; i++)
		{
			if ( pMove[i][2] == 1 || pMove[i][2] == 3 ) // Si un pion normal peut se déplacer OU tuer un ennemie en se deplacant
			{
				if ( zenCanMove ) // Si le Zen peux bouger
				{

					if(pMove[i][0] == this.aLastLine && pMove[i][1] == this.aLastColumn ) // Son dernier deplacement est interdit
					{
						if ( this.aNoTurn == pNoTurn - 1 ) //  Si le Zen a bouger au tour d'avant
						{
							pMove[i][2] = 5;
						}
					}
				}
				else // Si le Zen ne peux pas bouger
				{
					
					pMove[i][2] = 5; // Alors tout ses deplacement sont interdits
				}
			}
		}
		return pMove;
	}
	
	/**
	 * Regarde pour chaqu'une des 8 cases autour du Zen si il y a un pion blanc ou noir 
	 * Si il y a un pion alors le Zen peux bouger, sinon il ne peux pas
	 *
	 * @param pMap le plateau actuelle du jeux
	 * @return true si le Zen peut bouger, false sinon
	 */
	public boolean mZenCanMove(int[][] pMap)
	{
		boolean ret = false;
		for (int l = this.aLine - 1; l < this.aLine + 2; l++) // Pour chaque cases a cote du Zen
		{
			for (int c = this.aColumn - 1; c < this.aColumn + 2; c++)
			{
				if ( l < 11 && l >= 0 && c < 11 && c >= 0) // Si la case teste n'est pas en dehors du plateau
				{
					if (pMap[l][c] == 1 || pMap[l][c] == -1 ) // Si il y a un joueur blanc ou noir a cote du zen
					{
						ret = true; // Alors le zen peut bouger
					}
				}
			}
		}
		return ret; // Sinon il ne peux pas
	}
	
	/**
	 * Change la ligne et la colonne du Zen et met a jour ses anciennes coordonée du Zen
	 * @param pLine la ligne du Zen
	 * @param pColumn la colonne du Zen
	 * @param pTurn Le dernier tour ou il a bouger
	 */
	public void mRefreshZen(int pLine, int pColumn, int pTurn)
	{
		if ( pLine >= 0 && pColumn >=0 && pLine < 11 && pColumn < 11 && pTurn > this.aNoTurn)
		{
			this.aLastColumn = this.aColumn;
			this.aLastLine = this.aLine;
			
			this.aColumn = pColumn;
			this.aLine = pLine;
			this.aNoTurn = pTurn;
			 mToString();

		}
	}
	
	public void mToString()
	{
		/*
		System.out.println("== Zen To String ==");
		System.out.println("aLine: " + this.aLine );
		System.out.println("aColumn: " + this.aColumn );
		System.out.println("aLastLine: " + this.aLastLine );
		System.out.println("aLastColumn: " + this.aLastColumn );
		System.out.println("aNoTurn: " + this.aNoTurn );
		System.out.println("aIsAlive: " + this.aIsAlive );
		*/
	}
 }
