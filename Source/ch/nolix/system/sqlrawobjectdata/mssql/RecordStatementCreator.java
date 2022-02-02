//package declaration
package ch.nolix.system.sqlrawobjectdata.mssql;

//own imports
import ch.nolix.system.sqlrawobjectdata.sqlapi.IRecordStatementCreator;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.ILoadedContentFieldDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordHeadDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//class
public final class RecordStatementCreator implements IRecordStatementCreator {
	
	//method
	@Override
	public String createStatementToDeleteRecordFromTable(
		final String tableName,
		final IRecordHeadDTO recordHead
	) {
		return
		"DELETE FROM "
		+ tableName
		+ " WHERE Id = '"
		+ recordHead.getId()
		+ "' AND SaveStamp = '"
		+ recordHead.getSaveStamp()
		+ "';"
		+ "IF @@RowCount = 0 BEGIN THROW error(100000, 'The data was changed in the meanwhile.', 0) END;";
	}
	
	//method
	@Override
	public String createStatementToInsertRecordIntoTable(final String tableName, final IRecordDTO record) {
		return
		"INSERT INTO "
		+ tableName
		+ " (Id, "
		+ record.getContentFields().to(ILoadedContentFieldDTO::getColumnName).toString(", ")
		+ ") VALUES ("
		+ record.getId()
		+ ", "
		+ record.getContentFields().to(this::getValueOrNullInSQLOf).toString(", ")
		+ ")";
	}
	
	//method
	@Override
	public String createStatementToUpdateRecordOnTable(final String tableName, final IRecordUpdateDTO recordUpdate) {
		return
		"UPDATE "
		+ tableName
		+ " SET "
		+ recordUpdate.getUpdatedContentFields().to(cf -> cf.getColumnName() + " = " + getValueOrNullInSQLOf(cf))
		+ " WHERE Id = '"
		+ recordUpdate.getId()
		+ "' AND SaveStamp = '"
		+  recordUpdate.getSaveStamp()
		+ "';"
		+ "IF @@RowCount = 0 BEGIN THROW error(100000, 'The data were changed in the meanwhile.', 0) END;";
	}
	
	//method
	private String getValueOrNullInSQLOf(final ILoadedContentFieldDTO contentField) {
		
		final var value = contentField.getValueOrNull();
		
		if (value == null) {
			return "NULL";
		}
		
		return "'" + value + "'";
	}
}
