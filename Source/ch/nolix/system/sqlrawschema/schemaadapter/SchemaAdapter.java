//package declaration
package ch.nolix.system.sqlrawschema.schemaadapter;

//own imports
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.core.sql.SQLConnectionPool;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.system.sqlrawschema.databaseinitializer.DatabaseInitializer;
import ch.nolix.system.sqlrawschema.schemareader.SchemaReader;
import ch.nolix.system.sqlrawschema.schemawriter.SchemaWriter;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDTO;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
public abstract class SchemaAdapter implements ISchemaAdapter {
	
	//static attribute
	private static final DatabaseInitializer databaseInitializer = new DatabaseInitializer();
	
	//attribute
	private final SchemaReader rawSchemaReader;
	
	//attribute
	private final SchemaWriter rawSchemaWriter;
	
	//attribute
	private final CloseController closeController = CloseController.forElement(this);
	
	//constructor
	protected SchemaAdapter(
		final String databaseName,
		final SQLConnectionPool pSQLConnectionPool,
		final ch.nolix.systemapi.sqlbasicschemaapi.schemaadapterapi.ISchemaAdapter pSQLSchemaAdapter
	) {
		
		databaseInitializer.initializeDatabaseIfNotInitialized(pSQLSchemaAdapter);
		
		rawSchemaReader =
		SchemaReader.forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndSchemaAdapter(
			databaseName,
			pSQLConnectionPool,
			pSQLSchemaAdapter
		);
		
		rawSchemaWriter =
		SchemaWriter.forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndSchemaAdapter(
			databaseName,
			pSQLConnectionPool,
			pSQLSchemaAdapter
		);
		
		getRefCloseController().createCloseDependencyTo(rawSchemaReader);
		getRefCloseController().createCloseDependencyTo(rawSchemaWriter);
	}
	
	//method
	@Override
	public final boolean columnIsEmpty(final String tableName, final String columnName) {
		return rawSchemaReader.columnIsEmpty(tableName, columnName);
	}
	
	//method
	@Override
	public final void addColumn(final String tableName, IColumnDTO column) {
		rawSchemaWriter.addColumn(tableName, column);
	}
	
	//method
	@Override
	public final void addTable(final ITableDTO table) {
		rawSchemaWriter.addTable(table);
	}
	
	//method
	@Override
	public final void deleteColumn(final String tableName, final String columnName) {
		rawSchemaWriter.deleteColumn(tableName, columnName);
	}
	
	//method
	@Override
	public final void deleteTable(final String tableName) {
		rawSchemaWriter.deleteTable(tableName);
	}
	
	//method
	@Override
	public final CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	@Override
	public final int getSaveCount() {
		return rawSchemaWriter.getSaveCount();
	}
	
	//method
	@Override
	public final int getTableCount() {
		return rawSchemaReader.getTableCount();
	}
	
	//method
	@Override
	public final boolean hasChanges() {
		return rawSchemaWriter.hasChanges();
	}
	
	//method
	@Override
	public final IContainer<IColumnDTO> loadColumnsByTableId(final String tableId) {
		return rawSchemaReader.loadColumnsByTableId(tableId);
	}
	
	//method
	@Override
	public final IContainer<IColumnDTO> loadColumnsByTableName(final String tableName) {
		return rawSchemaReader.loadColumnsByTableName(tableName);
	}
	
	//method
	@Override
	public final IFlatTableDTO loadFlatTableById(final String id) {
		return rawSchemaReader.loadFlatTableById(id);
	}
	
	//method
	@Override
	public IFlatTableDTO loadFlatTableByName(final String name) {
		return rawSchemaReader.loadFlatTableByName(name);
	}
	
	//method
	@Override
	public final IContainer<IFlatTableDTO> loadFlatTables() {
		return rawSchemaReader.loadFlatTables();
	}
	
	//method
	@Override
	public final Time loadSchemaTimestamp() {
		return rawSchemaReader.loadSchemaTimestamp();
	}
	
	//method
	@Override
	public final ITableDTO loadTableById(final String id) {
		return rawSchemaReader.loadTableById(id);
	}
	
	//method
	@Override
	public final ITableDTO loadTableByName(final String name) {
		return rawSchemaReader.loadTableByName(name);
	}
	
	//method
	@Override
	public final IContainer<ITableDTO> loadTables() {
		return rawSchemaReader.loadTables();
	}
	
	//method
	@Override
	public final void noteClose() {}
	
	//method
	@Override
	public final void reset() {
		rawSchemaWriter.reset();
	}
	
	//method
	@Override
	public final void saveChangesAndReset() {
		rawSchemaWriter.saveChangesAndReset();
	}
	
	//method
	@Override
	public final void setColumnName(final String tableName, final String columnName, final String newColumnName) {
		rawSchemaWriter.setColumnName(tableName, columnName, newColumnName);
	}
	
	//method
	@Override
	public final void setColumnParametrizedPropertyType(
		final String columnId,
		final IParametrizedPropertyTypeDTO parametrizedPropertyType
	) {
		rawSchemaWriter.setColumnParametrizedPropertyType(columnId, parametrizedPropertyType);
	}
	
	//method
	@Override
	public final void setSchemaTimestamp(final ITime schemaTimestamp) {
		rawSchemaWriter.setSchemaTimestamp(schemaTimestamp);
	}
	
	//method
	@Override
	public final void setTableName(final String tableName, final String newTableName) {
		rawSchemaWriter.setTableName(tableName, newTableName);
	}
}
