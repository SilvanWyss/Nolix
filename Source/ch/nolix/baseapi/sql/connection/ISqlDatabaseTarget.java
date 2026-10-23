/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.sql.connection;

import ch.nolix.baseapi.net.target.IDatabaseTarget;
import ch.nolix.baseapi.sql.sqlproperty.SqlDatabaseEngine;

/**
 * @author Silvan Wyss
 */
public interface ISqlDatabaseTarget extends IDatabaseTarget {
  /**
   * @return the {@link SqlDatabaseEngine} of the current
   *         {@link ISqlDatabaseTarget}
   */
  SqlDatabaseEngine getSqlDatabaseEngine();
}
