//package declaration
package ch.nolix.core.SQL;

//Java imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//own imports
import ch.nolix.core.constants.IPv6Catalogue;
import ch.nolix.core.validator2.Validator;

//class
public final class SQLConnection {

	//attribute
	private final Connection connection;
	
	//constructor
	public SQLConnection(final Connection connection) {
		
		Validator.suppose(connection).isInstanceOf(Connection.class);
		
		this.connection = connection;
	}
	
	//constructor
	public SQLConnection(
		final int port,
		final String databaseName,
		final String userName,
		final String userPassword
	) {
		this(IPv6Catalogue.LOOP_BACK_ADDRESS, port, databaseName, userName, userPassword);
	}
	
	//constructor
	public SQLConnection(
		final String ip,
		final int port,
		final String databaseName,
		final String userName,
		final String userPassword
	) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection =
			DriverManager.getConnection(
				"jdbc:sqlserver://" + ip + ':'+ port + ";database=" + databaseName,
				userName,
				userPassword
			);
		}
		catch (final Exception exception) {
			throw new RuntimeException(exception);
		}
	}
	
	//method
	public SQLExecutor createSQLExecutor() {
		return new SQLExecutor(this);
	}
	
	//method
	public SQLConnection execute(final String statement) {
		//TODO: Handle the execution of a statement as transaction.
		try {
			
			connection.createStatement().execute(statement);
			
			return this;
			
		} catch (SQLException SQLException) {
			throw new RuntimeException(SQLException);
		}
	}
	
	//method
	public String getResult(final String query) {
		try {
			return connection.createStatement().executeQuery(query).toString();
		} catch (SQLException SQLException) {
			throw new RuntimeException(SQLException);
		}
	}
	
	//method
	public boolean tableExistsOnDatabase(final String name) {
		return	
		getResult("SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = '" + name + "'")
		.equals("1");
	}
}
