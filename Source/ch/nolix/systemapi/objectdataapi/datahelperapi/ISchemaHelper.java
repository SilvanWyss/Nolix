//package declaration
package ch.nolix.systemapi.objectdataapi.datahelperapi;

//own imports
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ISchema;

//interface
public interface ISchemaHelper {
	
	//method declaration
	<IMPL> Class<? extends IEntity<IMPL>> getEntityTypeByName(ISchema<IMPL> schema, String name);
}
