//package declaration
package ch.nolix.system.noderawobjectschema.schemaadapter;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.filenode.FileNode;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.element.time.base.Time;
import ch.nolix.system.noderawobjectschema.databaseinitializer.DatabaseInitializer;
import ch.nolix.system.noderawobjectschema.schemareader.SchemaReader;
import ch.nolix.system.noderawobjectschema.schemawriter.SchemaWriter;
import ch.nolix.techapi.rawobjectschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.ITableDTO;

//class
public final class SchemaAdapter implements ISchemaAdapter {
	
	//static attribute
	private static final DatabaseInitializer databaseInitializer = new DatabaseInitializer();
	
	//static method
	public static SchemaAdapter forDatabaseNode(final BaseNode databaseNode) {
		return new SchemaAdapter(databaseNode);
	}
	
	//static method
	public static SchemaAdapter forDatabaseNodeInFile(final String filePath) {
		return new SchemaAdapter(new FileNode(filePath));
	}
	
	//attributes
	private final SchemaReader schemaReader;
	private final SchemaWriter schemaWriter;
	
	//constructor
	private SchemaAdapter(final BaseNode databaseNode) {
		
		databaseInitializer.initializeDatabaseIfNotInitialized(databaseNode);
		
		schemaReader = new SchemaReader(databaseNode);
		schemaWriter = new SchemaWriter(databaseNode);
	}
	
	//method
	@Override
	public boolean columnIsEmpty(final String tableName, final String columnHeader) {
		return schemaReader.columnIsEmpty(tableName, columnHeader);
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
	public void deleteColumn(final String tableName, final String columnHeader) {
		schemaWriter.deleteColumn(tableName, columnHeader);
	}
	
	//method
	@Override
	public void deleteTable(final String tableName) {
		schemaWriter.deleteTable(tableName);
	}
	
	//method
	@Override
	public boolean hasChanges() {
		return schemaWriter.hasChanges();
	}
	
	//method
	@Override
	public LinkedList<IColumnDTO> loadColumns(final String tableName) {
		return schemaReader.loadColumns(tableName);
	}
	
	//method
	@Override
	public LinkedList<IFlatTableDTO> loadFlatTables() {
		return schemaReader.loadFlatTables();
	}
	
	//method
	@Override
	public Time loadSchemaTimestamp() {
		return schemaReader.loadSchemaTimestamp();
	}
	
	//method
	@Override
	public void saveChanges() {
		schemaWriter.saveChanges();
	}
	
	//method
	@Override
	public void setColumnHeader(final String tableName, final String columnHeader, final String newColumnHeader) {
		schemaWriter.setColumnHeader(tableName, columnHeader, newColumnHeader);
	}
	
	//method
	@Override
	public void setColumnParametrizedPropertyType(
		final String tableName,
		final String columnHeader,
		final IParametrizedPropertyTypeDTO parametrizedPropertyType
	) {
		schemaWriter.setColumnParametrizedPropertyType(tableName, columnHeader, parametrizedPropertyType);
	}
	
	//method
	@Override
	public void setSchemaTimestamp(final Time schemaTimestamp) {
		schemaWriter.setSchemaTimestamp(schemaTimestamp);
	}
	
	//method
	@Override
	public void setTableName(final String tableName, final String newTableName) {
		schemaWriter.setTableName(tableName, newTableName);
	}
}
