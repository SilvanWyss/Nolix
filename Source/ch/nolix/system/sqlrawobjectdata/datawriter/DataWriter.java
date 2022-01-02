//package declaration
package ch.nolix.system.sqlrawobjectdata.datawriter;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.common.sql.SQLExecutor;
import ch.nolix.system.sqlrawobjectdata.sqlapi.IRecordStatementCreator;
import ch.nolix.techapi.rawobjectdataapi.dataadapterapi.IDataWriter;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordDTO;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordDeletionDTO;
import ch.nolix.techapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//class
public final class DataWriter implements IDataWriter {
	
	//attributes
	private final SQLExecutor mSQLExecutor;
	private final IRecordStatementCreator recordStatementCreator;
	
	//constructor
	public DataWriter(final SQLConnection pSQLConnection, final IRecordStatementCreator recordStatementCreator) {
		
		Validator.assertThat(recordStatementCreator).thatIsNamed(IRecordStatementCreator.class).isNotNull();
		
		mSQLExecutor = new SQLExecutor(pSQLConnection);
		this.recordStatementCreator = recordStatementCreator;
	}
	
	//method
	@Override
	public void deleteEntriesFromMultiField(
		final String tableName,
		final String recordId,
		final String multiFieldColumn
	) {
		//TODO: Implement.
	}
	
	//method
	@Override
	public void deleteEntryFromMultiField(
		final String tableName,
		final String recordId,
		final String multiFieldColumn,
		final String entry
	) {
		//TODO: Implement.
	}
	
	//method
	@Override
	public void deleteRecordFromTable(final String tableName, final IRecordDeletionDTO recordDeletion) {
		mSQLExecutor.addSQLStatement(
			recordStatementCreator.createStatementToDeleteRecordFromTable(tableName, recordDeletion)
		);
	}
	
	//method
	@Override
	public boolean hasChanges() {
		return mSQLExecutor.getSQLStatements().containsAny();
	}
	
	//method
	@Override
	public void insertEntryIntoMultiField(
		final String tableName,
		final String recordId,
		final String multiFieldColumn,
		final String entry
	) {
		//TODO: Implement.
	}
	
	//method
	@Override
	public void insertRecordIntoTable(final String tableName, final IRecordDTO record) {
		mSQLExecutor.addSQLStatement(recordStatementCreator.createStatementToInsertRecordIntoTable(tableName, record));
	}
	
	//method
	@Override
	public void updateRecordOnTable(final String tableName, final IRecordUpdateDTO recordUpdate) {
		mSQLExecutor.addSQLStatement(recordStatementCreator.createStatementToUpdateRecordOnTable(tableName, recordUpdate));
	}
	
	//method
	@Override
	public void saveChanges() {
		mSQLExecutor.execute();
	}
}
