import java.rmi.* ; 
import java.net.InetAddress.* ;
import java.net.* ;

public class Client
{
	public static int TIME_TO_WAIT = 1000;
	public static void main(String[] args)
	{
		int licenceToKill = -1;
		KittyPimp datKittyPimp = null;
		KittyCluster cluster = null;
		Computation compute;

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
			//while(true)
            //{
                cluster = datKittyPimp.gimmeKittiesToKill(licenceToKill);
                if(cluster == null)
                {
                    System.out.println("KittyCluster not received");
                }
                System.out.println("Computation");
                compute = new Computation(cluster);
                compute.kitty_life_game(32);
                System.out.println("Send result of the genocide");
                datKittyPimp.resultsOfTheGenocide( compute.getHistory(), licenceToKill);
            //}
		}
		catch (Exception e)
		{
			System.err.println(e) ;
			e.printStackTrace();
		}
	}
}
