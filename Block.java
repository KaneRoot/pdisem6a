public class Block
{
	public static int BLOCK_SIZE = 32;
	// tableau de 32 * 32 bits 
	private int[] grid = new int[BLOCK_SIZE];
	public Block(int[] tab)
	{
		for(int i = 0 ; i < BLOCK_SIZE ; i++)
			grid[i] = tab[i];
	}

	public boolean getValue(int line, int column)
	{
		return ((grid[line] >> column)& 1) == 1;
	}

	public void setValue(int line, int column, boolean value)
	{
		grid[line] = setBitTo(grid[line], column, value);
	}

	private int setBitTo(int current_value, int column, boolean value)
	{
		int tmp = value ? 1 : 0;
		return current_value & (0xFFFFFFFF & (tmp << column));
	}
}
