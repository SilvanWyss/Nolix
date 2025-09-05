package ch.nolix.coreapi.sql.connection;

import ch.nolix.coreapi.net.target.IDatabaseTarget;
import ch.nolix.coreapi.sql.sqlproperty.SqlDatabaseEngine;

public interface ISqlDatabaseTarget extends IDatabaseTarget {
  SqlDatabaseEngine getSqlDatabaseEngine();
}
