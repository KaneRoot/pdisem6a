import java.rmi.*;
import java.net.InetAddress.*;
import java.net.*;
import javax.swing.*;

public class Serveur
{
	public static int TIME_BETWEEN_FRAMES = 1000;
	public static KittyPimp kitty_pimp = null;

	private String IP;
	private String PORT;
	private int NB_BLOCKS;
	private JFrame jf = null;
	private Vue v = null;

	public Serveur() 
	{ 
		this(10); 
	}
	public Serveur(int nb_blocks) 
	{ 
		this(nb_blocks, "0.0.0.0", "9000");
	}
	public Serveur(int nb_blocks, String address, String port)
	{
		this.NB_BLOCKS = nb_blocks; 
		this.IP = address;
		this.PORT = port;
	}

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

		jf.setLocationRelativeTo(null); // set the display to the center of the screen
	}

	public void go()
	{
		prepareDisplay();

		KittyCluster kc = KittyCluster.getNewRandomKittyCluster(NB_BLOCKS);
		/*
		Computation compute = new Computation(kc);
		compute.kitty_life_game(16);
		*/

		try
		{
			KittyPimpImpl objlocal = new KittyPimpImpl(this, kc);
			Naming.rebind("rmi://" + this.IP + ":" + this.PORT + "/KittyPimp", objlocal);
			System.out.println("RDY TO KILL SOME KITTIES");
		}
		catch (RemoteException re)      { System.out.println(re) ; }
		catch (MalformedURLException e) { System.out.println(e) ;  }
		v.display(kc);
		jf.setVisible(true);

		KittyCluster kc_tmp;

		/*	
			À mettre dans un thread
			Peut éventuellement s'arrêter si on n'a pas calculé assez vite
		*/
		for(int i = 0 ; ; i++)
		{
			kc_tmp = null;
			kc_tmp = this.getClick(i);
			while(kc_tmp == null)
			{
				try { java.lang.Thread.sleep(TIME_BETWEEN_FRAMES); } catch(Exception e) {}
				kc_tmp = this.getClick(i);
			}

			v.display(this.getClick(i));
			try { java.lang.Thread.sleep(TIME_BETWEEN_FRAMES); } catch(Exception e) {}
		}
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
		Serveur s = new Serveur(Integer.parseInt(args[0]), args[1], args[2]);
		s.go();
	}
}
