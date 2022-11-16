//package
package ch.nolix.system.objectdatabase.databasehelper;

import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ISchema;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.ISchemaHelper;

//class
public final class SchemaHelper implements ISchemaHelper {
	
	//method
	@Override
	public <IMPL> Class<? extends IEntity<IMPL>> getEntityTypeByName(final ISchema<IMPL> schema, final String name) {
		return schema.getEntityTypesInOrder().getRefFirst(et -> et.getSimpleName().equals(name));
	}
}
