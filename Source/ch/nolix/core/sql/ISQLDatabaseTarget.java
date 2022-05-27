//package declaration
package ch.nolix.core.sql;

import ch.nolix.core.net.targetuniversalapi.IDatabaseTarget;

//interface
public interface ISQLDatabaseTarget extends IDatabaseTarget {
	
	//method declaration
	SQLDatabaseEngine getSQLDatabaseEngine();
}
