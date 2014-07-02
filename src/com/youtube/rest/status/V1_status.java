package com.youtube.rest.status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.youtube.dao.OracleDB;

@Path("/v1/status")
public class V1_status {

	private static final String api_version ="00.01.00";
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle(){
		return "<p>Java Web Service</p>";
	}
	
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion(){
		return "<p>version:</p>" + api_version;
	}
	
	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseStatus() throws Exception{
		
		PreparedStatement query = null;
		String myString = null;
		String returnString = null;
		Connection conn = null; 
		
		try {
			
			conn = OracleDB.oracleConnect().getConnection();
			query = conn.prepareStatement("SELECT to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') DATETIME " +
					"FROM dual");
			
			ResultSet rs = query.executeQuery();
			
			while (rs.next()) {
				myString = rs.getString("DATETIME");
				
			}
			query.close();
			
			returnString = "<p> Database Status</p>" + 
						"<p> Database Date/Time return: " + myString + "</p>";
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
		
			if(conn !=null) conn.close();
		}
		
		return returnString;
		
	}
}
