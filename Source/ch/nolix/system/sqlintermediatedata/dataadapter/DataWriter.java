//package declaration
package ch.nolix.system.sqlintermediatedata.dataadapter;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.common.sql.SQLExecutor;
import ch.nolix.techapi.intermediatedataapi.dataadapterapi.IDataWriter;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IRecordDTO;
import ch.nolix.techapi.intermediatedataapi.sqldatalanguageapi.IDataStatementCreator;

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
		return mSQLExecutor.getStatements().containsAny();
	}
	
	//method
	@Override
	public void saveChanges() {
		mSQLExecutor.execute();
	}
}
