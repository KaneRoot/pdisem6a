import java.util.ArrayList;

public class KittyHistory
{
	private ArrayList<KittyCluster> history = null;

	public void addSnapshot(KittyCluster kc)
	{
		if(history == null)
			setHistorySize(16);

		this.history.add(kc);
	}

	public void setHistorySize(int size)
	{
		if(history == null)
			history = new ArrayList<KittyCluster>(size);
	}

	public ArrayList<KittyCluster> getHistory()
	{
		return this.history;
	}
}
