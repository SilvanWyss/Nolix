//package declaration
package ch.nolix.system.sqlintermediatedata.datawriter;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.common.sql.SQLExecutor;
import ch.nolix.system.sqlintermediatedata.sqlapi.IStatementCreator;
import ch.nolix.techapi.intermediatedataapi.dataadapterapi.IDataWriter;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IRecordDTO;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IRecordDeletionDTO;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IRecordUpdateDTO;

//class
public final class DataWriter implements IDataWriter {
	
	//attributes
	private final SQLExecutor mSQLExecutor;
	private final IStatementCreator statementCreator;
	
	//constructor
	public DataWriter(final SQLConnection pSQLConnection, final IStatementCreator statementCreator) {
		
		Validator.assertThat(statementCreator).thatIsNamed(IStatementCreator.class).isNotNull();
		
		mSQLExecutor = new SQLExecutor(pSQLConnection);
		this.statementCreator = statementCreator;
	}
	
	//method
	@Override
	public void deleteRecordFromTable(final String tableName, final IRecordDeletionDTO recordDeletion) {
		mSQLExecutor.addSQLStatement(
			statementCreator.createStatementToDeleteRecordFromTable(tableName, recordDeletion)
		);
	}
	
	//method
	@Override
	public boolean hasChanges() {
		return mSQLExecutor.getSQLStatements().containsAny();
	}
	
	//method
	@Override
	public void insertRecordIntoTable(final String tableName, final IRecordDTO record) {
		mSQLExecutor.addSQLStatement(statementCreator.createStatementToInsertRecordIntoTable(tableName, record));
	}
	
	//method
	@Override
	public void updateRecordOnTable(final String tableName, final IRecordUpdateDTO recordUpdate) {
		mSQLExecutor.addSQLStatement(statementCreator.createStatementToUpdateRecordOnTable(tableName, recordUpdate));
	}
	
	//method
	@Override
	public void saveChanges() {
		mSQLExecutor.execute();
	}
}
