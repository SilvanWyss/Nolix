//package declaration
package ch.nolix.system.objectdata.datahelper;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ReferencedArgumentException;
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.objectdataapi.dataapi.ITable;
import ch.nolix.techapi.objectdataapi.datahelperapi.IEntityHelper;

//class
public final class EntityHelper extends DatabaseObjectHelper implements IEntityHelper {
	
	//method
	@Override
	public void assertBelongsToTable(final IEntity<?, ?> entity) {
		if (!entity.belongsToTable()) {
			throw new ArgumentDoesNotBelongToParentException(entity, ITable.class);
		}
	}
	
	//method
	@Override
	public void assertIsNotBackReferenced(final IEntity<?, ?> entity) {
		if (entity.isBackReferenced()) {
			throw new InvalidArgumentException(entity, "is back referenced");
		}
	}
	
	//method
	@Override
	public void assertIsNotReferenced(final IEntity<?, ?> entity) {
		if (entity.isReferenced()) {
			throw new ReferencedArgumentException(entity);
		}
	}
}
