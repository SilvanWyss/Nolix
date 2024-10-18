package ch.nolix.coreapi.sqlapi.connectionapi;

import ch.nolix.coreapi.netapi.targetapi.IDatabaseTarget;
import ch.nolix.coreapi.sqlapi.sqlproperty.SqlDatabaseEngine;

public interface ISqlDatabaseTarget extends IDatabaseTarget {

  SqlDatabaseEngine getSqlDatabaseEngine();
}
