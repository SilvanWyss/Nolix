//package declaration
package ch.nolix.system.objectdata.datahelper;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.datahelperapi.ITableHelper;

//class
public final class TableHelper extends DatabaseObjectHelper implements ITableHelper {
	
	//static attribute
	private static final EntityHelper entityHelper = new EntityHelper();
	
	//method
	@Override
	public void assertCanInsertGivenEntity(final ITable<?, ?> table, final IEntity<?> entity) {
		if (!canInsertGivenEntity(table, entity)) {
			throw new InvalidArgumentException(entity, "cannot be inserted into the table " + table.getNameInQuotes());
		}
	}
	
	//method
	@Override
	public boolean canInsertEntity(final ITable<?, ?> table) {
		return table.isOpen();
	}
	
	//method
	@Override
	public boolean canInsertGivenEntity(ITable<?, ?> table, IEntity<?> entity) {
		return
		canInsertEntity(table)
		&& entityHelper.canBeInsertedIntoTable(entity)
		&& !hasInsertedGivenEntityInLocalData(table, entity);
	}
	
	//method
	@Override
	public boolean hasInsertedGivenEntityInLocalData(final ITable<?, ?> table, final IEntity<?> entity) {
		return table.containsEntityWithGivenIdInLocalData(entity.getId());
	}
}
