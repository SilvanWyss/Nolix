/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.sql.connectionpool;

import ch.nolix.base.programcontrol.authentification.Credential;
import ch.nolix.base.resourcecontrol.resourcepool.AbstractResourcePool;
import ch.nolix.base.sql.connection.AbstractSqlConnection;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.net.netproperty.SecurityMode;
import ch.nolix.baseapi.sql.connection.ISqlDatabaseTarget;
import ch.nolix.baseapi.sql.sqlproperty.SqlDatabaseEngine;

/**
 * @author Silvan Wyss
 */
public final class SqlConnectionPool
extends AbstractResourcePool<WrapperSqlConnection, AbstractSqlConnection>
implements ISqlDatabaseTarget {
  private static final SecurityMode SECURITY_MODE_FOR_CONNECTIONS = SecurityMode.NONE;

  private final String host;

  private final int port;

  private final String databaseName;

  private final SqlDatabaseEngine sqlDatabaseEngine;

  private final Credential credential;

  private SqlConnectionPool(
    final String host,
    final int port,
    final String databaseName,
    final SqlDatabaseEngine sqlDatabaseEngine,
    final String loginName,
    final String loginPassword) {
    Validator.assertThat(host).thatIsNamed("ip or address name").isNotBlank();
    Validator.assertThat(port).thatIsNamed(LowerCaseVariableNameCatalog.PORT).isBetween(0, 65_535);
    Validator.assertThat(databaseName).thatIsNamed("database name").isNotBlank();
    Validator.assertThat(sqlDatabaseEngine).thatIsNamed(SqlDatabaseEngine.class).isNotNull();

    this.host = host;
    this.port = port;
    this.databaseName = databaseName;
    this.sqlDatabaseEngine = sqlDatabaseEngine;
    credential = Credential.withLoginNameAndPassword(loginName, loginPassword);
  }

  public static SqlConnectionPool withHostAndPortAndDatabaseNameAndSqlDatabaseEngineAndLoginNameAndLoginPassword(
    final String host,
    final int port,
    final String databaseName,
    final SqlDatabaseEngine sqlDatabaseEngine,
    final String loginName,
    final String loginPassword) {
    return new SqlConnectionPool(host, port, databaseName, sqlDatabaseEngine, loginName, loginPassword);
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
  public String getHost() {
    return host;
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
  public SecurityMode getSecurityMode() {
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
