//package declaration
package ch.nolix.system.objectdata.data;

//class
final class EntityPreMutationValidator {
	
	//method
	public void assertCanDeleteEntity(final Entity entity) {
		entity.assertIsOpen();
		entity.assertIsNotDeleted();
		entity.assertIsNotNew();
		entity.assertIsNotReferenced();
	}
}
