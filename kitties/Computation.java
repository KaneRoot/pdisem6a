public class Computation
{
	private KittyCluster cluster, oldCluster;
	private KittyHistory kittyhistory;

	public Computation(KittyCluster c)
	{
		this.cluster = c.getCopy();
		this.kittyhistory = new KittyHistory();
	}

	public void kitty_life_game() { kitty_life_game(32); }

	public void kitty_life_game(int nb_tours)
	{
		int start;
		int end_columns;
		int end_line;

		this.kittyhistory.setHistorySize(nb_tours); // taille de l'historique

		for(start = 1 ; start <= nb_tours ; start++)
		{
			this.oldCluster = this.cluster.getCopy();
			end_columns = (oldCluster.getNbColumns()) - start;
			end_line = (oldCluster.getNbLines()) - start;

			for(int line = start ; line < end_line ; line++)
				for(int column = start ; column < end_columns ; column++)
				{
					if(oldCluster.isKittyAlive(line, column))
					{
						if(shouldKittyDie(line, column))
							cluster.killKitty(line, column);
					}
					else if(shouldKittyRaise(line, column))
						cluster.raiseKitty(line, column);
				}
			this.kittyhistory.addSnapshot(this.oldCluster.getCopy());
		}
	}

	private int nbKittiesLivingAround(int line, int column)
	{
		int count = oldCluster.isKittyAlive(line, column) ? -1 : 0;

		for(int l = -1 ; l < 2 ; l++)
			for(int c = -1 ; c < 2 ; c++)
				count = oldCluster.isKittyAlive(line + l, column + c) ? count + 1 : count;

		return count;
	}

	private boolean shouldKittyDie(int line, int column)
	{
		int nAlive = nbKittiesLivingAround(line, column);
		return ! (nAlive == 2 || nAlive == 3);
	}

	private boolean shouldKittyRaise(int line, int column)
	{
		//System.out.println("line : " + line + " column : " + column);
		return nbKittiesLivingAround(line, column) == 3;
	}

	public void displayHistory()
	{
		int k = 0;
		for(KittyCluster kc : this.kittyhistory.getHistory())
		{
			kc.displayKittyCluster();
			k++;
		}
	}

	public KittyHistory getHistory()
	{
		return this.kittyhistory;
	}
}
