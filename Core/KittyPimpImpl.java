import java.Remote;
import java.rmi.server.UnicastRemoteObject;
//Like an IRL Pimp, this class manages Kitties and gives them out
//to clients in need of satisfaction
public class KittyPimpImpl extends UnicastRemoteObject 
    implements KittyPimp {

    private KittyCluster subjects;
    private History achievements;

    public KittyPimpImpl(KittyCluster subjects)
    {
        this.subjects = subjects;
        achievements = History.getInstance();
    }
    public KittyCluster gimmeKittiesToKill() throws RemoteException
    {
        return subjects;
    }
    public void resultsOfTheGenocide(KittyHistory results)
        throws RemoteException
    {
        achievements.addNextClick(results);

    }
}
