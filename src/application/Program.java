package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import db.DB;

public class Program {

	public static void main(String[] args) {

		Connection connection = null;
		Statement statement = null;
		ResultSet resultset = null;

		try {
			connection = DB.getConnection();
			statement = connection.createStatement();
			resultset = statement.executeQuery("select * from department");
			while (resultset.next()) {
				System.out.println(resultset.getInt("Id") + ", " + resultset.getString("Name"));
			}

		}

		catch (SQLException e) {

			e.printStackTrace();

		}

		finally {

			DB.closeResultSet(resultset); 
			DB.closeStatement(statement);
			DB.closeConnection();

		}

	}

}