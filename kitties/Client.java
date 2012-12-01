import java.rmi.* ; 
import java.net.InetAddress.* ;
import java.net.* ;

public class Client
{
	public static void main(String[] args)
	{
		int licenceToKill = -1;
		KittyPimp datKittyPimp = null;

		if(args.length != 2)
		{
			System.out.println("Usage : java Client <serveur> <port>");
			System.exit(-1);
		}

		try
		{
			datKittyPimp = (KittyPimp) Naming.lookup("rmi://"+args[0]+":"+args[1]+"/KittyPimp") ;
			licenceToKill = datKittyPimp.gimmeLicenseToKill();
			System.out.println("My licence to kill kitties : " + licenceToKill);
		}
		catch (Exception e)
		{
			System.err.println(e) ;
			e.printStackTrace();
		}
	}
}
