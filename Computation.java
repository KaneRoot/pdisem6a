public class Computation
{
	private KittyCluster cluster;

	public Computation(KittyCluster c)
	{
		this.cluster = c;
	}

	public void kitty_life_game()
	{
		int start;
		int end_columns;
		int end_line;

		for(start = 1 ; start < 17 ; start++)
		{
			end_columns = (cluster.getNbColumns() *32) - start;
			end_line = (cluster.getNbLines() *32) - start;

			for(int line = start ; line < end_line ; line++)
				for(int column = start ; column < end_columns ; column++)
				{
					if(cluster.isKittyAlive(line, column))
					{
						if(shouldKittyDie(line, column))
							cluster.killKitty(line, column);
					}
					else if(shouldKittyRaise(line, column))
						cluster.raiseKitty(line, column);
				}
			// TODO : faire la copie
		}
	}

	private int nbKittiesLivingAround(int line, int column)
	{
		int count = cluster.isKittyAlive(line, column) ? -1 : 0;

		for(int l = -1 ; l < 2 ; l++)
			for(int i = -1 ; i < 2 ; i++)
				count = cluster.isKittyAlive(line + l, column + i) ? count + 1 : count;

		return count;
	}

	private boolean shouldKittyDie(int line, int column)
	{
		int nAlive = nbKittiesLivingAround(line, column);
		return ! (nAlive == 2 || nAlive == 3);
	}

	private boolean shouldKittyRaise(int line, int column)
	{
		return nbKittiesLivingAround(line, column) == 3;
	}
}
