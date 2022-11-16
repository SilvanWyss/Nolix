//package declaration
package ch.nolix.system.sqlrawdata.mssql;

//own imports
import ch.nolix.system.sqlrawdata.sqlapi.IRecordStatementCreator;
import ch.nolix.system.sqlrawschema.databasepropertytable.DatabaseProperty;
import ch.nolix.system.sqlrawschema.databasepropertytable.DatabasePropertySystemTableColumn;
import ch.nolix.system.sqlrawschema.structure.SystemDataTable;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IContentFieldDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IRecordDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IRecordUpdateDTO;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

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
	public String createStatementToExpectGivenSchemaTimestamp(final ITime schemaTimestamp) {
		return
		"SELECT "
		+ DatabasePropertySystemTableColumn.VALUE.getLabel()
		+ " FROM "
		+ SystemDataTable.DATABASE_PROPERTY.getFullName()
		+ " WHERE "
		+ DatabasePropertySystemTableColumn.KEY.getLabel()
		+ " = '"
		+ DatabaseProperty.SCHEMA_TIMESTAMP.getLabel()
		+ "' AND "
		+ DatabasePropertySystemTableColumn.VALUE.getLabel()
		+ " = '"
		+ schemaTimestamp
		+ "' "
		+ "IF @@RowCount = 0 BEGIN THROW error(100000, 'The schema was changed in the meanwhile.', 0) END;";
	}
	
	//method
	@Override
	public String createStatementToInsertRecordIntoTable(final String tableName, final IRecordDTO pRecord) {
		return
		"INSERT INTO "
		+ tableName
		+ " (Id, "
		+ pRecord.getContentFields().to(IContentFieldDTO::getColumnName).toString(", ")
		+ ") VALUES ("
		+ pRecord.getId()
		+ ", "
		+ pRecord.getContentFields().to(this::getValueOrNullInSQLOf).toString(", ")
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
