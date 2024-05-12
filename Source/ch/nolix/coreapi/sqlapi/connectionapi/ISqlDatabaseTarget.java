//package declaration
package ch.nolix.coreapi.sqlapi.connectionapi;

import ch.nolix.coreapi.netapi.targetapi.IDatabaseTarget;
import ch.nolix.coreapi.sqlapi.sqlproperty.SqlDatabaseEngine;

//interface
public interface ISqlDatabaseTarget extends IDatabaseTarget {

  //method declaration
  SqlDatabaseEngine getSqlDatabaseEngine();
}
