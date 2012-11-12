public class Computation
{
	private KittyCluster cluster;

	public Computation(KittyCluster c)
	{
		this.cluster = c;
	}

	public void kitty_life_game()
	{
		int debut = 1, fin = 31;
		for(int i = 1 ; i < 31 ; i++)
			for(int j = 1 ; j < 31 ; j++)
			{
				if(cluster.isKittyAlive(line, column))
				{
					if(shouldKittyDie(line, column))
						cluster.raiseKitty(line, column);
				}
				else if(shouldKittyRaise(line, column))
					cluster.killKitty(line, column);
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
