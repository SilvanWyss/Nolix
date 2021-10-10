//package declaration
package ch.nolix.system.sqlintermediatedata.dataadapter;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.system.sqlintermediatedata.datareader.DataReader;
import ch.nolix.system.sqlintermediatedata.datawriter.DataWriter;
import ch.nolix.system.sqlintermediatedata.sqlapi.IQueryCreator;
import ch.nolix.system.sqlintermediatedata.sqlapi.IStatementCreator;
import ch.nolix.techapi.intermediatedataapi.dataadapterapi.IDataAdapter;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.ILoadedRecordDTO;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IRecordDTO;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IRecordDeletionDTO;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IRecordUpdateDTO;
import ch.nolix.techapi.sqlschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public abstract class DataAdapter implements IDataAdapter {
	
	//attributes
	private final DataReader dataReader;
	private final DataWriter dataWriter;
	
	//constructor
	public DataAdapter(
		final SQLConnection pSQLConnection,
		final ISchemaAdapter schemaAdapter,
		final IQueryCreator queryCreator,
		final IStatementCreator statementCreator
	) {
		dataReader = new DataReader(pSQLConnection, schemaAdapter, queryCreator);
		dataWriter = new DataWriter(pSQLConnection, statementCreator);
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
