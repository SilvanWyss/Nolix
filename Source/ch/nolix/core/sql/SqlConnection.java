//package declaration
package ch.nolix.core.sql;

//Java imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.constant.IPv4Catalogue;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;

//class
public abstract class SqlConnection implements GroupCloseable {
	
	//attribute
	private final SqlDatabaseEngine sqlDatabaseEngine;
	
	//attribute
	private final Connection connection;
	
	//attribute
	private final CloseController closeController = CloseController.forElement(this);
	
	//optional attribute
	private final SqlConnectionPool parentSQLConnectionPool;
	
	//constructor
	protected SqlConnection(final SqlDatabaseEngine sqlDatabaseEngine, final Connection connection) {
		
		GlobalValidator.assertThat(sqlDatabaseEngine).thatIsNamed(SqlDatabaseEngine.class).isNotNull();
		GlobalValidator.assertThat(connection).thatIsNamed(Connection.class).isNotNull();
		
		this.sqlDatabaseEngine = sqlDatabaseEngine;
		this.connection = connection;
		parentSQLConnectionPool = null;
	}
	
	//constructor
	protected SqlConnection(
		final SqlDatabaseEngine sqlDatabaseEngine,
		final int port,
		final String userName,
		final String userPassword
	) {
		this(
			sqlDatabaseEngine,
			IPv4Catalogue.LOOP_BACK_ADDRESS,
			port,
			userName,
			userPassword
		);
	}
	
	//constructor
	protected SqlConnection(
		final SqlDatabaseEngine sqlDatabaseEngine,
		final String ip,
		final int port,
		final String userName,
		final String userPassword
	) {
		
		GlobalValidator.assertThat(sqlDatabaseEngine).thatIsNamed(SqlDatabaseEngine.class).isNotNull();
		
		this.sqlDatabaseEngine = sqlDatabaseEngine;
		
		registerSQLDatabaseEngineDriver();
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlserver://" + ip + ':'+ port, userName, userPassword);
		} catch (final SQLException sqlException) {
			throw WrapperException.forError(sqlException);
		}
		
		parentSQLConnectionPool = null;
	}
	
	//constructor
	protected SqlConnection(
		final SqlDatabaseEngine sqlDatabaseEngine,
		final String ip,
		final int port,
		final String userName,
		final String userPassword,
		final SqlConnectionPool parentSQLConnectionPool
	) {
		
		GlobalValidator.assertThat(sqlDatabaseEngine).thatIsNamed(SqlDatabaseEngine.class).isNotNull();
		GlobalValidator.assertThat(parentSQLConnectionPool).thatIsNamed("parent SQLConnectionPool").isNotNull();
		
		GlobalValidator
		.assertThat(parentSQLConnectionPool)
		.thatIsNamed("parent SQLConnectionPool")
		.fulfills(SqlConnectionPool::isOpen);
		
		this.sqlDatabaseEngine = sqlDatabaseEngine;
		
		registerSQLDatabaseEngineDriver();
		
		try {
			connection =
			DriverManager.getConnection(
				"jdbc:sqlserver://" + ip + ':'+ port,
				userName,
				userPassword
			);
		} catch (final SQLException sqlException) {
			throw WrapperException.forError(sqlException);
		}
		
		this.parentSQLConnectionPool = parentSQLConnectionPool;
	}
	
	//method
	public final boolean belongsToSQLConnectionPool() {
		return (parentSQLConnectionPool != null);
	}
	
	//method
	@Override
	public final void close() {
		if (!belongsToSQLConnectionPool()) {
			GroupCloseable.super.close();
		} else {
			giveBackSelfToParentSQLConnectionPool();
		}
	}
	
	//method
	public final SqlConnection execute(final Iterable<String> sqlStatements) {
		
		try (final var statement = connection.createStatement()) {
			
			connection.setAutoCommit(false);
			
			for (final var lSQLStatement : sqlStatements) {
				statement.addBatch(lSQLStatement);
			}
			
			statement.executeBatch();
			connection.commit();
		} catch (final SQLException sqlException) {
			
			try {
				connection.rollback();
			} catch (final SQLException sqlException2) {
				throw WrapperException.forError(sqlException2);
			}
			
			throw WrapperException.forError(sqlException);
		}
		
		return this;
	}
	
	//method
	public final SqlConnection execute(final String sqlStatement) {
		return execute(ReadContainer.withElements(sqlStatement));
	}
	
	//method
	public final SqlConnection execute(final String... sqlStatements) {
		return execute(ReadContainer.forArray(sqlStatements));
	}
	
	//method
	public final List<String> getOneRecord(final String sqlQuery) {
		return getRecords(sqlQuery).getOriOne();
	}
	
	//method
	@Override
	public final CloseController getOriCloseController() {
		return closeController;
	}
	
	//method
	public final LinkedList<List<String>> getRecords(final String sqlQuery) {
		
		final var records = new LinkedList<List<String>>();
		try (final var statement = connection.createStatement()) {
			
			try (final var result = statement.executeQuery(sqlQuery)) {
			
				final var columnCount = result.getMetaData().getColumnCount();
				
				while (result.next()) {
					final var entries = new ArrayList<String>();
					for (var i = 1; i <= columnCount; i++) {
						entries.add(result.getString(i));
					}
					records.addAtEnd(entries);
				}
			}
		} catch (SQLException sqlException) {
			throw WrapperException.forError(sqlException);
		}
		
		return records;
	}
	
	//method
	public final IContainer<String> getRecordsAsStrings(final String sqlQuery) {
		return getRecords(sqlQuery).toStrings();
	}
	
	//method
	public final SqlDatabaseEngine getSQLDatabaseEngine() {
		return sqlDatabaseEngine;
	}
	
	//method
	@Override
	public final boolean isClosed() {
		
		if (!belongsToSQLConnectionPool()) {
			return GroupCloseable.super.isClosed();
		}
		
		return parentSQLConnectionPool.isClosed();
	}
	
	//method
	@Override
	public final void noteClose() {
		internalCloseDirectly();
	}
	
	//method
	final void internalCloseDirectly() {
		try {
			connection.close();
		} catch (final SQLException sqlException) {
			throw WrapperException.forError(sqlException);
		}
	}
	
	//method declaration
	protected abstract String getSQLDatabaseEngineDriverClass();
	
	//method
	private void giveBackSelfToParentSQLConnectionPool() {
		parentSQLConnectionPool.takeBackSQLConnection(this);
	}
	
	//method
	private void registerSQLDatabaseEngineDriver() {
		try {
			Class.forName(getSQLDatabaseEngineDriverClass());
		} catch (final ClassNotFoundException classNotFoundException) {
			throw WrapperException.forError(classNotFoundException);
		}
	}
}
