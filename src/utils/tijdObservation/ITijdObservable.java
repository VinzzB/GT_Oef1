package utils.tijdObservation;

public interface ITijdObservable
{
	void addObserver(ITijdObserver tijdObserver);
	void removeObserver(ITijdObserver tijdObserver);
	void notifyObservers();
}
