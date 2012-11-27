import java.net.*;
import java.rmi.*;
public class Core
{
    int sizeHeight, sizeWidth;
    public Core(int nbLines, int nbColumns)
    {
        sizeHeight = nbLines;
        sizeWidth = nbColumns;
    }
    public KittyCluster getClick(int n)
    {
        //return GlobalCarnageHistory.get(n);
    }
    public static void main(String[] args)
    {
        try
        {
            KittyPimpImpl objlocal = new KittyPimpImpl(500,500);
            Naming.rebind("rmi://localhost:543543/KittyPimp",objlocal);
            System.out.println("RDY TO KILL SOME KITTIES");
        }
        catch (RemoteException re)      { System.out.println(re) ; }
        catch (MalformedURLException e) { System.out.println(e) ;  }
    }
}
