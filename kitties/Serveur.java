import javax.swing.*;

public class Serveur
{
	public static int NB_BLOCKS = 8;
	private JFrame jf = null;
	private Vue v = null;

	private void prepareDisplay()
	{
		v = new Vue();
		jf = new JFrame();

		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(v);
		jf.setTitle("Kill Kitties !");

		// taille = nombre de blocks (KittyCluster = carré) * 
		// nb colonnes (ou lignes, un bloc = un carré aussi) * 
		// taille voulue pour un chatton à l'affichage
		jf.setSize(NB_BLOCKS * Block.BLOCK_SIZE * Vue.TAILLE_BLOCS + 500, 
				NB_BLOCKS * Block.BLOCK_SIZE * Vue.TAILLE_BLOCS + 30);

		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}

	public static void main(String[] args)
	{
		prepareDisplay();

		KittyCluster kc = KittyCluster.getNewRandomKittyCluster(NB_BLOCKS);
		v.display(kc);
		Computation compute = new Computation(kc);
		compute.kitty_life_game(16);

		/*
		for(int i = 0 ; ; i++)
		{
			v.display(.getClick(i));
			try { java.lang.Thread.sleep(1000); } catch(Exception e) {}
		}
		*/
		for(KittyCluster kcluster : compute.getHistory().getHistory())
		{
			v.display(kcluster);
			try { java.lang.Thread.sleep(500); } catch(Exception e) {}
		}
	}
}
