package ch.nolix.core.sql.connectionpool;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.usercontrol.Credential;
import ch.nolix.core.programcontrol.usercontrol.CredentialBuilder;
import ch.nolix.core.resourcecontrol.resourcepool.AbstractResourcePool;
import ch.nolix.core.sql.connection.AbstractSqlConnection;
import ch.nolix.coreapi.net.securityproperty.SecurityMode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlDatabaseTarget;
import ch.nolix.coreapi.sqlapi.sqlproperty.SqlDatabaseEngine;

public final class SqlConnectionPool
extends AbstractResourcePool<WrapperSqlConnection, AbstractSqlConnection>
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

    Validator.assertThat(ipOrDomain).thatIsNamed("ip or address name").isNotBlank();
    Validator.assertThat(port).thatIsNamed(LowerCaseVariableCatalog.PORT).isBetween(0, 65_535);
    Validator.assertThat(databaseName).thatIsNamed("database name").isNotBlank();
    Validator.assertThat(sqlDatabaseEngine).thatIsNamed(SqlDatabaseEngine.class).isNotNull();

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
  protected AbstractSqlConnection createResource() {
    return SQL_CONNECTION_FACTORY.createSqlConnectionForSqlConnectionPool(this);
  }

  @Override
  protected WrapperSqlConnection createWrapperResourceWithResource(final AbstractSqlConnection resource) {
    return WrapperSqlConnection.forSqlConnection(resource);
  }
}
