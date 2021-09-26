//package declaration
package ch.nolix.system.sqloptimisticlockingdata.sqldatalanguage;

//own imports
import ch.nolix.techapi.sqloptimisticlockingdataapi.recorddtoapi.ICellDTO;
import ch.nolix.techapi.sqloptimisticlockingdataapi.recorddtoapi.IRecordDTO;
import ch.nolix.techapi.sqloptimisticlockingdataapi.sqldatalanguageapi.IDataStatementCreator;

//class
public final class MSSQLDataStatementCreator implements IDataStatementCreator {
	
	//method
	@Override
	public String creatStatementToAddRecordToTable(final String tableName, final IRecordDTO record) {
		return
		"INSERT INTO "
		+ tableName
		+ " VALUES ("
		+ record.getId()
		+ ", "
		+ record.getSaveStamp()
		+ ","
		+ record.getValues().to(ICellDTO::getValue)
		+ ")";
	}
	
	//method
	@Override
	public String createStatementToDeleteRecordFromTable(final String tableName, final IRecordDTO record) {
		return
		"DELETE FROM "
		+ tableName
		+ " WHERE Id = '"
		+ record.getId()
		+ "' AND SaveStamp = '"
		+  record.getSaveStamp()
		+ "';"
		+ "IF @@RowCount = BEGIN THROW error(100000, 'The data were changed in the meanwhile.', 0) END;";
	}
	
	//method
	@Override
	public String createStatementToEditRecordOnTable(final String tableName, final IRecordDTO record) {
		return
		"UPDATE "
		+ tableName
		+ " SET "
		+ record.getValues().to(v -> v.getColumnHeader() + " = " + v.getValue())
		+ " WHERE Id = '"
		+ record.getId()
		+ "' AND SaveStamp = '"
		+  record.getSaveStamp()
		+ "';"
		+ "IF @@RowCount = BEGIN THROW error(100000, 'The data were changed in the meanwhile.', 0) END;";
	}
}
