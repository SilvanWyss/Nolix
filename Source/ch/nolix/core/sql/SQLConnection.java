//package declaration
package ch.nolix.core.sql;

//Java imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//own imports
import ch.nolix.core.constant.IPv4Catalogue;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.core.programcontrol.groupcloseable.GroupCloseable;

//class
public abstract class SQLConnection implements GroupCloseable {
	
	//attribute
	private final SQLDatabaseEngine mSQLDatabaseEngine;
	
	//attribute
	private final Connection connection;
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//optional attribute
	private final SQLConnectionPool parentSQLConnectionPool;
	
	//constructor
	public SQLConnection(final SQLDatabaseEngine pSQLDatabaseEngine, final Connection connection) {
		
		GlobalValidator.assertThat(pSQLDatabaseEngine).thatIsNamed(SQLDatabaseEngine.class).isNotNull();
		GlobalValidator.assertThat(connection).thatIsNamed(Connection.class).isNotNull();
		
		this.mSQLDatabaseEngine = pSQLDatabaseEngine;
		this.connection = connection;
		parentSQLConnectionPool = null;
	}
	
	//constructor
	public SQLConnection(
		final SQLDatabaseEngine pSQLDatabaseEngine,
		final int port,
		final String userName,
		final String userPassword
	) {
		this(
			pSQLDatabaseEngine,
			IPv4Catalogue.LOOP_BACK_ADDRESS,
			port,
			userName,
			userPassword
		);
	}
	
	//constructor
	public SQLConnection(
		final SQLDatabaseEngine pSQLDatabaseEngine,
		final String ip,
		final int port,
		final String userName,
		final String userPassword
	) {
		
		GlobalValidator.assertThat(pSQLDatabaseEngine).thatIsNamed(SQLDatabaseEngine.class).isNotNull();
		
		this.mSQLDatabaseEngine = pSQLDatabaseEngine;
		
		registerSQLDatabaseEngineDriver();
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlserver://" + ip + ':'+ port, userName, userPassword);
		} catch (final SQLException pSQLException) {
			throw new WrapperException(pSQLException);
		}
		
		parentSQLConnectionPool = null;
	}
	
	//constructor
	public SQLConnection(
		final SQLDatabaseEngine pSQLDatabaseEngine,
		final String ip,
		final int port,
		final String userName,
		final String userPassword,
		final SQLConnectionPool parentSQLConnectionPool
	) {
		
		GlobalValidator.assertThat(pSQLDatabaseEngine).thatIsNamed(SQLDatabaseEngine.class).isNotNull();
		GlobalValidator.assertThat(parentSQLConnectionPool).thatIsNamed("parent SQLConnectionPool").isNotNull();
		
		GlobalValidator
		.assertThat(parentSQLConnectionPool)
		.thatIsNamed("parent SQLConnectionPool")
		.fulfills(SQLConnectionPool::isOpen);
		
		this.mSQLDatabaseEngine = pSQLDatabaseEngine;
		
		registerSQLDatabaseEngineDriver();
		
		try {
			connection =
			DriverManager.getConnection(
				"jdbc:sqlserver://" + ip + ':'+ port,
				userName,
				userPassword
			);
		} catch (final SQLException pSQLException) {
			throw new WrapperException(pSQLException);
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
	public final SQLConnection execute(final Iterable<String> pSQLStatements) {
		
		try (final var statement = connection.createStatement()) {
			
			connection.setAutoCommit(false);
			
			for (final var lSQLStatement : pSQLStatements) {
				statement.addBatch(lSQLStatement);
			}
			
			statement.executeBatch();
			connection.commit();
		} catch (final SQLException pSQLException) {
			
			try {
				connection.rollback();
			} catch (final SQLException pSQLException2) {
				throw new WrapperException(pSQLException2);
			}
			
			throw new WrapperException(pSQLException);
		}
		
		return this;
	}
	
	//method
	public final SQLConnection execute(final String pSQLStatement) {
		return execute(ReadContainer.withElements(pSQLStatement));
	}
	
	//method
	public final SQLConnection execute(final String... pSQLStatements) {
		return execute(ReadContainer.forArray(pSQLStatements));
	}
	
	//method
	public final List<String> getOneRecord(final String pSQLQuery) {
		return getRecords(pSQLQuery).getRefOne();
	}
	
	//method
	@Override
	public final CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	public final LinkedList<List<String>> getRecords(final String pSQLQuery) {
		
		final var records = new LinkedList<List<String>>();
		try (final var statement = connection.createStatement()) {
			
			try (final var result = statement.executeQuery(pSQLQuery)) {
			
				final var columnCount = result.getMetaData().getColumnCount();
				
				while (result.next()) {
					final var entries = new ArrayList<String>();
					for (var i = 1; i <= columnCount; i++) {
						entries.add(result.getString(i));
					}
					records.addAtEnd(entries);
				}
			}
		} catch (SQLException pSQLException) {
			throw new WrapperException(pSQLException);
		}
		
		return records;
	}
	
	//method
	public final LinkedList<String> getRecordsAsStrings(final String pSQLQuery) {
		return getRecords(pSQLQuery).toStrings();
	}
	
	//method
	public final SQLDatabaseEngine getSQLDatabaseEngine() {
		return mSQLDatabaseEngine;
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
		} catch (final SQLException pSQLException) {
			throw new WrapperException(pSQLException);
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
			throw new WrapperException(classNotFoundException);
		}
	}
}
