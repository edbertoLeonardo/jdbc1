package application;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;
import db.DbIntegrityException;


public class Program {



	public static void main(String[] args) {

		Connection connection = null;
		Statement statement = null;

		try {
			
			connection = DB.getConnection();
			connection.setAutoCommit(false);
			
			statement = connection.createStatement();
			
			int rows1 = statement.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
			/*	int x =1;
			if (x < 2) {
				throw new SQLException("Fake error");
			}
		*/	
			int rows2 = statement.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

			connection.commit();
			
			System.out.println("Rows1 " + rows1);
			System.out.println("Rows2 " + rows2);
		}

		catch (SQLException e) {
			try {
				connection.rollback();
				throw new DbException("Transaction rolled back! Cause by: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Error trying to rollback! Causer by: " + e.getSQLState());
			}

		} 

		finally {
			DB.closeStatement(statement);
			DB.closeConnection();
		}

	}

}