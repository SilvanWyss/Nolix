//package declaration
package ch.nolix.core.sql;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.constant.PortCatalogue;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.core.programcontrol.usercontrol.Credential;
import ch.nolix.coreapi.programcontrolapi.processproperty.SecurityLevel;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;

//class
public final class SQLConnectionPool implements GroupCloseable, ISqlDatabaseTarget {
	
	//constant
	public static final int DEFAULT_PORT = PortCatalogue.MSSQL_PORT;
	
	//constant
	private static final SecurityLevel SECURITY_LEVEL_FOR_CONNECTIONS = SecurityLevel.UNSECURE;
	
	//static attribute
	private static final SQLConnectionFactory sSQLConnectionFactory = new SQLConnectionFactory();
	
	//static method
	public static SQLConnectionPoolBuilder forIpOrAddressName(final String ipOrAddressName) {
		return new SQLConnectionPoolBuilder(ipOrAddressName, DEFAULT_PORT);
	}
	
	//attribute
	private final String ipOrAddressName;
	
	//attribute
	private final int port;
	
	//attribute
	private final String databaseName;
	
	//attribute
	private final SQLDatabaseEngine mSQLDatabaseEngine;
	
	//attribute
	private final Credential credential;
	
	//attribute
	private final CloseController closeController = CloseController.forElement(this);
	
	//multi-attribute
	private final LinkedList<SQLConnectionWrapper> mSQLConnections = new LinkedList<>();
	
	//constructor
	SQLConnectionPool(
		final String ipOrAddressName,
		final int port,
		final String databaseName,
		final SQLDatabaseEngine pSQLDatabaseEngine,
		final String loginName,
		final String loginPassword
	) {
		
		GlobalValidator.assertThat(ipOrAddressName).thatIsNamed("ip or address name").isNotBlank();
		GlobalValidator.assertThat(port).thatIsNamed(LowerCaseCatalogue.PORT).isBetween(0, 65_535);
		GlobalValidator.assertThat(databaseName).thatIsNamed("database name").isNotBlank();
		GlobalValidator.assertThat(pSQLDatabaseEngine).thatIsNamed(SQLDatabaseEngine.class).isNotNull();
				
		this.ipOrAddressName = ipOrAddressName;
		this.port = port;
		this.databaseName = databaseName;
		mSQLDatabaseEngine = pSQLDatabaseEngine;
		credential = Credential.withLoginName(loginName).andPassword(loginPassword);
	}
	
	//method
	public SqlConnection borrowSQLConnection() {
		
		final var lSQLConnection = getOrCreateAvailableSQLConnectionWrapper();
		
		final var innerSQLConnection = lSQLConnection.getOriSQLConnection();
		lSQLConnection.setAsInUse();
		
		return innerSQLConnection;
	}
	
	//method
	public boolean containsAvailableSQLConnection() {
		return mSQLConnections.containsAny(SQLConnectionWrapper::isAvailable);
	}
	
	//method
	@Override
	public String getDatabaseName() {
		return databaseName;
	}
	
	//method
	@Override
	public String getIpOrAddressName() {
		return ipOrAddressName;
	}
	
	//method
	@Override
	public String getLoginName() {
		return credential.getLoginName();
	}
	
	//method
	@Override
	public String getLoginPassword() {
		return credential.getPassword();
	}
	
	//method
	@Override
	public int getPort() {
		return port;
	}
	
	//method
	@Override
	public CloseController getOriCloseController() {
		return closeController;
	}
	
	//method
	@Override
	public SecurityLevel getSecurityLevelForConnections() {
		return SECURITY_LEVEL_FOR_CONNECTIONS;
	}
	
	//method
	@Override
	public SQLDatabaseEngine getSQLDatabaseEngine() {
		return mSQLDatabaseEngine;
	}
	
	//method
	@Override
	public void noteClose() {
		for (final var sqlc : mSQLConnections) {
			sqlc.close();
		}
	}
	
	//method
	public void takeBackSQLConnection(final SqlConnection pSQLConnection) {
		mSQLConnections.getOriFirst(sqlc -> sqlc.contains(pSQLConnection)).setAvailable();
	}
	
	//method
	@Override
	public String toURL() {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "toURL");
	}
	
	//method
	private SQLConnectionWrapper createSQLConnectionWrapper() {
		
		final var lSQLConnectionWrapper =
		SQLConnectionWrapper.forSQLConnection(sSQLConnectionFactory.createSQLConnectionFor(this));
		
		mSQLConnections.addAtEnd(lSQLConnectionWrapper);
		
		return lSQLConnectionWrapper;
	}
	
	//method
	private SQLConnectionWrapper getOrCreateAvailableSQLConnectionWrapper() {
		
		final var lSQLConnectionWrapper = mSQLConnections.getOriFirstOrNull(SQLConnectionWrapper::isAvailable);
		if (lSQLConnectionWrapper != null) {
			return lSQLConnectionWrapper;
		}
		
		return createSQLConnectionWrapper();
	}
}
