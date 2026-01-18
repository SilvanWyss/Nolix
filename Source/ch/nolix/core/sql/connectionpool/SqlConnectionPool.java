package ch.nolix.core.sql.connectionpool;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.usercontrol.Credential;
import ch.nolix.core.resourcecontrol.resourcepool.AbstractResourcePool;
import ch.nolix.core.sql.connection.AbstractSqlConnection;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.net.securityproperty.SecurityMode;
import ch.nolix.coreapi.sql.connection.ISqlDatabaseTarget;
import ch.nolix.coreapi.sql.sqlproperty.SqlDatabaseEngine;

/**
 * @author Silvan Wyss
 */
public final class SqlConnectionPool
extends AbstractResourcePool<WrapperSqlConnection, AbstractSqlConnection>
implements ISqlDatabaseTarget {
  private static final SecurityMode SECURITY_MODE_FOR_CONNECTIONS = SecurityMode.NONE;

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
    credential = Credential.withLoginNameAndPassword(loginName, loginPassword);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getDatabaseName() {
    return databaseName;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getIpOrDomain() {
    return ipOrDomain;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getLoginName() {
    return credential.getLoginName();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getLoginPassword() {
    return credential.getPassword();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getPort() {
    return port;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SecurityMode getSecurityModeForConnection() {
    return SECURITY_MODE_FOR_CONNECTIONS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SqlDatabaseEngine getSqlDatabaseEngine() {
    return sqlDatabaseEngine;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toUrl() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "toUrl");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected AbstractSqlConnection createResource() {
    return SqlConnectionFactory.createSqlConnectionForSqlConnectionPool(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected WrapperSqlConnection createWrapperResourceWithResource(final AbstractSqlConnection resource) {
    return WrapperSqlConnection.forSqlConnection(resource);
  }
}
