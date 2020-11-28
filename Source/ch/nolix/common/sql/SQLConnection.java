//package declaration
package ch.nolix.common.sql;

//Java imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//own imports
import ch.nolix.common.constant.CharacterCatalogue;
import ch.nolix.common.constant.IPv4Catalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.validator.Validator;
import ch.nolix.common.wrapperexception.WrapperException;

//class
public abstract class SQLConnection implements AutoCloseable {
	
	//attributes
	private final SQLDatabaseEngine mSQLDatabaseEngine;
	private final Connection connection;
	
	//constructor
	public SQLConnection(final SQLDatabaseEngine pSQLDatabaseEngine, final Connection connection) {
		
		Validator.assertThat(pSQLDatabaseEngine).thatIsNamed(SQLDatabaseEngine.class).isNotNull();
		Validator.assertThat(connection).thatIsNamed(Connection.class).isNotNull();
		
		this.mSQLDatabaseEngine = pSQLDatabaseEngine;
		this.connection = connection;
	}
	
	//constructor
	public SQLConnection(
		final SQLDatabaseEngine pSQLDatabaseEngine,
		final int port,
		final String databaseName,
		final String userName,
		final String userPassword
	) {
		this(
			pSQLDatabaseEngine,
			IPv4Catalogue.LOOP_BACK_ADDRESS,
			port,
			databaseName,
			userName,
			userPassword
		);
	}
	
	//constructor
	public SQLConnection(
		final SQLDatabaseEngine pSQLDatabaseEngine,
		final String ip,
		final int port,
		final String databaseName,
		final String userName,
		final String userPassword
	) {
		
		Validator.assertThat(pSQLDatabaseEngine).thatIsNamed(SQLDatabaseEngine.class).isNotNull();
		
		this.mSQLDatabaseEngine = pSQLDatabaseEngine;
		
		registerSQLDatabaseEngineDriver();
		
		try {
			connection =
			DriverManager.getConnection(
				"jdbc:sqlserver://" + ip + ':'+ port + ";database=" + databaseName,
				userName,
				userPassword
			);
		}
		catch (final SQLException pSQLException) {
			throw new WrapperException(pSQLException);
		}
	}
	
	//method
	@Override
	public final void close() {
		try {
			connection.close();
		}
		catch (SQLException pSQLException) {
			throw new WrapperException(pSQLException);
		}
	}
	
	//method
	public final SQLExecutor createSQLExecutor() {
		return new SQLExecutor(this);
	}
	
	//method
	public final SQLConnection execute(final Iterable<String> pSQLStatements) {
		
		try (final var statement = connection.createStatement()) {
			
			for (final var lSQLStatement : pSQLStatements) {
				statement.addBatch(lSQLStatement);
			}
			
			statement.executeBatch();		
		}
		catch (final SQLException pSQLException) {
			throw new WrapperException(pSQLException);
		}
		
		return this;
	}
	
	//method
	public final SQLConnection execute(final String pSQLStatement) {
		
		try (final var statement = connection.createStatement()) {
			statement.execute(pSQLStatement);
		}
		catch (final SQLException pSQLException) {
			throw new WrapperException(pSQLException);
		}
		
		return this;
	}
	
	//method
	public final SQLConnection execute(final String... pSQLStatements) {
		return execute(ReadContainer.forArray(pSQLStatements));
	}
	
	//method
	public final String getResult(final String SQLQuery) {
		return getRows(SQLQuery).toString(CharacterCatalogue.SEMICOLON);
	}
	
	//method
	public final LinkedList<LinkedList<String>> getRows(final String pSQLQuery) {
		try (final var statement = connection.createStatement()) {
			
			final var rows = new LinkedList<LinkedList<String>>();
			
			try (final var result = statement.executeQuery(pSQLQuery)) {
			
				final var columnCount = result.getMetaData().getColumnCount();
				
				while (result.next()) {
					final var line = new LinkedList<String>();
					for (var i = 1; i <= columnCount; i++) {
						line.addAtEnd(result.getString(i));
					}
					rows.addAtEnd(line);
				}
				
				return rows;
			}
		}
		catch (SQLException pSQLException) {
			throw new WrapperException(pSQLException);
		}
	}
	
	//method
	public final LinkedList<String> getRowsAsString(final String pSQLQuery) {
		return getRows(pSQLQuery).toStrings();
	}
	
	//method
	public final SQLDatabaseEngine getSQLDatabaseEngine() {
		return mSQLDatabaseEngine;
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
