package application;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import db.DB;
import db.DbIntegrityException;


public class Program {



	public static void main(String[] args) {

		Connection connection = null;

		PreparedStatement prepaedstatement = null;

		try {
			connection = DB.getConnection();

			prepaedstatement = connection.prepareStatement(
					"DELETE FROM department "
					+ "WHERE "
					+ "Id = ?");

			prepaedstatement.setInt(1, 5);

			int rowsAffected = prepaedstatement.executeUpdate();
			System.out.println("Done! Rows affected: " + rowsAffected);
		}

		catch (SQLException e) {

			throw new DbIntegrityException(e.getMessage());

		} 

		finally {
			DB.closeStatement(prepaedstatement);
			DB.closeConnection();
		}

	}

}