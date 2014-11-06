package persistency;

import java.lang.reflect.InvocationTargetException;

public class Test
{

	public static void main(String[] args)
	{
		try
		{
			DatabaseHandler db = new DatabaseHandler();
		} 
		catch 
		(InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

}
