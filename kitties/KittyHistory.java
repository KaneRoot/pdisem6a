import java.util.ArrayList;

public class KittyHistory
{
	ArrayList<KittyCluster> history = new ArrayList<KittyCluster>(16);

	public void addSnapshot(KittyCluster kc)
	{
		this.history.add(kc);
	}

	public ArrayList<KittyCluster> getHistory()
	{
		return this.history;
	}
}
