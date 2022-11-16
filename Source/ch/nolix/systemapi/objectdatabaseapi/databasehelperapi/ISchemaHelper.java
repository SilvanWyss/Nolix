//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databasehelperapi;

import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ISchema;

//interface
public interface ISchemaHelper {
	
	//method declaration
	<IMPL> Class<? extends IEntity<IMPL>> getEntityTypeByName(ISchema<IMPL> schema, String name);
}
