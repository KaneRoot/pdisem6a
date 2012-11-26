//singleton keeping the history of all already calculated snapshots
public class History
{
    private static final History _instance = new History();
    private List<Snapshot> history = new LinkedList<Snapshot>();

    private History()
    {
    }
    public static History getInstance()
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
