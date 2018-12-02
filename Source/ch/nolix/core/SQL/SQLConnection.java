//package declaration
package ch.nolix.core.SQL;

//Java imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//own imports
import ch.nolix.core.constants.IPv6Catalogue;
import ch.nolix.core.validator2.Validator;

//abstract class
public abstract class SQLConnection {
	
	//attributes
	private final SQLDatabaseEngine SQLDatabaseEngine;
	private final Connection connection;
	
	//constructor
	public SQLConnection(
		final SQLDatabaseEngine SQLDatabaseEngine,
		final Connection connection
	) {
		Validator.suppose(SQLDatabaseEngine).isInstanceOf(SQLDatabaseEngine.class);
		Validator.suppose(connection).isInstanceOf(Connection.class);
		
		this.SQLDatabaseEngine = SQLDatabaseEngine;
		this.connection = connection;
	}
	
	//constructor
	public SQLConnection(
		final SQLDatabaseEngine SQLDatabaseEngine,
		final int port,
		final String databaseName,
		final String userName,
		final String userPassword
	) {
		this(
			SQLDatabaseEngine,
			IPv6Catalogue.LOOP_BACK_ADDRESS,
			port,
			databaseName,
			userName,
			userPassword
		);
	}
	
	//constructor
	public SQLConnection(
		final SQLDatabaseEngine SQLDatabaseEngine,
		final String ip,
		final int port,
		final String databaseName,
		final String userName,
		final String userPassword
	) {
		Validator.suppose(SQLDatabaseEngine).isInstanceOf(SQLDatabaseEngine.class);
		
		this.SQLDatabaseEngine = SQLDatabaseEngine;
		
		registerSQLDatabaseEngineDriver();
		try {
			
			
			connection =
			DriverManager.getConnection(
				"jdbc:sqlserver://" + ip + ':'+ port + ";database=" + databaseName,
				userName,
				userPassword
			);
		}
		catch (final SQLException SQLException) {
			throw new RuntimeException(SQLException);
		}
	}
	
	//method
	public final SQLExecutor createSQLExecutor() {
		return new SQLExecutor(this);
	}
	
	//method
	public final SQLConnection execute(final String SQLStatement) {
		//TODO: Handle the execution of a statement as transaction.
		try {
			
			connection.createStatement().execute(SQLStatement);
			
			return this;
			
		} catch (final SQLException SQLException) {
			throw new RuntimeException(SQLException);
		}
	}
	
	//method
	public final String getResult(final String SQLQuery) {
		try {
			return connection.createStatement().executeQuery(SQLQuery).toString();
		} catch (SQLException SQLException) {
			throw new RuntimeException(SQLException);
		}
	}
	
	//method
	public final SQLDatabaseEngine getSQLDatabaseEngine() {
		return SQLDatabaseEngine;
	}
	
	//method
	public final boolean tableExistsOnDatabase(final String name) {
		return	
		getResult(
			"SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = '"
			+ name
			+ "'"
		)
		.equals("1");
	}
	
	//abstract method
	protected abstract String getSQLDatabaseEngineDriverClass();
	
	//method
	private void registerSQLDatabaseEngineDriver() {
		try {
			Class.forName(getSQLDatabaseEngineDriverClass());
		}
		catch (final ClassNotFoundException classNotFoundException) {
			throw new RuntimeException(classNotFoundException);
		}
	}
}
