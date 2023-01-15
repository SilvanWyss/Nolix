//package
package ch.nolix.system.objectdatabase.databasehelper;

import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.ISchemaHelper;
import ch.nolix.systemapi.objectdatabaseapi.schemaapi.ISchema;

//class
public final class SchemaHelper implements ISchemaHelper {
	
	//method
	@Override
	public <IMPL> Class<? extends IEntity<IMPL>> getEntityTypeByName(final ISchema<IMPL> schema, final String name) {
		return schema.getEntityTypes().getRefFirst(et -> et.getSimpleName().equals(name));
	}
}
