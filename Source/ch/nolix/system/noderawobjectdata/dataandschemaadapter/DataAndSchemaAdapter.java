//package declaration
package ch.nolix.system.noderawobjectdata.dataandschemaadapter;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.filenode.FileNode;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.element.time.base.Time;
import ch.nolix.system.noderawobjectdata.datareader.DataReader;
import ch.nolix.system.noderawobjectdata.datareader.TableDefinitionLoader;
import ch.nolix.system.noderawobjectdata.datawriter.DataWriter;
import ch.nolix.systemapi.rawobjectdataapi.dataadapterapi.IDataReader;
import ch.nolix.systemapi.rawobjectdataapi.dataadapterapi.IDataWriter;
import ch.nolix.systemapi.rawobjectdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.ILoadedRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;
import ch.nolix.systemapi.rawobjectschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.systemapi.rawobjectschemaapi.schemaadapterapi.ISchemaReader;
import ch.nolix.systemapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.rawobjectschemaapi.schemadtoapi.ITableDTO;

//class
public final class DataAndSchemaAdapter implements IDataAndSchemaAdapter {
	
	//static attribute
	private static final TableDefinitionLoader tableDefinitionLoader = new TableDefinitionLoader();
	
	//static method
	public DataAndSchemaAdapter forNodeDatabase(final BaseNode nodeDatabase) {
		return new DataAndSchemaAdapter(nodeDatabase);
	}
	
	//static method
	public DataAndSchemaAdapter  forNodeDatabaseInFile(final String filePath) {
		return new DataAndSchemaAdapter(new FileNode(filePath));
	}
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//attribute
	private final IDataReader dataReader;
	
	//attribute
	private final IDataWriter dataWriter;
	
	//attribute
	private final ISchemaReader schemaReader;
	
	//constructor
	private DataAndSchemaAdapter(final BaseNode nodeDatabase) {
		
		final var tableInfos = tableDefinitionLoader.loadTableDefinitionsFromDatabaseNode(nodeDatabase);
		
		dataReader = new DataReader(nodeDatabase, tableInfos);
		dataWriter = new DataWriter(nodeDatabase, tableInfos);
		schemaReader = new ch.nolix.system.noderawobjectschema.schemareader.SchemaReader(nodeDatabase);
		
		createCloseDependencyTo(dataReader);
		createCloseDependencyTo(dataWriter);
		createCloseDependencyTo(schemaReader);
	}
	
	//method
	@Override
	public  boolean columnIsEmpty(final String tableName, final String columnName) {
		return schemaReader.columnIsEmpty(tableName, columnName);
	}
	
	//method
	@Override
	public  void deleteEntriesFromMultiReference(
		final String tableName,
		final String entityId,
		final String multiReferenceColumnName
	) {
		dataWriter.deleteEntriesFromMultiReference(tableName, entityId, multiReferenceColumnName);
	}
	
	//method
	@Override
	public  void deleteEntriesFromMultiValue(
		final String tableName,
		final String entityId,
		final String multiValueColumnName
	) {
		dataWriter.deleteEntriesFromMultiValue(tableName, entityId, multiValueColumnName);
	}
	
	//method
	@Override
	public  void deleteEntryFromMultiReference(
		final String tableName,
		final String entityId,
		final String multiRefereceColumnName,
		final String referencedEntityId
	) {
		dataWriter.deleteEntryFromMultiReference(tableName, entityId, multiRefereceColumnName, referencedEntityId);
	}
	
	//method
	@Override
	public  void deleteEntryFromMultiValue(
		final String tableName,
		final String entityId,
		final String multiValueColumnName,
		final String entry
	) {
		dataWriter.deleteEntryFromMultiValue(tableName, entityId, multiValueColumnName, entry);
	}
	
	//method
	@Override
	public  void deleteRecordFromTable(final String tableName, final IEntityHeadDTO entity) {
		dataWriter.deleteRecordFromTable(tableName, entity);
	}
	
	//method
	@Override
	public  CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	@Override
	public  int getSaveCount() {
		return dataWriter.getSaveCount();
	}
	
	//method
	@Override
	public  boolean hasChanges() {
		return dataWriter.hasChanges();
	}
	
