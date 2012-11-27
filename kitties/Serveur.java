import java.rmi.*;
import java.net.InetAddress.*;
import java.net.*;
import javax.swing.*;

public class Serveur
{
	public static String IP = "0.0.0.0";
	public static String PORT = "9000";

	public static int NB_BLOCKS;
	public static KittyPimp kitty_pimp = null;
	private JFrame jf = null;
	private Vue v = null;

	public Serveur() { this(10); }
	public Serveur(int nb_blocks) { Serveur.NB_BLOCKS = nb_blocks; }

	public KittyCluster getClick(int n)
	{
		return GlobalCarnageHistory.getSnapshot(n);
	}

	private void prepareDisplay()
	{
		v = new Vue();
		jf = new JFrame();

		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(v);
		jf.setTitle("Kill Kitties !");

		// taille = nombre de blocks (KittyCluster = carré) * 
		// nb colonnes (ou lignes, un bloc = un carré aussi) * 
		// taille voulue pour un chatton à l'affichage
		jf.setSize(NB_BLOCKS * Block.BLOCK_SIZE * Vue.TAILLE_BLOCS + 500, 
				NB_BLOCKS * Block.BLOCK_SIZE * Vue.TAILLE_BLOCS + 30);

		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}

	public void go()
	{
		prepareDisplay();

		KittyCluster kc = KittyCluster.getNewRandomKittyCluster(Serveur.NB_BLOCKS);
		v.display(kc);
//		Computation compute = new Computation(kc);
//		compute.kitty_life_game(16);

		try
		{
			KittyPimpImpl objlocal = new KittyPimpImpl(this, kc);
			Naming.rebind("rmi://" + Serveur.IP + ":" + Serveur.PORT + "/KittyPimp", objlocal);
			System.out.println("RDY TO KILL SOME KITTIES");
		}
		catch (RemoteException re)      { System.out.println(re) ; }
		catch (MalformedURLException e) { System.out.println(e) ;  }

	/*	À mettre dans un thread
	 *	Peut éventuellement s'arrêter si on n'a pas calculé assez vite
		for(int i = 0 ; ; i++)
		{
			v.display(this.getClick(i));
			try { java.lang.Thread.sleep(1000); } catch(Exception e) {}
		}
		   */
		/*
		for(KittyCluster kcluster : compute.getHistory().getHistory())
		{
			v.display(kcluster);
			try { java.lang.Thread.sleep(500); } catch(Exception e) {}
		}
		*/
	}

	public static void main(String[] args)
	{
		Serveur s = new Serveur();
		s.go();
	}
}
