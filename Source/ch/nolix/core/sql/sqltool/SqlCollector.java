package ch.nolix.core.sql.sqltool;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.coreapi.sqlapi.sqltoolapi.ISqlCollector;

public final class SqlCollector implements ISqlCollector {

  private final LinkedList<String> sqlStatements = LinkedList.createEmpty();

  @Override
  public SqlCollector addSqlStatement(final String sqlstatement, final String... sqlStatements) {

    addSingleSqlStatement(sqlstatement);

    addSqlStatements(ContainerView.forArray(sqlStatements));

    return this;
  }

  @Override
  public SqlCollector addSqlStatements(final Iterable<String> sqlStatements) {

    sqlStatements.forEach(this::addSqlStatement);

    return this;
  }

  @Override
  public void clear() {
    sqlStatements.clear();
  }

  @Override
  public void executeAndClearUsingConnection(final ISqlConnection sqlConnection) {
    try {
      executeUsingConnection(sqlConnection);
    } finally {
      clear();
    }
  }

  @Override
  public IContainer<String> getSqlStatements() {
    return sqlStatements;
  }

  @Override
  public boolean isEmpty() {
    return sqlStatements.isEmpty();
  }

  private void addSingleSqlStatement(final String sqlstatement) {

    GlobalValidator.assertThat(sqlstatement).thatIsNamed("SQL statement").isNotBlank();

    this.sqlStatements.addAtEnd(sqlstatement);
  }

  private void executeUsingConnection(final ISqlConnection sqlConnection) {
    sqlConnection.executeStatements(sqlStatements);
  }
}
