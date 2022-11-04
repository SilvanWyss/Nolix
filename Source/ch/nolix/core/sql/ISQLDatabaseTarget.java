//package declaration
package ch.nolix.core.sql;

import ch.nolix.coreapi.programcontrolapi.targetuniversalapi.IDatabaseTarget;

//interface
public interface ISQLDatabaseTarget extends IDatabaseTarget {
	
	//method declaration
	SQLDatabaseEngine getSQLDatabaseEngine();
}
