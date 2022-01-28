//package declaration
package ch.nolix.system.objectdata.datahelper;

//own imports
import ch.nolix.system.database.databaseobjecthelper.DatabaseObjectHelper;
import ch.nolix.systemapi.objectdataapi.dataapi.IDatabase;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.datahelperapi.IDatabaseHelper;

//class
public final class DatabaseHelper extends DatabaseObjectHelper implements IDatabaseHelper {
	
	//method
	@Override
	@SuppressWarnings("unchecked")
	public <IMPL, E extends IEntity<IMPL>> ITable<IMPL, E> getRefTableForGivenEntity(
		final IDatabase<IMPL> database,
		final E entity
	) {
		return database.getRefTableByEntityClass(entity.getClass());
	}
}
