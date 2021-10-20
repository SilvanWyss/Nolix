//package declaration
package ch.nolix.system.nodeintermediatedata.dataadapter;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.system.nodeintermediatedata.datareader.DataReader;
import ch.nolix.system.nodeintermediatedata.datawriter.DataWriter;
import ch.nolix.system.nodeintermediatedata.tabledefinition.TableDefinitionLoader;
import ch.nolix.techapi.intermediatedataapi.dataadapterapi.IDataAdapter;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.ILoadedRecordDTO;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IRecordDTO;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IRecordDeletionDTO;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IRecordUpdateDTO;

//class
public final class DataAdapter implements IDataAdapter {
	
	//static attribute
	private static final TableDefinitionLoader tableDefinitionLoader = new TableDefinitionLoader();
	
	//attributes
	private final DataReader dataReader;
	private final DataWriter dataWriter;
	
	//constructor
	public DataAdapter(final BaseNode databaseNode) {
		
		final var tableDefinitions = tableDefinitionLoader.loadTableDefinitionsFromDatabaseNode(databaseNode);
		
		dataReader = new DataReader(databaseNode, tableDefinitions);
		dataWriter = new DataWriter(databaseNode, tableDefinitions);
	}
	
	//method
	@Override
	public LinkedList<ILoadedRecordDTO> loadAllRecordsFromTable(String tableName) {
		return dataReader.loadAllRecordsFromTable(tableName);
	}
	
	//method
	@Override
	public ILoadedRecordDTO loadRecordFromTableById(String tableName, String id) {
		return dataReader.loadRecordFromTableById(tableName, id);
	}
	
	//method
	@Override
	public void deleteRecordFromTable(String tableName, IRecordDeletionDTO recordDeletion) {
		dataWriter.deleteRecordFromTable(tableName, recordDeletion);
	}
	
	//method
	@Override
	public boolean hasChanges() {
		return dataWriter.hasChanges();
	}
	
	//method
	@Override
	public void insertRecordIntoTable(String tableName, IRecordDTO record) {
		dataWriter.insertRecordIntoTable(tableName, record);
	}
	
	//method
	@Override
	public void saveChanges() {
		dataWriter.saveChanges();
	}
	
	//method
	@Override
	public void updateRecordOnTable(String tableName, IRecordUpdateDTO recordUpdate) {
		dataWriter.updateRecordOnTable(tableName, recordUpdate);
	}
}
