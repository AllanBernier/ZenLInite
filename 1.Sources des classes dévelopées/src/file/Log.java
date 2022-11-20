package file;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Cette classe ne contient que des méthodes statique et n'a dont pas d'interet à etre instancier
 * Gère les logs du jeux.
 * @author badga
 */
public class Log 
{
	
	/**
	 * Ecrit le string entrée en parametre dans le fichier Log.txt à la suite de la derniere ligne du fichier
	 * Chaque ligne sont préfixé de la date sous forme yyyy/MM/dd HH:mm:ss suivit du message 
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
          //Gestion des exceptions en cas de problème d'accès au fichier
          System.out.println("Log : mLog problemes d'acces au fichier");
        }
    }
}
