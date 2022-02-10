//package declaration
package ch.nolix.system.sqlrawobjectdata.datawriter;

//own imports
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.sql.SQLCollector;
import ch.nolix.core.sql.SQLConnection;
import ch.nolix.system.sqlrawobjectdata.sqlapi.IMultiValueStatementCreator;
import ch.nolix.system.sqlrawobjectdata.sqlapi.IRecordStatementCreator;
import ch.nolix.system.sqlrawobjectdata.sqlapi.ISQLSyntaxProvider;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordHeadDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//class
public final class InternalDataWriter {
	
	//attribute
	private final SQLCollector mSQLCollector = new SQLCollector();
	
	//attribute
	private final SQLConnection mSQLConnection;
	
	//attribute
	private final IRecordStatementCreator recordStatementCreator;
	
	//attribute
	private final IMultiValueStatementCreator multiValueStatementCreator;

	//constructor
	public InternalDataWriter(
		final SQLConnection pSQLConnection,
		final ISQLSyntaxProvider pSQLSyntaxProvider
	) {
		
		Validator.assertThat(pSQLConnection).thatIsNamed(SQLConnection.class).isNotNull();
		
		mSQLConnection = pSQLConnection;
		recordStatementCreator = pSQLSyntaxProvider.getRecordStatementCreator();
		multiValueStatementCreator = pSQLSyntaxProvider.getMultiValueStatemeentCreator();
	}
	
	//method
	public void deleteEntriesFromMultiValue(
		final String recordId,
		final String multiValueColumnId
	) {
		mSQLCollector.addSQLStatement(
			multiValueStatementCreator.createStatementToDeleteEntriesFromMultiValue(recordId, multiValueColumnId)
		);
	}
	
	//method
	public void deleteEntryFromMultiValue(
		final String recordId,
		final String multiValueColumnId,
		final String entry
	) {
		mSQLCollector.addSQLStatement(
			multiValueStatementCreator.createStatementToDeleteEntryFromMultiValue(recordId, multiValueColumnId, entry)
		);
	}
	
	//method
	public void deleteRecordFromTable(final String tableName, final IRecordHeadDTO recordHead) {
		mSQLCollector.addSQLStatement(
			recordStatementCreator.createStatementToDeleteRecordFromTable(tableName, recordHead)
		);
	}
	
	//method
	public boolean hasChanges() {
		return mSQLCollector.containsAny();
	}
	
	//method
	public void insertEntryIntoMultiValue(
		final String recordId,
		final String multiValueColumnId,
		final String entry
	) {
		mSQLCollector.addSQLStatement(
			multiValueStatementCreator.createQueryToInsertEntryIntoMultiValue(recordId, multiValueColumnId, entry)
		);
	}
	
	//method
	public void insertRecordIntoTable(final String tableName, final IRecordDTO record) {
		mSQLCollector.addSQLStatement(
			recordStatementCreator.createStatementToInsertRecordIntoTable(tableName, record)
		);
	}
	
	//method
	public void reset() {
		mSQLCollector.clear();
	}
	
	//method
	public void saveChangesAndReset() {
		
		mSQLCollector.executeUsingConnection(mSQLConnection);
		
		reset();
	}
	
	//method
	public void updateRecordOnTable(final String tableName, final IRecordUpdateDTO recordUpdate) {
		mSQLCollector.addSQLStatement(
			recordStatementCreator.createStatementToUpdateRecordOnTable(tableName, recordUpdate)
		);
	}
}
