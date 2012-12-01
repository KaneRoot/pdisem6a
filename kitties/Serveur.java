import java.rmi.*;
import java.net.InetAddress.*;
import java.net.*;

public class Serveur
{
	public static KittyPimp kitty_pimp = null;

	private String IP;
	private String PORT;
	private int NB_BLOCKS;

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

	public void go()
	{
		KittyCluster kc = KittyCluster.getNewRandomKittyCluster(NB_BLOCKS);

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
		Serveur s = new Serveur(Integer.parseInt(args[0]), args[1], args[2]);
		s.go();
	}
}
