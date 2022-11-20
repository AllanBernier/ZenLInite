package file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import Modele.Mode;
import Modele.Zen;

/**
 * Cette classe sert a charger une partie sauvegarder.
 * @author Bernier Allan
 */
public class Load {
	
	int[][] aPlateau;	
	int aMode;
	Zen aZen;
	int aNextPlayer;
	int aNoTurn;
	int aSave;
	
	/**
	 * Charge la sauvegarde numero pSave et initialise tout les attribut de cette classe
	 * @param pSave le numero de la sauvegarde a charger
	 */
	public Load(int pSave) 
	{
		if ( pSave >= 0 && pSave < 5)
		{
			this.aSave = pSave;
			mLoadGame();
		}
		else
		{
			System.err.println("Load : Parametre incorect");
		}
	}
		
	/** 
	 * Lis le fichier de sauvegarde puis séparre chaque partie puis envoie chaque partie à la méthode qui dois la traité
	 */
	private void mLoadGame()
	{
		BufferedReader in;
		try 
		{
			in = new BufferedReader(new
			         InputStreamReader(
			        		 getClass().
			        		 getClassLoader().
			        		 getResourceAsStream("Save_" + this.aSave + ".txt") ));
			
			String line;
	
			line = in.readLine();
		
			String strZen = line.substring(0, line.indexOf(';') ); // On prend ce qu'il y a avant le premier ; c'est a dire les valeurs du Zen
			String plateau = line.substring( line.indexOf(';') + 1 ); // On prend tout le reste dans un autre string
			
			String turn = plateau.substring(0, plateau.indexOf(';') ); // On prend ce qu'il y a avant le premier ; du reste, c'est a dire le noTurn indice joueur et mode
			plateau = plateau.substring( plateau.indexOf(';') + 1 ); // On prend tout le reste c'est a dire le plateau dans un autre string 
			
			
			mStringToZen(strZen);
			mStringToTurn(turn);
			mStringToTab(plateau);
			
			in.close();
		}
		catch (FileNotFoundException e) 
		{
			System.err.println("mLoadGame : FileNotFoundException");
		}
		catch (IOException e) 
		{
			System.err.println("mLoadGame : IOException");
		}
	}
		
	/**
	 * Convertie la chaine de caractère lut dans le fichier texte en un Zen
	 * @param pZen info pour indexe le Zen de la sauvegarde
	 */
	private void mStringToZen(String pZen)
	{		
		int idMin = 0;
		int idMax = 0;
		String reste = pZen;
		int i = 0;
		int[] tabValues = new int[5];
		
		while (idMax != -1 && i < 5)
		{
			idMax = reste.indexOf(":") + 1;
			tabValues[i] = Integer.parseInt( reste.substring(idMin ,idMax - 1) ); 
			reste = reste.substring(idMax );
			idMin = 0;
			i++;
		}
		boolean isAlive =  Boolean.parseBoolean( reste );
		
		//Zen(int pLine, int pColumn, int pLastLine, int pLastColumn, int pNoTurn, boolean pIsAlive)
		this.aZen = new Zen(tabValues[0],tabValues[1],tabValues[2],tabValues[3],tabValues[4], isAlive);
		
	}
	
	/**
	 * Convertie la chaine de caractere envoyer en parametre en 3 attribut:
	 * aMode aNoTour aNextPlayer 
	 * Ne fais aucune verification sur la veracité de la chaine de caractere
	 * @param pTurn la chaine pour trouver les valeurs des attribut
	 */
	private void mStringToTurn(String pTurn)
	{
		//mode noTour nextPlayer
		
		int idMin = 0;
		int idMax = 0;
		String reste = pTurn;
		int i = 0;
		int[] tabValues = new int[2];
		
		while (idMax != -1 && i < 2)
		{
			idMax = reste.indexOf(":") + 1;
			tabValues[i] = Integer.parseInt( reste.substring(idMin ,idMax - 1) ); 
			reste = reste.substring(idMax );
			idMin = 0;
			i++;
		}
		
		this.aMode = tabValues[0];
		this.aNoTurn = tabValues[1];
		this.aNextPlayer = Integer.parseInt( reste );
	}
	
	/**
	 * Convertie la chaine de caractere envoyer en parametre en un magnifique tableau a deux dimensions:
	 * Ne fais aucune verification sur la veracité de la chaine de caractere
	 * @param pTurn la chaine correspondant au tableau
	 */
	private void mStringToTab(String pTab)
	{
		
		String[][] srtTab = new String[11][11];
		this.aPlateau = new int[11][11];
		
		for (int l = 0; l < 11; l++)
		{		
			for (int c = 0; c < 11; c++)
			{
				srtTab[l][c] = pTab.substring( 34* l + c * 3 ,34* l + (c * 3) + 2);
				if ( srtTab[l][c].equals("1 ") )
				{
					this.aPlateau[l][c] = 1;
				}
				else if ( srtTab[l][c].equals("-1") ) 
				{
					this.aPlateau[l][c] = -1;
				}
				else if ( srtTab[l][c].equals("2 ") )
				{
					this.aPlateau[l][c] = 2;	
				}
				else
				{
					this.aPlateau[l][c] = 0;
				}
					
	
			}

		}
		
		
	}
	
	// Getter de chaques attribut	
	/**
	 * @return the aPlateau
	 */
	public int[][] getPlateau() {
		return aPlateau;
	}

	/**
	 * @return the aMode
	 */
	public Mode getMode() {
		Mode mode = Mode.PvP;
		switch(this.aMode)
        {
	        case 1: //Joue en Player vs IA
	        	mode = Mode.PvP;
	        break;
	        case 2: //Joue en Player vs Player
	        	mode = Mode.PvIA;

	        break;
	        case 3: // Joue en 2 Player vs 2 Player
	        	mode = Mode.PvP4Joueurs;
	        break;
        }
		return mode;
	}

	/**
	 * @return the aZen
	 */
	public Zen getZen() {
		return aZen;
	}

	/**
	 * @return the aNextPlayer
	 */
	public int getNextPlayer() {
		return aNextPlayer;
	}

	/**
	 * @return the aNoTurn
	 */
	public int getNoTurn() {
		return aNoTurn;
	}

	@Override
	public String toString() {
		String toS = "Mode: ";
		if (this.aMode == 1)
		{
			toS = toS + "Player Vs IA";			
		}
		else if (this.aMode == 2)
		{
			toS = toS + "Player Vs Player";	
		}
		else if (this.aMode == 3)
		{
			toS = toS + "2 Player Vs 2 Player";
		}
		else if (this.aMode == 0)
		{
			toS = toS + "Pas de sauvegarde";
		}
		
		toS = toS + "\tTour :" + this.aNoTurn;
		
		return toS;
	}


	
	
	
}
