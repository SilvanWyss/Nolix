package ch.nolix.coreapi.sql.sqltool;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.sql.connection.ISqlConnection;
import ch.nolix.coreapi.state.statemutation.Clearable;

public interface ISqlCollector extends Clearable {

  ISqlCollector addSqlStatement(String sqlstatement, String... sqlStatements);

  ISqlCollector addSqlStatements(Iterable<String> sqlStatements);

  IContainer<String> getSqlStatements();

  void executeAndClearUsingConnection(ISqlConnection sqlConnection);
}
