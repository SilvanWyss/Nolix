//package declaration
package ch.nolix.system.nodedatabaserawschema.schemaadapter;

import ch.nolix.core.document.node.FileNode;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.coreapi.containerapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawschema.databaseinitializer.DatabaseInitializer;
import ch.nolix.system.nodedatabaserawschema.schemareader.SchemaReader;
import ch.nolix.system.nodedatabaserawschema.schemawriter.SchemaWriter;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDTO;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
public final class SchemaAdapter implements ISchemaAdapter {
	
	//static attribute
	private static final DatabaseInitializer databaseInitializer = new DatabaseInitializer();
	
	//static method
	public static SchemaAdapter forDatabaseNode(final IMutableNode<?> databaseNode) {
		return new SchemaAdapter(databaseNode);
	}
	
	//static method
	public static SchemaAdapter forDatabaseNodeInFile(final String filePath) {
		return new SchemaAdapter(new FileNode(filePath));
	}
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//attribute
	private final SchemaReader schemaReader;
	
	//attribute
	private final SchemaWriter schemaWriter;
	
	//constructor
	private SchemaAdapter(final IMutableNode<?> databaseNode) {
		
		databaseInitializer.initializeDatabaseIfNotInitialized(databaseNode);
		
		schemaReader = new SchemaReader(databaseNode);
		schemaWriter = new SchemaWriter(databaseNode);
		
		createCloseDependencyTo(schemaReader);
		createCloseDependencyTo(schemaWriter);
	}
	
	//method
	@Override
	public boolean columnIsEmpty(final String tableName, final String columnName) {
		return schemaReader.columnIsEmpty(tableName, columnName);
	}
	
	//method
	@Override
	public void addColumn(final String tableName, IColumnDTO column) {
		schemaWriter.addColumn(tableName, column);
	}
	
	//method
	@Override
	public void addTable(final ITableDTO table) {
		schemaWriter.addTable(table);
	}
	
	//method
	@Override
	public void deleteColumn(final String tableName, final String columnName) {
		schemaWriter.deleteColumn(tableName, columnName);
	}
	
	//method
	@Override
	public void deleteTable(final String tableName) {
		schemaWriter.deleteTable(tableName);
	}
	
	//method
	@Override
	public CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	@Override
	public int getSaveCount() {
		return schemaWriter.getSaveCount();
	}
	
	//method
	@Override
	public int getTableCount() {
		return schemaReader.getTableCount();
	}
	
	//method
	@Override
	public boolean hasChanges() {
		return schemaWriter.hasChanges();
	}
	
	//method
	@Override
	public IContainer<IColumnDTO> loadColumnsByTableId(final String tableId) {
		return schemaReader.loadColumnsByTableId(tableId);
	}
	
	//method
	@Override
	public IContainer<IColumnDTO> loadColumnsByTableName(final String tableName) {
		return schemaReader.loadColumnsByTableName(tableName);
	}
	
	//method
	@Override
	public IFlatTableDTO loadFlatTableById(final String id) {
		return schemaReader.loadFlatTableById(id);
	}
	
	//method
	@Override
	public IFlatTableDTO loadFlatTableByName(final String name) {
		return schemaReader.loadFlatTableById(name);
	}
	
	//method
	@Override
	public IContainer<IFlatTableDTO> loadFlatTables() {
		return schemaReader.loadFlatTables();
	}
	
	//method
	@Override
	public Time loadSchemaTimestamp() {
		return schemaReader.loadSchemaTimestamp();
	}
	
	//method
	@Override
	public ITableDTO loadTableById(final String id) {
		return schemaReader.loadTableById(id);
	}
	
	//method
	@Override
	public ITableDTO loadTableByName(final String name) {
		return schemaReader.loadTableByName(name);
	}
	
	//method
	@Override
	public IContainer<ITableDTO> loadTables() {
		return schemaReader.loadTables();
	}
	
	//method
	@Override
	public void noteClose() {
		//Does nothing.
	}
	
	//method
	@Override
	public void reset() {
		schemaWriter.reset();
	}
	
	//method
	@Override
	public void saveChangesAndReset() {
		schemaWriter.saveChangesAndReset();
	}
	
	//method
	@Override
	public void setColumnName(final String tableName, final String columnName, final String newColumnName) {
		schemaWriter.setColumnName(tableName, columnName, newColumnName);
	}
	
	//method
	@Override
	public void setColumnParametrizedPropertyType(
		final String columnId,
		final IParametrizedPropertyTypeDTO parametrizedPropertyType
	) {
		schemaWriter.setColumnParametrizedPropertyType(columnId, parametrizedPropertyType);
	}
	
	//method
	@Override
	public void setSchemaTimestamp(final ITime schemaTimestamp) {
		schemaWriter.setSchemaTimestamp(schemaTimestamp);
	}
	
	//method
	@Override
	public void setTableName(final String tableName, final String newTableName) {
		schemaWriter.setTableName(tableName, newTableName);
	}
}
