//package declaration
package ch.nolix.system.objectdata.data;

import ch.nolix.core.functionuniversalapi.IElementGetter;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.system.objectdata.datahelper.DatabaseHelper;
import ch.nolix.system.objectschema.parametrizedpropertytype.SchemaImplementation;
import ch.nolix.systemapi.objectdataapi.dataadapterapi.IDataAdapter;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ISchema;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.datahelperapi.IDatabaseHelper;
import ch.nolix.systemapi.objectschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.systemapi.rawdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;

//class
public abstract class DataAdapter implements IDataAdapter<DataImplementation> {
	
	//static attribute
	private static final IDatabaseHelper databaseHelper = new DatabaseHelper();
	
	//static attribute
	private static final SchemaInitializer schemaInitializer = new SchemaInitializer();
		
	//static attribute
	private static final PersistenceManager persistenceManager = new PersistenceManager();
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//attribute
	private final Database database;
	
	//attribute
	private int saveCount;
	
	//constructor
	public DataAdapter(
		final ISchemaAdapter<SchemaImplementation> schemaAdapter,
		final ISchema<DataImplementation> schema,
		final IElementGetter<IDataAndSchemaAdapter> dataAndSchemaAdapterCreator
	) {
		
		schemaInitializer.initializeDatabaseWithGivenSchemaUsingGivenSchemaAdapterIfDatabaseIsEmpty(
			schema,
			schemaAdapter
		);
		schemaAdapter.close();
		
		final var dataAndSchemaAdapter = dataAndSchemaAdapterCreator.getOutput();
		database = Database.withDataAndSchemaAdapterAndSchema(dataAndSchemaAdapter, schema);
		getRefCloseController().createCloseDependencyTo(dataAndSchemaAdapter);
	}
	
	//method
	@Override
	public final CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	@Override
	public final <E extends IEntity<DataImplementation>> ITable<DataImplementation, E> getRefTableByEntityType(
		final Class<E> entityType
	) {
		return database.getRefTableByEntityClass(entityType);
	}
	
	//method
	@Override
	public final int getSaveCount() {
		return saveCount;
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
	public final void noteClose() {
		database.internalClose();
	}
	
	//method
	@Override
	public final void reset() {
		database.internalReset();
	}
	
	//method
	@Override
	public final void saveChangesAndReset() {
		try {
			
			persistenceManager.peristChanges(database);
			
			saveCount++;
		} finally {
			reset();
		}
	}
}
