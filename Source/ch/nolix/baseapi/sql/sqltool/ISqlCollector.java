/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.sql.sqltool;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.generalstate.statemutation.Clearable;
import ch.nolix.baseapi.sql.connection.ISqlConnection;

/**
 * @author Silvan Wyss
 */
public interface ISqlCollector extends Clearable {
  ISqlCollector addSqlStatement(String sqlstatement);

  ISqlCollector addSqlStatements(Iterable<String> sqlStatements);

  ISqlCollector addSqlStatements(String... sqlStatements);

  ExtendedIterable<String> getSqlStatements();

  void executeAndClearUsingConnection(ISqlConnection sqlConnection);
}
