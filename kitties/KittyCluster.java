public class KittyCluster 
{
	Block[][] cluster;
	public KittyCluster(Block[][] blocks) { this.cluster = blocks; }

	public boolean isKittyAlive(int line, int column)
	{
		int bloc_x = line / Block.BLOCK_SIZE;
		int bloc_y = column / Block.BLOCK_SIZE;
		return cluster[bloc_x][bloc_y].getValue(
				line % Block.BLOCK_SIZE, column % Block.BLOCK_SIZE);
	}

	private void setValue(int line, int column, boolean value)
	{
		int bloc_x = line / Block.BLOCK_SIZE;
		int bloc_y = column / Block.BLOCK_SIZE;
		cluster[bloc_x][bloc_y].setValue(
				line % Block.BLOCK_SIZE, column % Block.BLOCK_SIZE,
				value);
	}

	public void raiseKitty(int line, int column)
	{
		setValue(line, column, true);
	}

	public void killKitty(int line, int column)
	{
		setValue(line, column, false);
	}

	public int getNbLines()
	{
		return cluster.length * Block.BLOCK_SIZE;
	}

	public int getNbColumns()
	{
		return cluster[0].length * Block.BLOCK_SIZE;
	}

	public Block[][] getCopyCluster()
	{
		Block[][] copie_cluster = new Block[getNbLines() / Block.BLOCK_SIZE][getNbColumns() / Block.BLOCK_SIZE];
		for(int i = 0 ; i < getNbLines() / Block.BLOCK_SIZE ; i++)
			for(int j = 0 ; j < getNbColumns() / Block.BLOCK_SIZE; j++)
				copie_cluster[i][j] = new Block(this.cluster[i][j].getCopy());
		return copie_cluster;
	}

	public KittyCluster getCopy()
	{
		return new KittyCluster(this.getCopyCluster());
	}

	public void displayKittyCluster()
	{
		for(int i = 0 ; i < this.getNbLines() ; i++)
		{
			for(int j = 0 ; j < this.getNbColumns() ; j++)
			{
				String kitty = (this.isKittyAlive(i,j)) ? "o" : "x";
				System.out.print(kitty);
			}
			System.out.println("");
		}
		System.out.println("");
	}
}
