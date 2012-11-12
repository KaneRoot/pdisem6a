public class History
{
    private List<ClusterBlock> history = new LinkedList<ClusterBlock>();
    public void addNextClick(ClusterBlock nextClick)
    {
        history.Add(nextClick);
    }
}
