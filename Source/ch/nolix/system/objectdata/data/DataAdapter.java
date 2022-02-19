//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.system.objectdata.datahelper.DatabaseHelper;
import ch.nolix.systemapi.objectdataapi.dataadapterapi.IDataAdapter;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.datahelperapi.IDatabaseHelper;

//class
public abstract class DataAdapter implements IDataAdapter<DataImplementation> {
	
	//static attribute
	private static final IDatabaseHelper databaseHelper = new DatabaseHelper();
	
	//attribute
	private final Database database;
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//constructor
	private DataAdapter(final Database database) {
		
		//TODO: Check if database is valid.
		this.database = database;
	}
	
	//method
	@Override
	public final CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	@Override
	public <E extends IEntity<DataImplementation>> ITable<DataImplementation, E> getRefTableByEntityType(
		final Class<E> entityType
	) {
		return database.getRefTableByEntityClass(entityType);
	}
	
	//method
	@Override
	public final boolean hasChanges() {
		return databaseHelper.hasChanges(database);
	}
	
	//method
	@Override
	public final <E extends IEntity<DataImplementation>> IDataAdapter<DataImplementation> insert(final E entity) {
		
		database.insert(entity);
		
		return this;
	}
	
	//method
	@Override
	public final void reset() {
		database.internalReset();
	}
}
