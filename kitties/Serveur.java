import java.rmi.*;
import java.net.InetAddress.*;
import java.net.*;

public class Serveur
{
	public static KittyPimp kitty_pimp = null;

	private String IP;
	private String PORT;
	private int NB_BLOCKS_X;
	private int NB_BLOCKS_Y;

	public Serveur() 
	{ 
		this(10); 
	}

	public Serveur(int nb_blocks) 
	{ 
		this(nb_blocks, nb_blocks);
	}


	public Serveur(int nb_blocks_x, int nb_blocks_y)
	{
		this(nb_blocks_x, nb_blocks_y, "0.0.0.0", "9000");
	}

	public Serveur(int nb_blocks, String address, String port)
	{
		this(nb_blocks, nb_blocks, address, port);
	}

	public Serveur(int nb_blocks_x,int nb_blocks_y, String address, String port)
	{
		this.NB_BLOCKS_X = nb_blocks_x; 
		this.NB_BLOCKS_Y = nb_blocks_y; 
		this.IP = address;
		this.PORT = port;
	}

	public void go()
	{
		KittyCluster kc = KittyCluster.getNewRandomKittyCluster(NB_BLOCKS_X, NB_BLOCKS_Y);

		try
		{
			KittyPimpImpl objlocal = new KittyPimpImpl(kc);
			Naming.rebind("rmi://" + this.IP + ":" + this.PORT + "/KittyPimp", objlocal);
			System.out.println("RDY TO KILL SOME KITTIES");
		}
		catch (RemoteException re)      { System.out.println(re) ; }
		catch (MalformedURLException e) { System.out.println(e) ;  }
	}

	public static void main(String[] args)
	{
		Serveur s;
		if(args.length == 3)
			s = new Serveur(Integer.parseInt(args[0]), args[1], args[2]);
		else if (args.length == 4)
			s = new Serveur(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], args[3]);
		else
			s = new Serveur();

		s.go();
	}
}
