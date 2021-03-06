public class Block implements java.io.Serializable
{
	public static int BLOCK_SIZE = 32;
	// tableau de 32 * 32 bits 
	private int[] grid = new int[BLOCK_SIZE];

	public Block() {}

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
        if(value)
        {
            current_value |= (1 << column);
        }
        else
        {
            current_value &= (0xFFFFFFFF ^ (1 << column));
        }
        return current_value;
	}
    public Block getBlockCopy()
    {
        return new Block(getCopy());
    }
	public int[] getCopy()
	{
		/*
		int[] copy = new int[grid.length];
		for(int i = 0 ; i < 
		*/
		return java.util.Arrays.copyOf(grid, grid.length);
	}
    public static void main(String[] args)
    {
        int[] tmp = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        Block b = new Block(tmp);
        b.setValue(0,3, true);
        System.out.println(b.getCopy()[0] + " " + b.getValue(0,3));
    }
}
