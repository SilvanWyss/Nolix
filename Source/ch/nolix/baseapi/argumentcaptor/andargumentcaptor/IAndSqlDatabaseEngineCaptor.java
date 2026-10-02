package ch.nolix.baseapi.argumentcaptor.andargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.baseapi.sql.sqlproperty.SqlDatabaseEngine;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IAndSqlDatabaseEngineCaptor}
 */
public interface IAndSqlDatabaseEngineCaptor<S> extends ArgumentCaptor<S> {
  S andSqlDatabaseEngine(SqlDatabaseEngine sqlDatabaseEngine);

  SqlDatabaseEngine getSqlDatabaseEngine();
}
