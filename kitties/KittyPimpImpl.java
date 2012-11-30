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

    private KittyCluster subjects;
    private KittyCluster[] genocideResults = new KittyCluster[Block.BLOCK_SIZE];
    private GlobalCarnageHistory achievements;
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private int lastLicense = 0;
    private HashMap<Integer,Task> assignements = new HashMap<Integer, Task>();

    public KittyPimpImpl(KittyCluster subjects) throws RemoteException
    {
        this.subjects = subjects;
        achievements = GlobalCarnageHistory.getInstance();
    }
    public int gimmeLicenseToKill() throws RemoteException
    {
        lastLicense++;
        return lastLicense;
    }
    private int calculateNumberOfcolumns()
    {
        int n;
        for(n = 1;n*n< lastLicense*2; n++);
        return n;
    }
    private void createTasks()
    {
        int numberOfLines = calculateNumberOfcolumns();
        //On calcule la taille des blocs que les clients doivent calculer
        int sizeLine = subjects.getNbBlockLines() / numberOfLines;
        int sizeColumn = subjects.getNbBlockColumns() / numberOfLines;
        // On créer tout les tasks
        for(int i = 0; i <  subjects.getNbBlockLines(); i+= sizeLine)
        {
            for(int j = 0; j < subjects.getNbBlockColumns(); i += sizeColumn)
            {
                // on s'occupe de la derniere colonne et ligne, dans le
                // cas ou c'est pas un multiple.
                int tmpSizeLine = sizeLine;
                int tmpSizeColumn = sizeColumn;
                if(i + sizeLine <= subjects.getNbBlockLines())
                {
                    tmpSizeLine = subjects.getNbBlockLines() - i;
                }
                if(j + sizeColumn <= subjects.getNbBlockColumns())
                {
                    tmpSizeColumn = subjects.getNbBlockColumns() - j;
                }
                tasks.add(new Task(i, j, tmpSizeColumn, tmpSizeColumn));
            }
        }
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
