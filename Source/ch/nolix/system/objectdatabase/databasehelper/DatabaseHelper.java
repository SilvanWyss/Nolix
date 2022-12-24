//package declaration
package ch.nolix.system.objectdatabase.databasehelper;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
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
	public boolean allNewAndEditedMandatoryPropertiesAreSet(final IDatabase<?> database) {
		return database.getRefTables().containsOnly(tableHelper::allNewAndEditedMandatoryPropertiesAreSet);
	}
	
	//method
	@Override
	public boolean canSaveChanges(final IDatabase<?> database) {
		return
		database.isOpen()
		&& database.isLinkedWithRealDatabase()
		&& allNewAndEditedMandatoryPropertiesAreSet(database);
	}
	
	//method
	@Override
	public <IMPL> IContainer<IEntity<IMPL>> getRefEntitiesInLocalData(final IDatabase<IMPL> database) {
		return database.getRefTables().toFromMany(ITable::technicalGetRefEntitiesInLocalData);
	}
	
	//method
	@Override
	@SuppressWarnings("unchecked")
	public <IMPL, E extends IEntity<IMPL>> ITable<IMPL, E> getRefTableForGivenEntity(
		final IDatabase<IMPL> database,
		final E entity
	) {
		return database.getRefTableByEntityType(entity.getClass());
	}
	
	//method
	@Override
	public boolean hasChanges(final IDatabase<?> database) {
		return database.getRefTables().containsAny(tableHelper::hasChanges);
	}
}
