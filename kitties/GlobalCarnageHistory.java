import java.util.*;

//singleton keeping the history of all already calculated snapshots
public class GlobalCarnageHistory
{
    private static final GlobalCarnageHistory _instance = new GlobalCarnageHistory();
    private static LinkedList<KittyCluster> history = new LinkedList<KittyCluster>();

    private GlobalCarnageHistory()
    {
    }
    public static GlobalCarnageHistory getInstance()
    {
        return _instance;
    }
    public static void addNextClick(KittyCluster nextClick)
    {
        history.add(nextClick.getCopy());
    }
    public static KittyCluster getSnapshot(int click)
    {
        return (KittyCluster) history.get(click);
    }
    
}
