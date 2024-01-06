//package declaration
package ch.nolix.coreapi.sqlapi.databaseconnectionapi;

//own imports
import ch.nolix.coreapi.programcontrolapi.targetapi.IDatabaseTarget;
import ch.nolix.coreapi.sqlapi.sqlproperty.SqlDatabaseEngine;

//interface
public interface ISqlDatabaseTarget extends IDatabaseTarget {

  //method declaration
  SqlDatabaseEngine getSqlDatabaseEngine();
}
