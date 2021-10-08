//package declaration
package ch.nolix.system.sqloptimisticlockingdata.dataadapter;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.common.sql.SQLExecutor;
import ch.nolix.techapi.sqloptimisticlockingdataapi.dataadapterapi.IDataWriter;
import ch.nolix.techapi.sqloptimisticlockingdataapi.recorddtoapi.IRecordDTO;
import ch.nolix.techapi.sqloptimisticlockingdataapi.sqldatalanguageapi.IDataStatementCreator;

//class
final class DataWriter implements IDataWriter {
	
	//attributes
	private final SQLExecutor mSQLExecutor;
	private final IDataStatementCreator dataStatementCreator;
	
	//constructor
	public DataWriter(final SQLConnection pSQLConnection, final IDataStatementCreator dataStatementCreator) {
		
		Validator.assertThat(pSQLConnection).thatIsNamed(SQLConnection.class).isNotNull();
		Validator.assertThat(dataStatementCreator).thatIsNamed(IDataStatementCreator.class).isNotNull();
		
		mSQLExecutor = new SQLExecutor(pSQLConnection);
		this.dataStatementCreator = dataStatementCreator;
	}
	
	//method
	@Override
	public void addRecordToTable(final String tableName, final IRecordDTO record) {
		mSQLExecutor.addSQLStatement(dataStatementCreator.creatStatementToAddRecordToTable(tableName, record));
	}
	
	//method
	@Override
	public void deleteRecordFromTable(final String tableName, final IRecordDTO record) {
		mSQLExecutor.addSQLStatement(dataStatementCreator.createStatementToDeleteRecordFromTable(tableName, record));
	}
	
	//method
	@Override
	public void editRecordOnTable(final String tableName, final IRecordDTO record) {
		mSQLExecutor.addSQLStatement(dataStatementCreator.createStatementToEditRecordOnTable(tableName, record));
	}
	
	//method
	@Override
	public boolean hasChanges() {
		return mSQLExecutor.getSQLStatements().containsAny();
	}
	
	//method
	@Override
	public void saveChanges() {
		mSQLExecutor.execute();
	}
}
