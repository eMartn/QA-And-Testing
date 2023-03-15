import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.*;

public class DatabaseTest
    {

        public static final String dbURL = "jdbc:sqlite:test.db";
        private static Connection connection;

        @BeforeClass
        public static void setUp() throws SQLException
        {

            connection = DriverManager.getConnection(dbURL);
        }

        @Test
        public void testAddRow() throws SQLException
        {
            Assert.assertTrue(getNumOfRows() > 0);
            String sql = "insert into COMPANY values (?, ?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 100);


        }

        public int getNumOfRows() throws SQLException
        {
            String sql = "select count(*) from COMPANY";
            Statement statement = connection.createStatement();
            statement.executeQuery(sql);
            ResultSet result = statement.executeQuery(sql);
            result.next();
            return result.getInt("count(*)");
        }

        @AfterClass
        public static void cleanUp() throws SQLException
        {
            connection.close();
        }

    }
