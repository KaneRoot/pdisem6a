import java.Remote;
import java.rmi.server.UnicastRemoteObject;
//Like an IRL PIMP, this class manages Kitties and gives them out
//to clients in need of satisfaction
public class KittyPIMPImpl extends UnicastRemoteObject 
    implements KittyPIMP {

    private KittyCluster subjects;
    private History achievements;

    public KittyPIMPImpl(KittyCluster subjects)
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
