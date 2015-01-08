package persistency;

import java.lang.reflect.InvocationTargetException;

public class Test
{

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException
	{
		try
		{
			DatabaseHandler db = new DatabaseHandler();
		} 
		catch 
		(IllegalArgumentException | SecurityException e)
		{
			e.printStackTrace();
		}
	}

}
