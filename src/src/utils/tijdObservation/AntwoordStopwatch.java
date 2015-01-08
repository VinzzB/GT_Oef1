package src.utils.tijdObservation;

/**
 * Timer om antwoordTijd te volgen
 * 
 * @author Natalia Dyubankova
 * 
 */
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import model.Opdracht;

public class AntwoordStopwatch implements ITijdObservable
{
	private int antwoordTijd = 0;
	private int maxAntwoordTijd;
	private Timer timer;
	
	private Set<ITijdObserver> observers;

	public AntwoordStopwatch()
	{
		this.timer = new Timer();
		this.timer.schedule(new Task(), 1000);
		this.observers = new HashSet<ITijdObserver>();
	}
	public AntwoordStopwatch(Opdracht opdracht)
	{
		this();
		this.maxAntwoordTijd = opdracht.getMaxAntwoordTijdinSec();
	}
	

	/**
	 * @return the antwoordTijd
	 */
	public int getAntwoordTijd()
	{
		return this.antwoordTijd;
	}

	@Override
	public void addObserver(ITijdObserver tijdObserver)
	{
		this.observers.add(tijdObserver);
	}
	@Override
	public void removeObserver(ITijdObserver tijdObserver)
	{
		if(this.observers.contains(tijdObserver))
			this.observers.remove(tijdObserver);
	}
	@Override
	public void notifyObservers()
	{
		Iterator<ITijdObserver> it = this.observers.iterator();
		while(it.hasNext())
		{
			ITijdObserver t = it.next();
			t.handleTijdNotification(this);
		}
	}
	
	private class Task extends TimerTask
	{
		@Override
		public void run() 
		{
			while(antwoordTijd < maxAntwoordTijd)
			{
				antwoordTijd++; 				
			}
			if (antwoordTijd >= maxAntwoordTijd)
			{
				notifyObservers();
			}
		}
	}

}
