//package declaration
package ch.nolix.common.SQL;

//Java imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//own imports
import ch.nolix.common.constants.CharacterCatalogue;
import ch.nolix.common.constants.IPv4Catalogue;
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.containers.ReadContainer;
import ch.nolix.common.validator.Validator;
import ch.nolix.common.wrapperException.WrapperException;

//class
public abstract class SQLConnection implements AutoCloseable {
	
	//attributes
	private final SQLDatabaseEngine SQLDatabaseEngine;
	private final Connection connection;
	
	//constructor
	public SQLConnection(
		final SQLDatabaseEngine SQLDatabaseEngine,
		final Connection connection
	) {
		
		Validator.assertThat(SQLDatabaseEngine).isOfType(SQLDatabaseEngine.class);
		Validator.assertThat(connection).isOfType(Connection.class);
		
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
		
		Validator.assertThat(SQLDatabaseEngine).isOfType(SQLDatabaseEngine.class);
		
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
			throw new WrapperException(SQLException);
		}
	}
	
	//method
	@Override
	public final void close() {
		try {
			connection.close();
		}
		catch (SQLException SQLException) {
			throw new WrapperException(SQLException);
		}
	}
	
	//method
	public final SQLExecutor createSQLExecutor() {
		return new SQLExecutor(this);
	}
	
	//method
	public final SQLConnection execute(final Iterable<String> SQLStatements) {	
		
		try {
			
			final var statement = connection.createStatement();
			
			for (final var SQLS : SQLStatements) {
				statement.addBatch(SQLS);
			}
			
			statement.executeBatch();		
		} catch (final SQLException SQLException) {
			throw new WrapperException(SQLException);
		}
		
		return this;
	}
	
	//method
	public final SQLConnection execute(final String SQLStatement) {
		
		try {
			connection.createStatement().execute(SQLStatement);
		} catch (final SQLException SQLException) {
			close();
			throw new WrapperException(SQLException);
		}
		
		return this;
	}
	
	//method
	public final SQLConnection execute(final String... SQLStatements) {
		return execute(new ReadContainer<String>(SQLStatements));
	}
	
	//method
	public final String getResult(final String SQLQuery) {
		return getRows(SQLQuery).toString(CharacterCatalogue.SEMICOLON);
	}
	
	//method
	public final LinkedList<LinkedList<String>> getRows(final String SQLQuery) {
		try {
			
			final var rows = new LinkedList<LinkedList<String>>();
			
			final var result = connection.createStatement().executeQuery(SQLQuery);
			final var columnCount = result.getMetaData().getColumnCount();
			
			while (result.next()) {
				final var line = new LinkedList<String>();
				for (var i = 1; i <= columnCount; i++) {
					line.addAtEnd(result.getString(i));
				}
				rows.addAtEnd(line);
			}
			
			return rows;
		} catch (SQLException SQLException) {
			throw new WrapperException(SQLException);
		}
	}
	
	//method
	public final LinkedList<String> getRowsAsString(final String SQLQuery) {
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
	
	//method declaration
	protected abstract String getSQLDatabaseEngineDriverClass();
	
	//method
	private void registerSQLDatabaseEngineDriver() {
		try {
			Class.forName(getSQLDatabaseEngineDriverClass());
		}
		catch (final ClassNotFoundException classNotFoundException) {
			throw new WrapperException(classNotFoundException);
		}
	}
}
