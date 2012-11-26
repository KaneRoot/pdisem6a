import java.Remote;
import java.rmi.RemoteException;

public interface KittyPIMP extends Remote {
    public KittyCluster gimmeKittiesToKill() throws RemoteException;
    public void resultsOfTheGenocide(KittyHistory results)
        throws RemoteException;
}
