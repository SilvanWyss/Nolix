package ch.nolix.coreapi.sqlapi.sqltoolapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.coreapi.stateapi.statemutationapi.Clearable;

public interface ISqlCollector extends Clearable {

  ISqlCollector addSqlStatement(String sqlstatement, String... sqlStatements);

  ISqlCollector addSqlStatements(Iterable<String> sqlStatements);

  IContainer<String> getSqlStatements();

  void executeAndClearUsingConnection(ISqlConnection sqlConnection);
}
