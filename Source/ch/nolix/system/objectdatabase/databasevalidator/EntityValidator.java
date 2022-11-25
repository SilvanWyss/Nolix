//package declaration
package ch.nolix.system.objectdatabase.databasevalidator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ReferencedArgumentException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.system.objectdatabase.databasehelper.EntityHelper;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.IEntityHelper;
import ch.nolix.systemapi.objectdatabaseapi.databasevalidatorapi.IEntityValidator;

//class
public final class EntityValidator implements IEntityValidator {
	
	//static attribute
	public static final IEntityValidator INSTANCE = new EntityValidator();
	
	//static attribute
	private static final IEntityHelper entityHelper = new EntityHelper();
	
	//constructor
	private EntityValidator() {}
	
	//method
	@Override
	public final void assertBelongsToTable(final IEntity<?> entity) {
		if (!entity.belongsToTable()) {
			throw ArgumentDoesNotBelongToParentException.forArgumentAndParentType(entity, ITable.class);
		}
	}
	
	//method
	@Override
	public final void assertCanBeDeleted(final IEntity<?> entity) {
		if (!entityHelper.canBeDeleted(entity)) {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(entity, "cannot be deleted");
		}
	}
	
	//method
	@Override
	public final void assertDoesNotBelongToTable(final IEntity<?> entity) {
		if (entity.belongsToTable()) {
			throw ArgumentBelongsToParentException.forArgumentAndParent(entity, entity.getRefParentTable());
		}
	}
	
	//method
	@Override
	public final void assertHasSaveStamp(final IEntity<?> entity) {
		if (!entity.hasSaveStamp()) {
			throw
			ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(entity, LowerCaseCatalogue.SAVE_STAMP);
		}
	}
	
	//method
	@Override
	public final void assertIsNotReferenced(final IEntity<?> entity) {
		if (entityHelper.isReferenced(entity)) {
			throw ReferencedArgumentException.forArgument(entity);
		}
	}
}
