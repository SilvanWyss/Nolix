//package declaration
//package declaration
package ch.nolix.system.sqlintermediatedata.sqldatalanguage;

//own imports
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IRecordDTO;
import ch.nolix.techapi.intermediatedataapi.sqldatalanguageapi.IDataStatementCreator;

//class
public final class MSSQLDataStatementCreator implements IDataStatementCreator {
	
	//method
	@Override
	public String creatStatementToAddRecordToTable(final String tableName, final IRecordDTO record) {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public String createStatementToDeleteRecordFromTable(final String tableName, final IRecordDTO record) {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public final String createStatementToEditRecordOnTable(final String tableName, final IRecordDTO record) {
		// TODO Auto-generated method stub
		return null;
	}

}
