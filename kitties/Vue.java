import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.*;

@SuppressWarnings("serial")
public class Vue extends JPanel
{
	public static int TAILLE_BLOCS = 2;
	public KittyCluster kitties = null;
	public Vue() { super(); }

	public void display(KittyCluster kc)
	{
		this.kitties = kc;
		this.repaint();
	}

	public void paint(Graphics g)
	{
		// --------------------------
		// Dessin de l'arrière plan
		//g.setColor(new Color(140,140,140));
		//g.fillRect(0, 0, this.getWidth(), this.getHeight());

		int x = 0, y = 0;

		for(int i = 0 ; i < kitties.getNbLines() ; i++)
		{
			for(int j = 0 ; j < kitties.getNbColumns() ; j++)
			{
				if(kitties.isKittyAlive(i, j))
					g.setColor(new Color(0,255,0));
				else
					g.setColor(new Color(255,0,0));
				g.fillRect(x, y, x+TAILLE_BLOCS, y+TAILLE_BLOCS);
				x += TAILLE_BLOCS;
			}
			y += TAILLE_BLOCS;
			x = 0;
		}

		g.setColor(new Color(0,0,0));
		String message = "Little kitties in the slaughter <3";
		g.drawString(message, getWidth() - ( message.length() + 5)*6 , 35);
	}

	public static void main(String[] args)
	{
		Vue v = new Vue();
		KittyCluster kc = Computation.getNewRandomKittyCluster();
		JFrame test = new JFrame();
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.add(v);
		v.display(kc);
		test.setTitle("Kill Kitties !");
		test.setSize(kc.getNbColumns() * Vue.TAILLE_BLOCS + 500, kc.getNbLines() * Vue.TAILLE_BLOCS + 30);
		test.setLocationRelativeTo(null);
		test.setVisible(true);

		Computation compute = new Computation(kc);
		compute.kitty_life_game();
		//compute.displayHistory();
		for(KittyCluster kcluster : compute.getHistory().getHistory())
		{
			v.display(kcluster);
			try { java.lang.Thread.sleep(1000); } catch(Exception e) {}
		}
	}
}
