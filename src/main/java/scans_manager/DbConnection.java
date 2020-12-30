package scans_manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbConnection
{
	private String serverHost;
	private int port;
	private String dbName;
	private String user;
	private String password;
	private String connectionString;
	
	private Connection connection;

	public DbConnection()
	{
		serverHost = "sql7.freemysqlhosting.net";
		port = 3306;
		dbName = "sql7384156";
		user = "sql7384156";
		password = "h6Q1U9x6ii";

		connectionString = String.format("jdbc:mysql://%s:%d/%s", serverHost, port, dbName);
	}

	public void connect() throws SQLException
	{
		connection = DriverManager.getConnection(connectionString, user, password);
	}
	
	public List<Scan> getPetientScans(int patientId) throws SQLException
	{
		ArrayList<Scan> scans = new ArrayList<>();
		
		String query = "SELECT id,hospital_id,timestamp FROM scans WHERE patient_id=?";
		
		try (PreparedStatement stat = connection.prepareStatement(query))
		{
			stat.setInt(1, patientId);
			try (ResultSet results = stat.executeQuery())
			{
				while (results.next())
				{
					Scan scan = new Scan();
					
					scan.patientId = patientId;
					scan.id = results.getInt(1);
					scan.hospitalId = results.getInt(2);
					scan.timestamp = results.getDate(3);
					
					scans.add(scan);
				}
			}
		}
		
		return scans;
	}

	public void disconnect() throws SQLException
	{
		if (connection != null && !connection.isClosed())
		{
			connection.close();
		}

	}
}
