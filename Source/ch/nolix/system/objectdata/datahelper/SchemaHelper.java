//package
package ch.nolix.system.objectdata.datahelper;

//own imports
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ISchema;
import ch.nolix.systemapi.objectdataapi.datahelperapi.ISchemaHelper;

//class
public final class SchemaHelper implements ISchemaHelper {
	
	//method
	@Override
	public <IMPL> Class<? extends IEntity<IMPL>> getEntityTypeByName(final ISchema<IMPL> schema, final String name) {
		return schema.getEntityTypesInOrder().getRefFirst(et -> et.getSimpleName().equals(name));
	}
}
