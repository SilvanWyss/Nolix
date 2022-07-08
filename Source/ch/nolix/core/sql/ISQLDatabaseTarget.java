//package declaration
package ch.nolix.core.sql;

//own imports
import ch.nolix.coreapi.netapi.targetuniversalapi.IDatabaseTarget;

//interface
public interface ISQLDatabaseTarget extends IDatabaseTarget {
	
	//method declaration
	SQLDatabaseEngine getSQLDatabaseEngine();
}
