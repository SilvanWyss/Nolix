//package declaration
package ch.nolix.system.sqlrawobjectdata.datawriter;

//own imports
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.sql.SQLConnection;
import ch.nolix.core.sql.SQLExecutor;
import ch.nolix.system.sqlrawobjectdata.sqlapi.IMultiValueStatementCreator;
import ch.nolix.system.sqlrawobjectdata.sqlapi.IRecordStatementCreator;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordHeadDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//class
public final class InternalDataWriter {
	
	//attribute
	private final SQLExecutor mSQLExecutor;
	
	//attribute
	private final IRecordStatementCreator recordStatementCreator;
	
	//attribute
	private final IMultiValueStatementCreator multiValueStatementCreator;

	//constructor
	public InternalDataWriter(
		final SQLConnection pSQLConnection,
		final IRecordStatementCreator recordStatementCreator,
		final IMultiValueStatementCreator multiValueStatementCreator
	) {
		
		Validator.assertThat(recordStatementCreator).thatIsNamed(IRecordStatementCreator.class).isNotNull();
		Validator.assertThat(multiValueStatementCreator).thatIsNamed(IMultiValueStatementCreator.class).isNotNull();
		
		mSQLExecutor = new SQLExecutor(pSQLConnection);
		this.recordStatementCreator = recordStatementCreator;
		this.multiValueStatementCreator = multiValueStatementCreator;
	}
	
	//method
	public void deleteEntriesFromMultiValue(
		final String recordId,
		final String multiValueColumnId
	) {
		mSQLExecutor.addSQLStatement(
			multiValueStatementCreator.createStatementToDeleteEntriesFromMultiValue(recordId, multiValueColumnId)
		);
	}
	
	//method
	public void deleteEntryFromMultiValue(
		final String recordId,
		final String multiValueColumnId,
		final String entry
	) {
		mSQLExecutor.addSQLStatement(
			multiValueStatementCreator.createStatementToDeleteEntryFromMultiValue(recordId, multiValueColumnId, entry)
		);
	}
	
	//method
	public void deleteRecordFromTable(final String tableName, final IRecordHeadDTO recordHead) {
		mSQLExecutor.addSQLStatement(
			recordStatementCreator.createStatementToDeleteRecordFromTable(tableName, recordHead)
		);
	}
	
	//method
	public boolean hasChanges() {
		return mSQLExecutor.getSQLStatements().containsAny();
	}
	
	//method
	public void insertEntryIntoMultiValue(
		final String recordId,
		final String multiValueColumnId,
		final String entry
	) {
		mSQLExecutor.addSQLStatement(
			multiValueStatementCreator.createQueryToInsertEntryIntoMultiValue(recordId, multiValueColumnId, entry)
		);
	}
	
	//method
	public void insertRecordIntoTable(final String tableName, final IRecordDTO record) {
		mSQLExecutor.addSQLStatement(recordStatementCreator.createStatementToInsertRecordIntoTable(tableName, record));
	}
	
	//method
	public void updateRecordOnTable(final String tableName, final IRecordUpdateDTO recordUpdate) {
		mSQLExecutor.addSQLStatement(recordStatementCreator.createStatementToUpdateRecordOnTable(tableName, recordUpdate));
	}
	
	//method
	public void saveChanges() {
		mSQLExecutor.execute();
	}
}
