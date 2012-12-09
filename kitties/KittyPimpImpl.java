import java.rmi.*;
import java.util.*;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class TheHungUpTimer implements ActionListener
{
	private KittyPimpImpl kpi = null;
	public TheHungUpTimer(KittyPimpImpl k)
	{
		this.kpi = k;
	}

	public void actionPerformed(ActionEvent e)
	{
		kpi.run();
	}
}

//Like an IRL Pimp, this class manages Kitties and gives them out
//to clients in need of satisfaction
public class KittyPimpImpl extends UnicastRemoteObject 
implements KittyPimp 
{
	private class Task
	{
		public int startX, startY, sizeX, sizeY;
		public Task(int startX, int startY, int sizeX, int sizeY)
		{
			this.startX = startX;
			this.startY = startY;
			this.sizeX = sizeX;
			this.sizeY = sizeY;
		}
	}

	private KittyCluster subjects;
	private KittyCluster[] genocideResults = new KittyCluster[Block.BLOCK_SIZE];
	private GlobalCarnageHistory achievements;
	private ArrayList<Task> tasks = new ArrayList<Task>();
	private int lastLicense = 0;
	private HashMap<Integer,Task> assignments = new HashMap<Integer, Task>();
	private TheHungUpTimer hungUp = new TheHungUpTimer(this);
	javax.swing.Timer someoneProbablyHungUp = new javax.swing.Timer(15000, hungUp);

	public void run()
	{
		System.out.println("Client don't sent what I'm looking for !");
		for(Task t: assignments.values())
		{
			tasks.add(t);
		}
		assignments.clear();
	}

	public KittyPimpImpl(KittyCluster subjects) throws RemoteException
	{
		this.subjects = subjects;
		achievements = GlobalCarnageHistory.getInstance();
		createTasks();
		for(int i = 0; i < Block.BLOCK_SIZE; i++)
		{
			genocideResults[i] = subjects.getCopy();
		}
		someoneProbablyHungUp.start();
	}
	public int gimmeLicenseToKill() throws RemoteException
	{
		lastLicense++;
		return lastLicense;
	}
	private int calculateNumberOfcolumns()
	{
		int n;
		for(n = 1;n*n< lastLicense*2; n++);
		if(n > subjects.getNbBlockLines()) n = subjects.getNbBlockLines();
		if(n > subjects.getNbBlockColumns()) n = subjects.getNbBlockLines();
		return n;
	}
	private void createTasks()
	{
		int numberOfLines = calculateNumberOfcolumns();
		//On calcule la taille des blocs que les clients doivent calculer
		int sizeLine = subjects.getNbBlockLines() / numberOfLines;
		int sizeColumn = subjects.getNbBlockColumns() / numberOfLines;
		// On créer tout les tasks
		for(int i = 0; i <  subjects.getNbBlockLines(); i+= sizeLine)
		{
			for(int j = 0; j < subjects.getNbBlockColumns(); j += sizeColumn)
			{
				// on s'occupe de la derniere colonne et ligne, dans le
				// cas ou c'est pas un multiple.
				int tmpSizeLine = sizeLine;
				int tmpSizeColumn = sizeColumn;
				if(i + sizeLine > subjects.getNbBlockLines())
				{
					tmpSizeLine = subjects.getNbBlockLines() - i;
				}
				if(j + sizeColumn > subjects.getNbBlockColumns())
				{
					tmpSizeColumn = subjects.getNbBlockColumns() - j;
				}
				tasks.add(new Task(i, j, tmpSizeLine, tmpSizeColumn));
			}
		}
	}
	public KittyCluster gimmeKittiesToKill(int license) throws RemoteException
	{
		KittyCluster victims = null;
		//récuperation de la tache à faire.
		if(tasks.size() != 0)
		{
			Task assignment = tasks.get(0);
			tasks.remove(0);
			assignments.put(license, assignment);

			victims = subjects.getCustomSubCopy(assignment.startX, assignment.startY,
					assignment.sizeX, assignment.sizeY);

		}
		else
		{
			someoneProbablyHungUp.start();
		}

		return victims;
	}

	public KittyCluster gimmeSnapshotOfMassacre(int snapshot_number)
		throws RemoteException
		{
			return GlobalCarnageHistory.getSnapshot(snapshot_number);
		}

	public void resultsOfTheGenocide(KittyHistory results, int license)
		throws RemoteException
		{
			someoneProbablyHungUp.stop();
			someoneProbablyHungUp.restart();
			//gerer les resultats
			Task assignment = assignments.get(license);

			if(assignment != null)
			{
				System.out.println("Assignment: "+ assignment.startX + " " + assignment.sizeX
						+ " " + assignment.startY + " " + assignment.sizeY);
				//pour chacune des 32 frames
				for(int i = 0; i < Block.BLOCK_SIZE ; i++)
				{
					Block[][] currentFrameToStore = results.getHistory().get(i).getCopyCluster();
					//pour chacune des lignes
					for(int j = 0; j < assignment.sizeX; j++)
					{
						System.arraycopy( currentFrameToStore[j+1], 1, genocideResults[i].cluster[
								assignment.startX + j], assignment.startY, assignment.sizeY);
					}
				}
				assignments.remove(license);
			}
			//lancement du calcul des taches suivantes
			if(tasks.size() == 0 && assignments.size() == 0)
			{
				System.out.println("everything has been calculated !, lets create the new batch");
				for(int i = 0; i < Block.BLOCK_SIZE ; i++)
				{
					achievements.addNextClick(genocideResults[i]); 
				}
				subjects = genocideResults[genocideResults.length -1].getCopy();
				createTasks();
				System.out.println("endoftheshit");
			}
		}
}
