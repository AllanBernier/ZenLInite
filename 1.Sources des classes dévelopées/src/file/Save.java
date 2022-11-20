package file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import Modele.Mode;
import Modele.Zen;

public class Save {
	
	/**
	 * Convertie tout les parametre en un string puis les enregistre dans un fichier texte a l'indice pSave
	 * @param pSave l'indice de la sauvegarde.
	 */
	public static void mSaveGame(int[][] pPlateau, Mode pMode, Zen pZen, int pNextPlayer, int pNoTurn, int pSave) 
	{
		if ( pPlateau != null && pZen != null && pSave >= 0 && pSave < 5)
		{

			String zen = mZenToString(pZen);
			String turn = mSaveTurnToString(pMode, pNextPlayer, pNoTurn);
			String plateau = mPlateauToString(pPlateau);
			
			File txt =  new File("Save_" + pSave + ".txt");
			try {
				txt.createNewFile();
				
				FileWriter txtWrite = new FileWriter(txt);
				
				         
				
				
				
				txtWrite.write( zen );
				txtWrite.write( turn );
				txtWrite.write( plateau );
		
				txtWrite.close();
			} catch (IOException e) {
				Log.mLog("Error : Save -> SaveGame IOException");
			}
			
		}
		else 
		{
			Log.mLog("Error : Save -> SaveGame erreur de parametres");

		}
		
		
	}
	
	/**
	 * Supprime la sauvegarde existante a l'incice pSave et le remplace par une partie injouable au tour 1
	 */
	public static void mSaveGame(int pSave) 
	{
		Zen zenNull = new Zen();

		Mode mode = Mode.PvP;
		int nextPlayer = 1;
		int noTurn = 0;
			
		int[][] plateauNull = {
				{0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0}};
			
		String zen = mZenToString(zenNull);
		String turn = mSaveTurnToString(mode, nextPlayer, noTurn);
		String plateau = mPlateauToString(plateauNull);
		
		File txt =  new File("Save_" + pSave + ".txt");
		try {
			txt.createNewFile();
			
			FileWriter txtWrite = new FileWriter(txt);
			txtWrite.write( zen );
			txtWrite.write( turn );
			txtWrite.write( plateau );
	
			txtWrite.close();
		} catch (IOException e) {
			Log.mLog("Error : Save -> SaveGame IOException");
		}		
	}
	
	/**
	 * Convertie chaque element du Zen un seul et unique string
	 * la chaine est de la forme ligne:colonne:oldLigne:oldColonne:noTurn:isAlive;
	 * 6:6:6:6:0:true;
	 * toutes les valeur sont des int sauf le isAlive qui est un boolean
	 * La chaine termine par un point virgule
	 * @return zen transformer en string
	 */
	private static String mZenToString(Zen pZen)
	{
		int l = pZen.getLine();
		int c = pZen.getColumn();
		int oldL = pZen.getLastLine();
		int oldC = pZen.getLastColumn();
		int noT = pZen.getNoTurn();
		boolean alive = pZen.getIsAlive();
		
		String zenStr =  l + ":" + c + ":" + oldL + ":" + oldC + ":" + noT + ":" + alive + ";";
		
		return zenStr;
	}
	
	
	/**
	 * Convertie l'attribut plateau en string
	 * chaque ligne est delimiter par une , chaque valeur est delimiter par : chaque valeur fait 2 caractere
	 *  "-1" , "1 " ou "2 "
	 * La fin du tableau se termine par un point virgule
	 * 0 :0 :0 :-1:0 :-1:0 :0 :0 :0 :,0 :0 :1 :0 :0 :0 :1 :0 :0 :..ect..:0 :0 :0 :1 :;
	 * @return plateau transformer en string 
	 */
	private static String mPlateauToString(int[][] pPlateau)
	{
		String zenStr = "";

		for (int l = 0; l < pPlateau.length; l++ )
		{
			for (int c = 0; c < pPlateau[l].length; c++ )
			{
				if ( pPlateau[l][c] == -1 )
				{
					zenStr = zenStr + pPlateau[l][c] + ":";
				}
				else
				{
					zenStr = zenStr + pPlateau[l][c] + " :";
				}
					
			}
			if (l < pPlateau.length - 1)
			{
				zenStr += ",";
			}
		}
		
		zenStr = zenStr + ";";
		
		
		return zenStr;

	}
	/**
	 * Convertie l'attribut mode de la partie, numero du tour de jeu et indice de prochain joueur a jouer
	 * forme aMode:aNoTurn:aNextPlayer;
	 * 2:18:-1;

	 * separateur : et ;
	 * @return les attribut transformer en string
	 */
	private static String mSaveTurnToString(Mode pMode,  int pNextPlayer, int pNoTurn)
	{
		String mode;
		if (pMode == Mode.PvP)
		{
			mode = "1";
		}
		else if (pMode == Mode.PvIA)
		{
			mode = "2";
		}
		else 
		{
			mode = "3";
		}
		
		String str = mode + ":"  + pNoTurn + ":" + pNextPlayer + ";";
		return str;	
		
	}
	
}
