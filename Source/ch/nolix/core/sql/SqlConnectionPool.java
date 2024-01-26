//package declaration
package ch.nolix.core.sql;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.usercontrol.Credential;
import ch.nolix.core.programcontrol.usercontrol.CredentialBuilder;
import ch.nolix.core.resourcecontrol.resourcepool.ResourcePool;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.sqlapi.databaseconnectionapi.ISqlDatabaseTarget;
import ch.nolix.coreapi.sqlapi.sqlproperty.SqlDatabaseEngine;

//class
public final class SqlConnectionPool extends ResourcePool<SqlConnectionWrapper, SqlConnection>
implements ISqlDatabaseTarget {

  //constant
  private static final SecurityMode SECURITY_LEVEL_FOR_CONNECTIONS = SecurityMode.NONE;

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

  //constructor
  SqlConnectionPool(
    final String ipOrDomain,
    final int port,
    final String databaseName,
    final SqlDatabaseEngine sqlDatabaseEngine,
    final String loginName,
    final String loginPassword) {

    GlobalValidator.assertThat(ipOrDomain).thatIsNamed("ip or address name").isNotBlank();
    GlobalValidator.assertThat(port).thatIsNamed(LowerCaseVariableCatalogue.PORT).isBetween(0, 65_535);
    GlobalValidator.assertThat(databaseName).thatIsNamed("database name").isNotBlank();
    GlobalValidator.assertThat(sqlDatabaseEngine).thatIsNamed(SqlDatabaseEngine.class).isNotNull();

    this.ipOrDomain = ipOrDomain;
    this.port = port;
    this.databaseName = databaseName;
    this.sqlDatabaseEngine = sqlDatabaseEngine;
    credential = CredentialBuilder.createCredential().withLoginName(loginName).andPassword(loginPassword);
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
  public SecurityMode getSecurityLevelForConnections() {
    return SECURITY_LEVEL_FOR_CONNECTIONS;
  }

  //method
  @Override
  public SqlDatabaseEngine getSqlDatabaseEngine() {
    return sqlDatabaseEngine;
  }

  //method
  @Override
  public String toUrl() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "toUrl");
  }

  //method
  @Override
  protected SqlConnection createResource() {
    return SQL_CONNECTION_FACTORY.createSqlConnectionForSqlConnectionPool(this);
  }

  //method
  @Override
  protected SqlConnectionWrapper createWrapperResourceWithResource(final SqlConnection resource) {
    return SqlConnectionWrapper.forSqlConnection(resource);
  }
}
