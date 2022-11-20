
import Controleur.MasterCMD;
import Vue.FrameManager;
import file.Log;

/**
 * Dans ce projet:
 * L'ensemble des attribut commencent par un a
 * 
 * L'ensemble des methodes comment par m a l'exception des getter et setter d'attribut commencent 
 * respectivement par get et set suivi du nom de l'attribut (sans le a)
 * 
 * L'ensemble des parametre commencent par un p
 * Les variables definis au seins d'une methode n'ont pas de regles particulieres
 * 
 * Cette classe sert de lanceur entre le mode graphique et le mode terminal
 * @author Bernier Allan
 * @since 18/04/20
 */
public class Main 
{	
	public static void main(String[] args)
	{
		Log.mLog("Debut du programme.");
		new FrameManager("Zen");

		MasterCMD game = new MasterCMD();
		while (true)
			game.mMenu();
	}
}
