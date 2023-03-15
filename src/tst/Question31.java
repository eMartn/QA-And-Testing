import org.junit.Assert;
import org.junit.Test;

import java.sql.*;

public class Question31
	{
	
		
		@Test
		public void testNumOfVehicles() throws SQLException
			{
		
			//You are given a Connection object called "c" from the setup() method
			
			Connection c = DriverManager.getConnection("jdbc:sqlite:test.db");
			
			// your code goes here
			// gets the number of rows in the Vehicles table.
			String sql = "select count(*) from vehicles";
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			rs.next();
			int count = rs.getInt("count(*)");
			
			Assert.assertEquals(500, count);
			
		}
	}
