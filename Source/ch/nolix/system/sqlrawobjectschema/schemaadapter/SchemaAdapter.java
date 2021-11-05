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
	private final SchemaReader mSQLIntermediateSchemaReader;
	private final SchemaWriter mSQLIntermediateSchemaWriter;
	
	//constructor
	public SchemaAdapter(
		final SQLConnection pSQLConnection,
		final ch.nolix.techapi.sqlschemaapi.schemaadapterapi.ISchemaAdapter mSQLSchemaAdapter,
		final ch.nolix.techapi.sqlschemaapi.schemadtoapi.IColumnDTO pSQLSaveStampColumnDTO
	) {
		
		databaseInitializer.initializeDatabaseIfNotInitialized(mSQLSchemaAdapter);
		
		mSQLIntermediateSchemaReader = new SchemaReader(pSQLConnection, mSQLSchemaAdapter);
		mSQLIntermediateSchemaWriter = new SchemaWriter(pSQLConnection, mSQLSchemaAdapter, pSQLSaveStampColumnDTO);
	}
	
	//method
	@Override
	public boolean columnIsEmpty(final String tableName, final String columnHeader) {
		return mSQLIntermediateSchemaReader.columnIsEmpty(tableName, columnHeader);
	}
	
	//method
	@Override
	public void addColumn(final String tableName, IColumnDTO column) {
		mSQLIntermediateSchemaWriter.addColumn(tableName, column);
	}
	
	//method
	@Override
	public void addTable(final ITableDTO table) {
		mSQLIntermediateSchemaWriter.addTable(table);
	}
	
	//method
	@Override
	public void deleteColumn(final String tableName, final String columnHeader) {
		mSQLIntermediateSchemaWriter.deleteColumn(tableName, columnHeader);
	}
	
	//method
	@Override
	public void deleteTable(final String tableName) {
		mSQLIntermediateSchemaWriter.deleteTable(tableName);
	}
	
	//method
	@Override
	public boolean hasChanges() {
		return mSQLIntermediateSchemaWriter.hasChanges();
	}
	
	//method
	@Override
	public LinkedList<IColumnDTO> loadColumns(final String tableName) {
		return mSQLIntermediateSchemaReader.loadColumns(tableName);
	}
	
	//method
	@Override
	public LinkedList<IFlatTableDTO> loadFlatTables() {
		return mSQLIntermediateSchemaReader.loadFlatTables();
	}
	
	//method
	@Override
	public Time loadSchemaTimestamp() {
		return mSQLIntermediateSchemaReader.loadSchemaTimestamp();
	}
	
	//method
	@Override
	public ITableDTO loadTable(final String tableName) {
		return mSQLIntermediateSchemaReader.loadTable(tableName);
	}
	
	//method
	@Override
	public LinkedList<ITableDTO> loadTables() {
		return mSQLIntermediateSchemaReader.loadTables();
	}
	
	//method
	@Override
	public void saveChanges() {
		mSQLIntermediateSchemaWriter.saveChanges();
	}
	
	//method
	@Override
	public void setColumnHeader(final String tableName, final String columnHeader, final String newColumnHeader) {
		mSQLIntermediateSchemaWriter.setColumnHeader(tableName, columnHeader, newColumnHeader);
	}
	
	//method
	@Override
	public void setColumnParametrizedPropertyType(
		final String tableName,
		final String columnHeader,
		final IParametrizedPropertyTypeDTO parametrizedPropertyType
	) {
		mSQLIntermediateSchemaWriter.setColumnParametrizedPropertyType(tableName, columnHeader, parametrizedPropertyType);
	}
	
	//method
	@Override
	public void setSchemaTimestamp(final Time schemaTimestamp) {
		mSQLIntermediateSchemaWriter.setSchemaTimestamp(schemaTimestamp);
	}
	
	//method
	@Override
	public void setTableName(final String tableName, final String newTableName) {
		mSQLIntermediateSchemaWriter.setTableName(tableName, newTableName);
	}
}
