/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.forargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.baseapi.sql.sqlproperty.SqlDatabaseEngine;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IForSqlDatabaseEngineCaptor}
 */
public interface IForSqlDatabaseEngineCaptor<S> extends ArgumentCaptor<S> {
  S forSqlDatabaseEngine(SqlDatabaseEngine sqlDatabaseEngine);

  SqlDatabaseEngine getSqlDatabaseEngine();
}
