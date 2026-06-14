/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.sql.sqltool;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.baseapi.sql.connection.ISqlConnection;
import ch.nolix.baseapi.state.statemutation.Clearable;

/**
 * @author Silvan Wyss
 */
public interface ISqlCollector extends Clearable {
  ISqlCollector addSqlStatement(String sqlstatement);

  ISqlCollector addSqlStatements(Iterable<String> sqlStatements);

  ISqlCollector addSqlStatements(String... sqlStatements);

  IWellOrderContainer<String> getSqlStatements();

  void executeAndClearUsingConnection(ISqlConnection sqlConnection);
}
