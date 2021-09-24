//package declaration
package ch.nolix.system.sqlintermediatedata.dataadapter;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.techapi.intermediatedataapi.dataadapterapi.IDataAdapter;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IRecordDTO;
import ch.nolix.techapi.intermediatedataapi.sqldatalanguageapi.IDataQueryCreator;
import ch.nolix.techapi.intermediatedataapi.sqldatalanguageapi.IDataStatementCreator;

//class
public final class DataAdapter implements IDataAdapter {
	
	//attribute
	private final DataReader dataReader;
	private final DataWriter dataWriter;
	
	//constructor
	public DataAdapter(
		final SQLConnection pSQLConnection,
		final IDataQueryCreator dataQueryCreator,
		final IDataStatementCreator dataStatementCreator
	) {
		dataReader = new DataReader(pSQLConnection, dataQueryCreator);
		dataWriter = new DataWriter(pSQLConnection, dataStatementCreator);
	}
	
	//method
	@Override
	public void addRecordToTable(final String tableName, final IRecordDTO record) {
		dataWriter.addRecordToTable(tableName, record);
	}
	
	//method
	@Override
	public void deleteRecordFromTable(final String tableName, final IRecordDTO record) {
		dataWriter.deleteRecordFromTable(tableName, record);
	}
	
	//method
	@Override
	public void editRecordOnTable(final String tableName, final IRecordDTO record) {
		dataWriter.editRecordOnTable(tableName, record);
	}
	
	//method
	@Override
	public boolean hasChanges() {
		return dataWriter.hasChanges();
	}
	
	//method
	@Override
	public LinkedList<IRecordDTO> loadAllRecordsFromTable(final String tableName) {
		return dataReader.loadAllRecordsFromTable(tableName);
	}
	
	//method
	@Override
	public void saveChanges() {
		dataWriter.saveChanges();
	}
}
