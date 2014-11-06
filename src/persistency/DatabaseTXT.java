package persistency;

import java.io.File;

public class DatabaseTXT extends LeesSafeAlhoritme implements IDatabaseStrategy
{

	public DatabaseTXT() {}

	@Override
	public void leesOpdrachten()
	{
		super.file = new File("bestanden\\opdrachten.txt");
		super.leesVanTXTBestand();
		
		
	}

	@Override
	public void leesQuzen()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leesQuizOpdrachten()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void kopelQuizOpdrachten()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void safeOpdrachten()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void safeQuizen()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void safeQuizOpdrachten()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	void setVeld(String veld)
	{
		// TODO Auto-generated method stub
		
	}

}
