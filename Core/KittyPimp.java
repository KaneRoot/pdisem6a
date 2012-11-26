import java.Remote;
import java.rmi.RemoteException;

public interface KittyPimp extends Remote {
    public KittyCluster gimmeKittiesToKill(int license)
        throws RemoteException;
    public int gimmeLicenseToKill() throws RemoteException;
    public void resultsOfTheGenocide(KittyHistory results, int license)
        throws RemoteException;
}
