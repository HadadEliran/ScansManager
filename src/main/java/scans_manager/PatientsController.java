package scans_manager;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;

@RestController
@Service
@RequestMapping("/patients")
public class PatientsController
{
	private DbConnection connection;

	public PatientsController(DbConnection connection)
	{
		this.connection = connection; 
	}
	
	@RequestMapping(value = "/get_scans", method = RequestMethod.GET)
	public String getPatientScans(HttpServletRequest request, HttpServletResponse response,@RequestBody String id)
	{
		try
		{
			List<Scan> scans = connection.getPetientScans(Integer.valueOf(id));
			JsonArray scansAsJsons = new JsonArray();

			scans.stream().forEach(scan -> scansAsJsons.add(scan.toJsonObject()));
			
			response.setStatus(HttpServletResponse.SC_OK);
			return scansAsJsons.toString();
		}
		catch (SQLException e)
		{
			response.setStatus(404);
			return "{error_msg:" + e.getMessage() + "}";

		}
	}
	
	
	
	

}
