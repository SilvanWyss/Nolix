package ch.nolix.core.sql;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.sql.connection.SqlConnection;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.coreapi.stateapi.statemutationapi.Clearable;

public final class SqlCollector implements Clearable {

  private final LinkedList<String> sqlStatements = LinkedList.createEmpty();

  public SqlCollector addSqlStatement(final String sqlstatement) {

    GlobalValidator.assertThat(sqlstatement).thatIsNamed("SQL statement").isNotBlank();

    sqlStatements.addAtEnd(getSqlStatementWithSemicolonAtEnd(sqlstatement));

    return this;
  }

  public SqlCollector addSqlStatements(final Iterable<String> sqlStatements) {

    sqlStatements.forEach(this::addSqlStatement);

    return this;
  }

  @Override
  public void clear() {
    sqlStatements.clear();
  }

  public void executeAndClearUsingConnection(final SqlConnection sqlConnection) {
    try {
      executeUsingConnection(sqlConnection);
    } finally {
      clear();
    }
  }

  public void executeUsingConnection(final ISqlConnection sqlConnection) {
    sqlConnection.executeStatements(sqlStatements);
  }

  public IContainer<String> getSqlStatements() {
    return sqlStatements;
  }

  @Override
  public boolean isEmpty() {
    return sqlStatements.isEmpty();
  }

  private String getSqlStatementWithSemicolonAtEnd(final String sqlStatement) {

    if (!sqlStatement.endsWith(";")) {
      return (sqlStatement + ";");
    }

    return sqlStatement;
  }
}
