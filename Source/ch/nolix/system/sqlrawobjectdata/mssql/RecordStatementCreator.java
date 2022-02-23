//package declaration
package ch.nolix.system.sqlrawobjectdata.mssql;

//own imports
import ch.nolix.system.sqlrawobjectdata.sqlapi.IRecordStatementCreator;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IContentFieldDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//class
public final class RecordStatementCreator implements IRecordStatementCreator {
	
	//method
	@Override
	public String createStatementToDeleteRecordFromTable(
		final String tableName,
		final IEntityHeadDTO entity
	) {
		return
		"DELETE FROM "
		+ tableName
		+ " WHERE Id = '"
		+ entity.getId()
		+ "' AND SaveStamp = '"
		+ entity.getSaveStamp()
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
		+ record.getContentFields().to(IContentFieldDTO::getColumnName).toString(", ")
		+ ") VALUES ("
		+ record.getId()
		+ ", "
		+ record.getContentFields().to(this::getValueOrNullInSQLOf).toString(", ")
		+ ")";
	}
	
	//method
	@Override
	public String createStatementToSetEntityAsUpdated(final String tableName, final IEntityHeadDTO entity) {
		return
		"UPDATE" 
		+ tableName
		+ " SET SaveStamp = '"
		+ (entity.getSaveStamp() + 1)
		+ " WHERE Id = '"
		+ entity.getId()
		+ " AND SaveStamp = "
		+ entity.getSaveStamp()
		+ ";"
		+ "IF @@RowCount = 0 BEGIN THROW error(100000, 'The data was changed in the meanwhile.', 0) END;";
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
		+  recordUpdate.getSaveStamp();
	}
	
	//method
	private String getValueOrNullInSQLOf(final IContentFieldDTO contentField) {
		
		final var string = contentField.getValueAsStringOrNull();
		
		if (string == null) {
			return "NULL";
		}
		
		return "'" + string + "'";
	}
}
