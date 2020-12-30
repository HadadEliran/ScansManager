package scans_manager;

import java.sql.Date;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class Scan
{
	public int id;
	public int patientId;
	public int hospitalId;
	public Date timestamp;
	
	public JsonObject toJsonObject()
	{
		JsonObject obj = new JsonObject();

		obj.add("id", new JsonPrimitive(id));
		obj.add("patientId", new JsonPrimitive(id));
		obj.add("hospitalId", new JsonPrimitive(id));
		obj.add("timestamp", new JsonPrimitive(timestamp.toString()));
		
		return obj;
	}
}
