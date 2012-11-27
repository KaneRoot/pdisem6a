import java.rmi.*;
import java.util.*;
import java.rmi.server.UnicastRemoteObject;


//Like an IRL Pimp, this class manages Kitties and gives them out
//to clients in need of satisfaction
public class KittyPimpImpl extends UnicastRemoteObject 
    implements KittyPimp 
{

    private class Task
    {
        public int startX, startY, sizeX, sizeY;
        public Task(int startX, int startY, int sizeX, int sizeY)
        {
            this.startX = startX;
            this.startY = startY;
            this.sizeX = sizeX;
            this.sizeY = sizeY;
        }
    }

	private Serveur serveur = null;
    private KittyCluster subjects;
    private KittyCluster[] genocideResults = new KittyCluster[16];
    private GlobalCarnageHistory achievements;
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private int lastLicense = 0;
    private HashMap<Integer,Task> assignements = new HashMap<Integer, Task>();

    public KittyPimpImpl(Serveur s, KittyCluster subjects) throws RemoteException
    {
		this.serveur = s;
        this.subjects = subjects;
        achievements = GlobalCarnageHistory.getInstance();
    }
    public int gimmeLicenseToKill() throws RemoteException
    {
        lastLicense++;
        return lastLicense;
    }
    private void divideInTasks(int nbTasks)
    {
       
    }
    public KittyCluster gimmeKittiesToKill(int license) throws RemoteException
    {
        return subjects;
    }
    public void resultsOfTheGenocide(KittyHistory results, int license)
        throws RemoteException
    {
    }
}