	//method
	@Override
	public  void insertEntryIntoMultiReference(
		final String tableName,
		final String entityId,
		final String multiReferenceColumnName,
		final String referencedEntityId
	) {
		dataWriter.insertEntryIntoMultiReference(tableName, entityId, multiReferenceColumnName, referencedEntityId);
	}
	
	//method
	@Override
	public  void insertEntryIntoMultiValue(
		final String tableName,
		final String entityId,
		final String multiValueColumnName,
		final String entry
	) {
		dataWriter.insertEntryIntoMultiValue(tableName, entityId, multiValueColumnName, entry);
	}
	
	//method
	@Override
	public  void insertRecordIntoTable(final String tableName, final IRecordDTO record) {
		dataWriter.insertRecordIntoTable(tableName, record);
	}
	
	//method
	@Override
	public  LinkedList<String> loadAllMultiReferenceEntriesForRecord(
		final String tableName,
		final String entityId,
		final String multiReferenceColumnName
	) {
		return dataReader.loadAllMultiReferenceEntriesForRecord(tableName, entityId, multiReferenceColumnName);
	}
	
	//method
	@Override
	public  LinkedList<Object> loadAllMultiValueEntriesFromRecord(
		final String tableName,
		final String entityId,
		final String multiFieldColumnName
	) {
		return dataReader.loadAllMultiValueEntriesFromRecord(tableName, entityId, multiFieldColumnName);
	}
	
	//method	
	@Override
	public  LinkedList<ILoadedRecordDTO> loadAllRecordsFromTable(final String tableName) {
		return dataReader.loadAllRecordsFromTable(tableName);
	}
	
	//method
	@Override
	public  LinkedList<IColumnDTO> loadColumnsByTableId(final String tableId) {
		return schemaReader.loadColumnsByTableId(tableId);
	}
	
	//method
	@Override
	public  LinkedList<IColumnDTO> loadColumnsByTableName(final String tableName) {
		return schemaReader.loadColumnsByTableName(tableName);
	}
	
	//method
	@Override
	public IFlatTableDTO loadFlatTableById(String id) {
		return schemaReader.loadFlatTableById(id);
	}
	
	//method
	@Override
	public  IFlatTableDTO loadFlatTableByName(final String name) {
		return schemaReader.loadFlatTableByName(name);
	}
	
	//method
	@Override
	public  LinkedList<IFlatTableDTO> loadFlatTables() {
		return schemaReader.loadFlatTables();
	}
	
	//method
	@Override
	public  ILoadedRecordDTO loadRecordFromTableById(final String tableName, final String id) {
		return dataReader.loadRecordFromTableById(tableName, id);
	}
	
	//method
	@Override
	public  Time loadSchemaTimestamp() {
		return schemaReader.loadSchemaTimestamp();
	}
	
	//method
	@Override
	public  ITableDTO loadTableById(final String id) {
		return schemaReader.loadTableById(id);
	}
	
	//method
	@Override
	public  ITableDTO loadTableByName(final String name) {
		return schemaReader.loadTableByName(name);
	}
	
	//method
	@Override
	public  LinkedList<ITableDTO> loadTables() {
		return schemaReader.loadTables();
	}
	
	//method
	@Override
	public  void noteClose() {}
	
	//method
	@Override
	public  void reset() {
		dataWriter.reset();
	}
	
	//method
	@Override
	public  void saveChangesAndReset() {
		dataWriter.saveChangesAndReset();
	}
	
	//method
	@Override
	public  void setEntityAsUpdated(final String tableName, final IEntityHeadDTO entity) {
		dataWriter.setEntityAsUpdated(tableName, entity);
	}
	
	//method
	@Override
	public  boolean tableContainsRecordWithGivenValueAtColumn(
		final String tableName,
		final String columnName,
		final String value
	) {
		return dataReader.tableContainsRecordWithGivenValueAtColumn(tableName, columnName, value);
	}
	
	//method
	@Override
	public  void updateRecordOnTable(final String tableName, final IRecordUpdateDTO recordUpdate) {
		dataWriter.updateRecordOnTable(tableName, recordUpdate);
	}
}
