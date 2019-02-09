//package declaration
package ch.nolix.core.SQL;

//Java imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//own imports
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.core.constants.IPv4Catalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.validator.Validator;

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
		Validator.suppose(SQLDatabaseEngine).isOfType(SQLDatabaseEngine.class);
		Validator.suppose(connection).isOfType(Connection.class);
		
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
			IPv4Catalogue.LOOP_BACK_ADDRESS,
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
		Validator.suppose(SQLDatabaseEngine).isOfType(SQLDatabaseEngine.class);
		
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
		return getRows(SQLQuery).toString(CharacterCatalogue.SEMICOLON);
	}
	
	//method
	public final List<List<String>> getRows(final String SQLQuery) {
		try {
			
			final var rows = new List<List<String>>();
			
			final var result = connection.createStatement().executeQuery(SQLQuery);
			final var columnCount = result.getMetaData().getColumnCount();
			
			while (result.next()) {
				final var line = new List<String>();
				for (var i = 1; i <= columnCount; i++) {
					line.addAtEnd(result.getString(i));
				}
				rows.addAtEnd(line);
			}
			
			return rows;
		} catch (SQLException SQLException) {
			throw new RuntimeException(SQLException);
		}
	}
	
	//method
	public final List<String> getRowsAsString(final String SQLQuery) {
		return getRows(SQLQuery).toStrings();
	}
	
	//method
	public final SQLDatabaseEngine getSQLDatabaseEngine() {
		return SQLDatabaseEngine;
	}
	
	//method
	public final boolean tableExistsOnDatabase(final String name) {
		return
		getRows(
			"SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = '"
			+ name
			+ "'"
		)
		.containsAny();
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
