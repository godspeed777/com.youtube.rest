package com.youtube.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class OracleDB {

	private static DataSource dsOracle = null;
	private static Context context = null;
	
	
	public static DataSource oracleConnect() throws Exception {
		if(dsOracle !=null){
			return dsOracle;
		}
		
		try {
			if(context == null){
				context = new InitialContext();
			}
			
			dsOracle = (DataSource) context.lookup("godspeed777Oracle");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsOracle;
	}
}
