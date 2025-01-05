package ch.nolix.core.sql.connectionpool;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.usercontrol.Credential;
import ch.nolix.core.programcontrol.usercontrol.CredentialBuilder;
import ch.nolix.core.resourcecontrol.resourcepool.ResourcePool;
import ch.nolix.core.sql.connection.SqlConnection;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlDatabaseTarget;
import ch.nolix.coreapi.sqlapi.sqlproperty.SqlDatabaseEngine;

public final class SqlConnectionPool
extends ResourcePool<WrapperSqlConnection, SqlConnection>
implements ISqlDatabaseTarget {

  private static final SecurityMode SECURITY_MODE_FOR_CONNECTIONS = SecurityMode.NONE;

  private static final SqlConnectionFactory SQL_CONNECTION_FACTORY = new SqlConnectionFactory();

  private final String ipOrDomain;

  private final int port;

  private final String databaseName;

  private final SqlDatabaseEngine sqlDatabaseEngine;

  private final Credential credential;

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

  @Override
  public String getDatabaseName() {
    return databaseName;
  }

  @Override
  public String getIpOrDomain() {
    return ipOrDomain;
  }

  @Override
  public String getLoginName() {
    return credential.getLoginName();
  }

  @Override
  public String getLoginPassword() {
    return credential.getPassword();
  }

  @Override
  public int getPort() {
    return port;
  }

  @Override
  public SecurityMode getSecurityModeForConnection() {
    return SECURITY_MODE_FOR_CONNECTIONS;
  }

  @Override
  public SqlDatabaseEngine getSqlDatabaseEngine() {
    return sqlDatabaseEngine;
  }

  @Override
  public String toUrl() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "toUrl");
  }

  @Override
  protected SqlConnection createResource() {
    return SQL_CONNECTION_FACTORY.createSqlConnectionForSqlConnectionPool(this);
  }

  @Override
  protected WrapperSqlConnection createWrapperResourceWithResource(final SqlConnection resource) {
    return WrapperSqlConnection.forSqlConnection(resource);
  }
}
