import java.rmi.*;
import java.util.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public interface KittyPimp extends Remote {
    public KittyCluster gimmeKittiesToKill(int license)
        throws RemoteException;
    public int gimmeLicenseToKill() throws RemoteException;
	public KittyCluster gimmeSnapshotOfMassacre(int snapshot_number)
		throws RemoteException;
    public void resultsOfTheGenocide(KittyHistory results, int license)
        throws RemoteException;
}
