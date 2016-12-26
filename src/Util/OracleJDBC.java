package util;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.detail.FlightSearchDetail;

public class OracleJDBC {

	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@10.224.164.14:1521:vnswtest";
	private static final String DB_USER = "sh_hangkhong";
	private static final String DB_PASSWORD = "SH_HANGKHONG";

	private static Connection getDBConnection() {

		Connection dbConnection = null;

		try {

			Class.forName(DB_DRIVER);

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}

	public void selectRecordsFromDbFlightTable(FlightSearchDetail flightSearchDetail) throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;

		String flightDate = flightSearchDetail.getFlightDate();
		String airlinesCode = flightSearchDetail.getAirlinesCode();
		String flightNumber = flightSearchDetail.getFlightNumber();
		String departureCode = flightSearchDetail.getDepartureCode();
		String destinationCode = flightSearchDetail.getDestinationCode();
		String gate = flightSearchDetail.getGate();
		String flightType = flightSearchDetail.getFlightType();

		// String selectTableSQL = "SELECT * " + "FROM TBDCHUYENBAY "
		// + "WHERE TO_DATE(FI_NGAYBAY, 'dd-MON-yy') = TO_DATE('" + flightDate +
		// "', 'dd/mm/yyyy') "
		// + "AND FI_MAHANGHANGKHONG = '" + airlinesCode + "' AND FI_SOCHUYENBAY
		// LIKE '%" + flightNumber
		// + "%' AND FI_MACANGHANGKHONGDI = '" + departureCode + "' AND
		// FI_MACANGHANGKHONGDEN = '"
		// + destinationCode + "' AND FI_CUA = '" + gate + "' AND FI_LOAIDIDEN =
		// " + flightType;
		//
		String selectTableSQL = "SELECT * " + "FROM TBDCHUYENBAY "
				+ "WHERE TO_DATE(FI_NGAYBAY, 'dd-MON-yy') = TO_DATE('" + flightDate + "', 'dd/mm/yyyy') "
				+ "AND FI_MAHANGHANGKHONG = '" + airlinesCode + "'AND FI_SOCHUYENBAY LIKE '%" + flightNumber
				+ "%' AND FI_MACANGHANGKHONGDI = '" + departureCode + "' AND FI_MACANGHANGKHONGDEN = '"
				+ destinationCode + "' AND FI_CUA = " + gate + " AND FI_LOAIDIDEN = " + 0;

		try {

			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(selectTableSQL);

			// execute select SQL stetement
			ResultSet rs = statement.executeQuery(selectTableSQL);

			while (rs.next()) {
				String ngaybay = rs.getString("FI_NGAYBAY");
				String mahanghangkhong = rs.getString("FI_MAHANGHANGKHONG");
				String tenhanghangkhong = rs.getString("FI_TENHANGHANGKHONG");

				System.out.println("ngaybay: " + ngaybay);
				System.out.println("mahanghangkhong: " + mahanghangkhong);
				System.out.println("tenhanghangkhong: " + tenhanghangkhong);
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

	}
}
