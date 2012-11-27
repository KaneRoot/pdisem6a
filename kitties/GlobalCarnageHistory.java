//singleton keeping the history of all already calculated snapshots
public class GlobalCarnageHistory
{
    private static final GlobalCarnageHistory _instance = new GlobalCarnageHistory();
    private List<Snapshot> history = new LinkedList<Snapshot>();

    private GlobalCarnageHistory()
    {
    }
    public static GlobalCarnageHistory getInstance()
    {
        return _instance;
    }
    public void addNextClick(KittyCluster nextClick)
    {
        history.Add(nextClick);
    }
    public KittyCluster getSnapshot(int click)
    {
        return (KittyCluster) history.get(click);
    }
    
}
