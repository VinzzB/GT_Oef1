package utils.tijdObservation;

/**
 * Timer om antwoordTijd te volgen
 * 
 * @author Natalia Dyubankova
 * 
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.Timer;

public class AntwoordStopwatch implements ITijdObservable
{
	private int antwoordTijd = 0;
	private Timer timer;
	
	private Set<ITijdObserver> observers;

	public AntwoordStopwatch()
	{
		this.timer = new Timer(1000, new TimerListener());
		this.observers = new HashSet<ITijdObserver>();
		timer.start();
	}
	

	/**
	 * @return the antwoordTijd
	 */
	public int getAntwoordTijd()
	{
		return this.antwoordTijd;
	}
	
	public void stop()
	{
		timer.stop();
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
	
	class TimerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			antwoordTijd++;
			notifyObservers();
		}}
}
