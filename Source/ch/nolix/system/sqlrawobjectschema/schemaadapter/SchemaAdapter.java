//package declaration
package ch.nolix.system.sqlrawobjectschema.schemaadapter;

import ch.nolix.core.container.LinkedList;
import ch.nolix.core.sql.SQLConnection;
import ch.nolix.element.time.base.Time;
import ch.nolix.system.sqlrawobjectschema.databaseinitializer.DatabaseInitializer;
import ch.nolix.system.sqlrawobjectschema.schemareader.SchemaReader;
import ch.nolix.system.sqlrawobjectschema.schemawriter.SchemaWriter;
import ch.nolix.systemapi.rawobjectschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.systemapi.rawobjectschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.systemapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.rawobjectschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;
import ch.nolix.systemapi.rawobjectschemaapi.schemadtoapi.ITableDTO;

//class
public abstract class SchemaAdapter implements ISchemaAdapter {
	
	//static attribute
	private static final DatabaseInitializer databaseInitializer = new DatabaseInitializer();
	
	//attributes
	private final SchemaReader rawSchemaReader;
	private final SchemaWriter rawSchemaWriter;
	
	//constructor
	public SchemaAdapter(
		final SQLConnection pSQLConnection,
		final ch.nolix.systemapi.sqlschemaapi.schemaadapterapi.ISchemaAdapter pSQLSchemaAdapter,
		final ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDTO pSQLSaveStampColumnDTO
	) {
		
		databaseInitializer.initializeDatabaseIfNotInitialized(pSQLSchemaAdapter);
		
		rawSchemaReader = new SchemaReader(pSQLConnection, pSQLSchemaAdapter);
		rawSchemaWriter = new SchemaWriter(pSQLConnection, pSQLSchemaAdapter, pSQLSaveStampColumnDTO);
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
	public final int getSaveCount() {
		return rawSchemaWriter.getSaveCount();
	}
	
	//method
	@Override
	public final boolean hasChanges() {
		return rawSchemaWriter.hasChanges();
	}
	
	//method
	@Override
	public final LinkedList<IColumnDTO> loadColumnsByTableId(final String tableId) {
		return rawSchemaReader.loadColumnsByTableId(tableId);
	}
	
	//method
	@Override
	public final LinkedList<IColumnDTO> loadColumnsByTableName(final String tableName) {
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
	public final LinkedList<IFlatTableDTO> loadFlatTables() {
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
	public final LinkedList<ITableDTO> loadTables() {
		return rawSchemaReader.loadTables();
	}
	
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
	public final void setSchemaTimestamp(final Time schemaTimestamp) {
		rawSchemaWriter.setSchemaTimestamp(schemaTimestamp);
	}
	
	//method
	@Override
	public final void setTableName(final String tableName, final String newTableName) {
		rawSchemaWriter.setTableName(tableName, newTableName);
	}
}
