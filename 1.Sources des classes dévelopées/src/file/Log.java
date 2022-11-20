package file;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Cette classe ne contient que des m�thodes statique et n'a dont pas d'interet � etre instancier
 * G�re les logs du jeux.
 * @author badga
 */
public class Log 
{
	
	/**
	 * Ecrit le string entr�e en parametre dans le fichier Log.txt � la suite de la derniere ligne du fichier
	 * Chaque ligne sont pr�fix� de la date sous forme yyyy/MM/dd HH:mm:ss suivit du message 
	 * 
	 * @param pMessage Le texte a ecrire
	 */
	 public static void mLog(String pMessage)
    {
        try
        {
             DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
             Date date = new Date();
             FileWriter fw = new FileWriter("Log.txt",true);
             fw.write(format.format(date) + ": " + pMessage + "\n");
             fw.close();        

        }
        catch (IOException e)
        {
          //Gestion des exceptions en cas de probl�me d'acc�s au fichier
          System.out.println("Log : mLog problemes d'acces au fichier");
        }
    }
}
