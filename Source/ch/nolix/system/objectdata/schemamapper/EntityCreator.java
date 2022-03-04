//package declaration
package ch.nolix.system.objectdata.schemamapper;

//own imports
import ch.nolix.core.reflectionhelper.GlobalClassHelper;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;

//class
final class EntityCreator {
	
	//method
	public <E extends IEntity<?>> E createEmptyEntityOf(final Class<E> entityType) {
		return GlobalClassHelper.createInstanceFromDefaultConstructorOf(entityType);
	}
}
