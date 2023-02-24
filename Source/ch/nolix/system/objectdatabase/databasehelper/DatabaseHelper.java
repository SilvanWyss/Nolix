//package declaration
package ch.nolix.system.objectdatabase.databasehelper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IDatabase;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.IDatabaseHelper;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.ITableHelper;

//class
public final class DatabaseHelper extends DatabaseObjectHelper implements IDatabaseHelper {
	
	//static attribute
	private final ITableHelper tableHelper = new TableHelper();
	
	//method
	@Override
	public boolean allNewAndEditedMandatoryPropertiesAreSet(final IDatabase database) {
		return database.getRefTables().containsOnly(tableHelper::allNewAndEditedMandatoryPropertiesAreSet);
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
	public  IContainer<IEntity> getRefEntitiesInLocalData(final IDatabase database) {
		return database.getRefTables().toFromGroups(ITable::technicalGetRefEntitiesInLocalData);
	}
	
	//method
	@Override
	@SuppressWarnings("unchecked")
	public <E extends IEntity> ITable<E> getRefTableForGivenEntity(
		final IDatabase database,
		final E entity
	) {
		return database.getRefTableByEntityType((Class<E>)entity.getClass());
	}
	
	//method
	@Override
	public boolean hasChanges(final IDatabase database) {
		return database.getRefTables().containsAny(tableHelper::hasChanges);
	}
}
