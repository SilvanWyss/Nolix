//package declaration
package ch.nolix.system.sqlintermediatedata.mssql;

//own imports
import ch.nolix.system.sqlintermediatedata.sqlapi.IStatementCreator;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IContentFieldDTO;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IRecordDTO;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IRecordDeletionDTO;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.IRecordUpdateDTO;

//class
public final class StatementCreator implements IStatementCreator {
	
	//method
	@Override
	public String createStatementToDeleteRecordFromTable(
		final String tableName,
		final IRecordDeletionDTO recordDeletion
	) {
		return
		"DELETE FROM "
		+ tableName
		+ " WHERE Id = '"
		+ recordDeletion.getId()
		+ "' AND SaveStamp = '"
		+ recordDeletion.getSaveStamp()
		+ "';"
		+ "IF @@RowCount = BEGIN THROW error(100000, 'The data was changed in the meanwhile.', 0) END;";
	}
	
	//method
	@Override
	public String createStatementToInsertRecordIntoTable(final String tableName, final IRecordDTO record) {
		return
		"INSERT INTO "
		+ tableName
		+ " (Id, "
		+ record.getContentFields().to(IContentFieldDTO::getColumnHeader).toString(", ")
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
		+ recordUpdate.getUpdatedContentFields().to(cf -> cf.getColumnHeader() + " = " + getValueOrNullInSQLOf(cf))
		+ " WHERE Id = '"
		+ recordUpdate.getId()
		+ "' AND SaveStamp = '"
		+  recordUpdate.getSaveStamp()
		+ "';"
		+ "IF @@RowCount = BEGIN THROW error(100000, 'The data were changed in the meanwhile.', 0) END;";
	}
	
	//method
	private String getValueOrNullInSQLOf(final IContentFieldDTO contentField) {
		
		final var value = contentField.getValueOrNull();
		
		if (value == null) {
			return "NULL";
		}
		
		return "'" + value + "'";
	}
}
