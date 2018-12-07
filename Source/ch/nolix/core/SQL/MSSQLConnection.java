//package declaration
package ch.nolix.core.SQL;

//Java import
import java.sql.Connection;

//class
public final class MSSQLConnection extends SQLConnection {
	
	//constant
	public static final SQLDatabaseEngine SQL_DATABASE_ENGINE = SQLDatabaseEngine.MSSQL;
	
	//constant
	private static final String MSSQL_DATABASE_ENINGE_DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	//constructor
	public MSSQLConnection(
		final Connection connection
	) {
		super(SQL_DATABASE_ENGINE, connection);
	}
	
	//constructor
	public MSSQLConnection(
		final int port,
		final String databaseName,
		final String userName,
		final String userPassword
	) {
		super(
			SQL_DATABASE_ENGINE,
			port,
			databaseName,
			userName,
			userPassword
		);
	}
	
	//constructor
	public MSSQLConnection(
		final String ip,
		final int port,
		final String databaseName,
		final String userName,
		final String userPassword
	) {
		super(
			SQL_DATABASE_ENGINE,
			ip,
			port,
			databaseName,
			userName,
			userPassword	
		);
	}

	//method
	@Override
	protected String getSQLDatabaseEngineDriverClass() {
		return MSSQL_DATABASE_ENINGE_DRIVER_CLASS;
	}
}
