//package declaration
package ch.nolix.system.objectdatabase.databasehelper;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IDatabase;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.IDatabaseHelper;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.ITableHelper;

//class
public final class DatabaseHelper extends DatabaseObjectHelper implements IDatabaseHelper {
	
	//constant
	private static final ITableHelper TABLE_HELPER = new TableHelper();
	
	//method
	@Override
	public boolean allNewAndEditedMandatoryPropertiesAreSet(final IDatabase database) {
		return database.getOriTables().containsOnly(TABLE_HELPER::allNewAndEditedMandatoryPropertiesAreSet);
	}
	
	//method
	@Override
	public boolean canSaveChanges(final IDatabase database) {
		return
		database.isOpen()
		&& database.isLinkedWithRealDatabase()
		&& allNewAndEditedMandatoryPropertiesAreSet(database);
	}
	
	//method
	@Override
	public  IContainer<IEntity> getOriEntitiesInLocalData(final IDatabase database) {
		return database.getOriTables().toFromGroups(ITable::technicalGetRefEntitiesInLocalData);
	}
	
	//method
	@Override
	@SuppressWarnings("unchecked")
	public <E extends IEntity> ITable<E> getOriTableForGivenEntity(
		final IDatabase database,
		final E entity
	) {
		return database.getOriTableByEntityType((Class<E>)entity.getClass());
	}
	
	//method
	@Override
	public boolean hasChanges(final IDatabase database) {
		return database.getOriTables().containsAny(TABLE_HELPER::hasChanges);
	}
}
