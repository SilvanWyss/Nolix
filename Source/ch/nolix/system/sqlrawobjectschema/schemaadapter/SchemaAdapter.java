//package declaration
package ch.nolix.system.sqlrawobjectschema.schemaadapter;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.element.time.base.Time;
import ch.nolix.system.sqlrawobjectschema.databaseinitializer.DatabaseInitializer;
import ch.nolix.system.sqlrawobjectschema.schemareader.SchemaReader;
import ch.nolix.system.sqlrawobjectschema.schemawriter.SchemaWriter;
import ch.nolix.techapi.rawobjectschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.ITableDTO;

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
		final ch.nolix.techapi.sqlschemaapi.schemaadapterapi.ISchemaAdapter mSQLSchemaAdapter,
		final ch.nolix.techapi.sqlschemaapi.schemadtoapi.IColumnDTO pSQLSaveStampColumnDTO
	) {
		
		databaseInitializer.initializeDatabaseIfNotInitialized(mSQLSchemaAdapter);
		
		rawSchemaReader = new SchemaReader(pSQLConnection, mSQLSchemaAdapter);
		rawSchemaWriter = new SchemaWriter(pSQLConnection, mSQLSchemaAdapter, pSQLSaveStampColumnDTO);
	}
	
	//method
	@Override
	public boolean columnIsEmpty(final String tableName, final String columnHeader) {
		return rawSchemaReader.columnIsEmpty(tableName, columnHeader);
	}
	
	//method
	@Override
	public void addColumn(final String tableName, IColumnDTO column) {
		rawSchemaWriter.addColumn(tableName, column);
	}
	
	//method
	@Override
	public void addTable(final ITableDTO table) {
		rawSchemaWriter.addTable(table);
	}
	
	//method
	@Override
	public void deleteColumn(final String tableName, final String columnHeader) {
		rawSchemaWriter.deleteColumn(tableName, columnHeader);
	}
	
	//method
	@Override
	public void deleteTable(final String tableName) {
		rawSchemaWriter.deleteTable(tableName);
	}
	
	//method
	@Override
	public boolean hasChanges() {
		return rawSchemaWriter.hasChanges();
	}
	
	//method
	@Override
	public LinkedList<IColumnDTO> loadColumns(final String tableName) {
		return rawSchemaReader.loadColumns(tableName);
	}
	
	//method
	@Override
	public LinkedList<IFlatTableDTO> loadFlatTables() {
		return rawSchemaReader.loadFlatTables();
	}
	
	//method
	@Override
	public Time loadSchemaTimestamp() {
		return rawSchemaReader.loadSchemaTimestamp();
	}
	
	//method
	@Override
	public ITableDTO loadTable(final String tableName) {
		return rawSchemaReader.loadTable(tableName);
	}
	
	//method
	@Override
	public LinkedList<ITableDTO> loadTables() {
		return rawSchemaReader.loadTables();
	}
	
	//method
	@Override
	public void saveChanges() {
		rawSchemaWriter.saveChanges();
	}
	
	//method
	@Override
	public void setColumnHeader(final String tableName, final String columnHeader, final String newColumnHeader) {
		rawSchemaWriter.setColumnHeader(tableName, columnHeader, newColumnHeader);
	}
	
	//method
	@Override
	public void setColumnParametrizedPropertyType(
		final String tableName,
		final String columnHeader,
		final IParametrizedPropertyTypeDTO parametrizedPropertyType
	) {
		rawSchemaWriter.setColumnParametrizedPropertyType(tableName, columnHeader, parametrizedPropertyType);
	}
	
	//method
	@Override
	public void setSchemaTimestamp(final Time schemaTimestamp) {
		rawSchemaWriter.setSchemaTimestamp(schemaTimestamp);
	}
	
	//method
	@Override
	public void setTableName(final String tableName, final String newTableName) {
		rawSchemaWriter.setTableName(tableName, newTableName);
	}
}
