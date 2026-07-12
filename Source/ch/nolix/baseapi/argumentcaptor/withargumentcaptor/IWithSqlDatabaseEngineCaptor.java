/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.withargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.baseapi.sql.sqlproperty.SqlDatabaseEngine;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a
 *            {@link IWithSqlDatabaseEngineCaptor}.
 */
public interface IWithSqlDatabaseEngineCaptor<S> extends ArgumentCaptor<S> {
  SqlDatabaseEngine getSqlDatabaseEngine();

  S withSqlDatabaseEngine(final SqlDatabaseEngine sqlDatabaseEngine);
}
