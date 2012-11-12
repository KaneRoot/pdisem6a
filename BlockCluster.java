public class BlockCluster 
{
	Block[][] cluster;
	public BlockCluster(Block[][] blocks) { this.cluster = blocks; }

	public boolean getValue(int line, int column)
	{
		int bloc_x = line / Block.BLOCK_SIZE;
		int bloc_y = column / Block.BLOCK_SIZE;
		return cluster[bloc_x][bloc_y].getValue(
				line % Block.BLOCK_SIZE, column % Block.BLOCK_SIZE);
	}

	public void setValue(int line, int column, boolean value)
	{
		int bloc_x = line / Block.BLOCK_SIZE;
		int bloc_y = column / Block.BLOCK_SIZE;
		cluster[bloc_x][bloc_y].setValue(
				line % Block.BLOCK_SIZE, column % Block.BLOCK_SIZE);
	}
}
