import java.rmi.* ; 
import java.net.InetAddress.* ;
import java.net.* ;
import javax.swing.*;

public class WatchTheKittiesDie
{
	public static int TIME_BETWEEN_FRAMES = 1000;
	public int NB_BLOCKS;

	private JFrame jf = null;
	private Vue v = null;
	private String IP;
	private String PORT;
	private int NB_BLOCKS;

	public WatchTheKittiesDie() 
	{ 
		this(10); 
	}

	public WatchTheKittiesDie(int nb_blocks) 
	{ 
		this(nb_blocks, "0.0.0.0", "9000");
	}

	public WatchTheKittiesDie(int nb_blocks, String address, String port)
	{
		this.NB_BLOCKS = nb_blocks; 
		this.IP = address;
		this.PORT = port;
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

		int snapshot_count = 0;
		KittyPimp datKittyPimp = null;
		KittyCluster cluster = null;

		if(args.length != 2)
		{
			System.out.println("Usage : java WatchTheKittiesDie <serveur> <port>");
			System.exit(-1);
		}

		jf.setVisible(true);

		try
		{
			datKittyPimp = (KittyPimp) Naming.lookup("rmi://"+args[0]+":"+args[1]+"/KittyPimp") ;

			while(1)
			{
				cluster = datKittyPimp.gimmeSnapshotOfMassacre(snapshot_count);
				while(cluster == null)
				{
					try { java.lang.Thread.sleep(TIME_BETWEEN_FRAMES); } catch(Exception e) {}
					cluster = datKittyPimp.gimmeSnapshotOfMassacre(snapshot_count);
				}
				snapshot_count++;
				v.display(cluster);
				cluster = null;
			}
		}
		catch (Exception e)
		{
			System.err.println(e) ;
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		WatchTheKittiesDie wtkd = new WatchTheKittiesDie(Integer.parseInt(args[0]), args[1], args[2]);
		wtkd.go();
	}
}
