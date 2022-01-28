//package declaration
package ch.nolix.systemapi.objectdataapi.datahelperapi;

import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ISchema;

//interface
public interface ISchemaHelper {
	
	//method declaration
	<IMPL> Class<IEntity<IMPL>> getEntityTypeByName(ISchema<IMPL> schema, String name);
}
