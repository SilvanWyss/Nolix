//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.system.objectdata.datahelper.EntityHelper;
import ch.nolix.techapi.objectdataapi.datahelperapi.IEntityHelper;

//class
final class EntityMutationValidator {
	
	//static attribute
	private static final IEntityHelper entityHelper = new EntityHelper();
	
	//method
	public void assertCanDeleteEntity(final Entity entity) {
		entity.assertIsOpen();
		entityHelper.assertIsNotDeleted(entity);
		entityHelper.assertIsNotNew(entity);
		entityHelper.assertIsNotReferenced(entity);
	}
}
