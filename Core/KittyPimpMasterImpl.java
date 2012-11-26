import java.Remote;
import java.rmi.server.UnicastRemoteObject;
//Like an IRL PimpMaster, this class manages Kitties and gives them out
//to clients in need of satisfaction
public class KittyPimpMasterImpl extends UnicastRemoteObject 
    implements KittyPimpMaster {

    private KittyCluster subjects;
    private History achievements;

    public KittyPimpMasterImpl(KittyCluster subjects)
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
