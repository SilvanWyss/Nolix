//package declaration
package ch.nolix.system.objectdata.datahelper;

import ch.nolix.coreapi.containerapi.IContainer;
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.systemapi.objectdataapi.dataapi.IDatabase;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.datahelperapi.IDatabaseHelper;
import ch.nolix.systemapi.objectdataapi.datahelperapi.ITableHelper;

//class
public final class DatabaseHelper extends DatabaseObjectHelper implements IDatabaseHelper {
	
	//static attribute
	private final ITableHelper tableHelper = new TableHelper();
	
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
		return database.getRefTableByEntityClass(entity.getClass());
	}
	
	//method
	@Override
	public boolean hasChanges(final IDatabase<?> database) {
		return database.getRefTables().containsAny(tableHelper::hasChanges);
	}
}
