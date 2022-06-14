//package declaration
package ch.nolix.system.objectdata.schemamapper;

import ch.nolix.core.reflection.GlobalClassHelper;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;

//class
final class EntityCreator {
	
	//method
	public <E extends IEntity<?>> E createEmptyEntityOf(final Class<E> entityType) {
		return GlobalClassHelper.createInstanceFromDefaultConstructorOf(entityType);
	}
}
