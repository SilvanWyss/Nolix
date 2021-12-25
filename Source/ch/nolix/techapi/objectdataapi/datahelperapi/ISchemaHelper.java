//package declaration
package ch.nolix.techapi.objectdataapi.datahelperapi;

//own imports
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.objectdataapi.dataapi.ISchema;

//interface
public interface ISchemaHelper {
	
	//method declaration
	<IMPL> Class<IEntity<IMPL>> getEntityTypeByName(ISchema<IMPL> schema, String name);
}
