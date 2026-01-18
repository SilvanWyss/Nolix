/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.sql.sqltool;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.sql.connection.ISqlConnection;
import ch.nolix.coreapi.sql.sqltool.ISqlCollector;

/**
 * @author Silvan Wyss
 */
public final class SqlCollector implements ISqlCollector {
  private final LinkedList<String> memberSqlStatements = LinkedList.createEmpty();

  @Override
  public SqlCollector addSqlStatement(final String sqlstatement, final String... sqlStatements) {
    addSingleSqlStatement(sqlstatement);

    addSqlStatements(ContainerView.forArray(sqlStatements));

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SqlCollector addSqlStatements(final Iterable<String> sqlStatements) {
    sqlStatements.forEach(this::addSqlStatement);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clear() {
    memberSqlStatements.clear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void executeAndClearUsingConnection(final ISqlConnection sqlConnection) {
    try {
      executeUsingConnection(sqlConnection);
    } finally {
      clear();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<String> getSqlStatements() {
    return memberSqlStatements;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty() {
    return memberSqlStatements.isEmpty();
  }

  private void addSingleSqlStatement(final String sqlstatement) {
    Validator.assertThat(sqlstatement).thatIsNamed("SQL statement").isNotBlank();

    memberSqlStatements.addAtEnd(sqlstatement);
  }

  private void executeUsingConnection(final ISqlConnection sqlConnection) {
    sqlConnection.executeStatements(memberSqlStatements);
  }
}
