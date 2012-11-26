public class Computation
{
	private KittyCluster cluster;
	private KittyHistory kittyhistory;

	public Computation(KittyCluster c)
	{
		this.cluster = c;
		this.kittyhistory = new KittyHistory();
	}

	public void kitty_life_game()
	{
		int start;
		int end_columns;
		int end_line;

		for(start = 1 ; start < 16 ; start++)
		{
			end_columns = (cluster.getNbColumns()) - start;
			end_line = (cluster.getNbLines()) - start;

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
			this.kittyhistory.addSnapshot(this.cluster.getCopy());
			this.cluster.displayKittyCluster();
			try { java.lang.Thread.sleep(1000); } catch(Exception e) {}
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
		//System.out.println("line : " + line + " column : " + column);
		return nbKittiesLivingAround(line, column) == 3;
	}

	public static KittyCluster getNewRandomKittyCluster()
	{
		java.util.Random rd = new java.util.Random();
		int NB_BLOCK = 1;
		int[] entiers_random = new int[32];

		Block[][] b = new Block[NB_BLOCK][NB_BLOCK];
		for(int i = 0 ; i < NB_BLOCK ; i++)
			for(int j = 0 ; j < NB_BLOCK ; j++)
			{
				for(int k = 0 ; k < 32 ; k++)
				{
					entiers_random[k] = rd.nextInt() ;
					System.out.print("" + entiers_random[k] + " ");
				}

				b[i][j] = new Block(entiers_random);
			}

		return new KittyCluster(b);
	}

	public void displayHistory()
	{
		int k = 0;
		for(KittyCluster kc : this.kittyhistory.getHistory())
		{
			System.out.println("## " + k++ + " ##");
			//System.out.println("NbLignes : " + kc.getNbLines() + " nbcolonnes : " + kc.getNbColumns());
			kc.displayKittyCluster();
		}
	}

	public static void main(String[] args)
	{
		KittyCluster kc = getNewRandomKittyCluster();
		//kc.displayKittyCluster();
		Computation compute = new Computation(kc);
		compute.kitty_life_game();
		//compute.displayHistory();
	}
}
