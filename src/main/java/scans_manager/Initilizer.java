package scans_manager;

import java.sql.SQLException;

public class Initilizer
{
	public void init()
	{
		try
		{
			DbConnection connection = new DbConnection();
			connection.connect();
			
			PatientsController controller = new PatientsController(connection);
			
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
