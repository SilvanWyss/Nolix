//package declaration
package ch.nolix.system.objectdata.datahelper;

//own imports
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.objectdataapi.dataapi.ITable;
import ch.nolix.techapi.objectdataapi.datahelperapi.ITableHelper;

//class
public final class TableHelper extends DatabaseObjectHelper implements ITableHelper {
	
	//static attribute
	private static final EntityHelper entityHelper = new EntityHelper();
	
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
		&& hasInsertedGivenEntity(table, entity);
	}
	
	//method
	@Override
	public boolean hasInsertedGivenEntity(final ITable<?, ?> table, final IEntity<?> entity) {
		return table.hasInsertedEntityWithId(entity.getId());
	}
}
