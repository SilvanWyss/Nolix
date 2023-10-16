//package declaration
package ch.nolix.core.sql;

import ch.nolix.coreapi.programcontrolapi.targetapi.IDatabaseTarget;

//interface
public interface ISqlDatabaseTarget extends IDatabaseTarget {

  //method declaration
  SqlDatabaseEngine getSqlDatabaseEngine();
}
