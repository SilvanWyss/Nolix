//package declaration
package ch.nolix.core.sql;

//own imports
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
public final class SqlConnectionPool implements GroupCloseable, ISqlDatabaseTarget {

  //constant
  public static final int DEFAULT_PORT = PortCatalogue.MSSQL;

  //constant
  private static final SecurityLevel SECURITY_LEVEL_FOR_CONNECTIONS = SecurityLevel.UNSECURE;

  //constant
  private static final SqlConnectionFactory SQL_CONNECTION_FACTORY = new SqlConnectionFactory();

  //attribute
  private final String ipOrDomain;

  //attribute
  private final int port;

  //attribute
  private final String databaseName;

  //attribute
  private final SqlDatabaseEngine sqlDatabaseEngine;

  //attribute
  private final Credential credential;

  //attribute
  private final CloseController closeController = CloseController.forElement(this);

  //multi-attribute
  private final LinkedList<SqlConnectionWrapper> sqlConnections = new LinkedList<>();

  //constructor
  SqlConnectionPool(
      final String ipOrDomain,
      final int port,
      final String databaseName,
      final SqlDatabaseEngine sqlDatabaseEngine,
      final String loginName,
      final String loginPassword) {

    GlobalValidator.assertThat(ipOrDomain).thatIsNamed("ip or address name").isNotBlank();
    GlobalValidator.assertThat(port).thatIsNamed(LowerCaseCatalogue.PORT).isBetween(0, 65_535);
    GlobalValidator.assertThat(databaseName).thatIsNamed("database name").isNotBlank();
    GlobalValidator.assertThat(sqlDatabaseEngine).thatIsNamed(SqlDatabaseEngine.class).isNotNull();

    this.ipOrDomain = ipOrDomain;
    this.port = port;
    this.databaseName = databaseName;
    this.sqlDatabaseEngine = sqlDatabaseEngine;
    credential = Credential.withLoginName(loginName).andPassword(loginPassword);
  }

  //static method
  public static SqlConnectionPoolBuilder forIpOrDomain(final String ipOrDomain) {
    return new SqlConnectionPoolBuilder(ipOrDomain, DEFAULT_PORT);
  }

  //method
  public SqlConnection borrowSqlConnection() {

    final var sqlConnection = getOrCreateAvailableSqlConnectionWrapper();

    final var innerSqlConnection = sqlConnection.getStoredSqlConnection();
    sqlConnection.setAsInUse();

    return innerSqlConnection;
  }

  //method
  public boolean containsAvailableSqlConnection() {
    return sqlConnections.containsAny(SqlConnectionWrapper::isAvailable);
  }

  //method
  @Override
  public String getDatabaseName() {
    return databaseName;
  }

  //method
  @Override
  public String getIpOrDomain() {
    return ipOrDomain;
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
  public CloseController getStoredCloseController() {
    return closeController;
  }

  //method
  @Override
  public SecurityLevel getSecurityLevelForConnections() {
    return SECURITY_LEVEL_FOR_CONNECTIONS;
  }

  //method
  @Override
  public SqlDatabaseEngine getSqlDatabaseEngine() {
    return sqlDatabaseEngine;
  }

  //method
  @Override
  public void noteClose() {
    for (final var sqlc : sqlConnections) {
      sqlc.close();
    }
  }

  //method
  public void takeBackSqlConnection(final SqlConnection sqlConnection) {
    sqlConnections.getStoredFirst(sqlc -> sqlc.contains(sqlConnection)).setAvailable();
  }

  //method
  @Override
  public String toUrl() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "toUrl");
  }

  //method
  private SqlConnectionWrapper createQslConnectionWrapper() {

    final var sqlConnectionWrapper = SqlConnectionWrapper
        .forSqlConnection(SQL_CONNECTION_FACTORY.createQslConnectionFor(this));

    sqlConnections.addAtEnd(sqlConnectionWrapper);

    return sqlConnectionWrapper;
  }

  //method
  private SqlConnectionWrapper getOrCreateAvailableSqlConnectionWrapper() {

    final var sqlConnectionWrapper = sqlConnections.getStoredFirstOrNull(SqlConnectionWrapper::isAvailable);
    if (sqlConnectionWrapper != null) {
      return sqlConnectionWrapper;
    }

    return createQslConnectionWrapper();
  }
}
