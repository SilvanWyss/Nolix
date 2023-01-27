//package declaration
package ch.nolix.system.sqlrawdata.mssql;

//own imports
import ch.nolix.system.sqlrawdata.sqlapi.IEntityStatementCreator;
import ch.nolix.system.sqlrawschema.databasepropertytable.DatabaseProperty;
import ch.nolix.system.sqlrawschema.databasepropertytable.DatabasePropertySystemTableColumn;
import ch.nolix.system.sqlrawschema.structure.SystemDataTable;
import ch.nolix.system.sqlrawschema.structure.TableType;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IContentFieldDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.INewEntityDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDTO;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
public final class EntityStatementCreator implements IEntityStatementCreator {
	
	//method
	@Override
	public String createStatementToDeleteEntity(
		final String tableName,
		final IEntityHeadDTO entity
	) {
		return
		"DELETE FROM "
		+ TableType.ENTITY_TABLE.getNamePrefix() + tableName
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
		"IF NOT EXISTS (SELECT * FROM "
		+ SystemDataTable.DATABASE_PROPERTY.getFullName()
		+ " WHERE "
		+ DatabasePropertySystemTableColumn.KEY.getLabel()
		+ " = '"
		+ DatabaseProperty.SCHEMA_TIMESTAMP.getLabel()
		+ "' AND "
		+ DatabasePropertySystemTableColumn.VALUE.getLabel()
		+ " = '"
		+ schemaTimestamp.getSpecification().getSingleChildNodeHeader()
		+ "') BEGIN THROW 100000, 'The schema was changed in the meanwhile.', 0; END;";
	}
	
	//method
	@Override
	public String createStatementToExpectTableContainsEntity(final String tableName, final String entityId) {
		return
		"SELECT Id FROM "
		+ TableType.ENTITY_TABLE.getNamePrefix() + tableName
		+ " WHERE Id = '"
		+ entityId
		+ "' "
		+ "IF @@RowCount = 0 BEGIN THROW error(100000, 'The database does not contain a "
		+ tableName
		+ " with the id "
		+ entityId
		+ ".', 0) END;";
	}
	
	//method
	@Override
	public String createStatementToInsertNewEntity(final String tableName, final INewEntityDTO newEntity) {
		return
		"INSERT INTO "
		+ TableType.ENTITY_TABLE.getNamePrefix() + tableName
		+ " (Id, SaveStamp, "
		+ newEntity.getContentFields().to(IContentFieldDTO::getColumnName).toString(", ")
		+ ") VALUES ('"
		+ newEntity.getId()
		+ "', '"
		+ 1
		+ "', "
		+ newEntity.getContentFields().to(this::getValueOrNullInSQLOf).toString(", ")
		+ ")";
	}
	
	//method
	@Override
	public String createStatementToSetEntityAsUpdated(final String tableName, final IEntityHeadDTO entity) {
		return
		"UPDATE" 
		+ TableType.ENTITY_TABLE.getNamePrefix() + tableName
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
	public String createStatementToUpdateEntityOnTable(final String tableName, final IEntityUpdateDTO entityUpdate) {
		
		final var contentFieldSets =
		entityUpdate.getUpdatedContentFields().to(cf -> cf.getColumnName() + " = " + getValueOrNullInSQLOf(cf));
		
		var contentFieldSetsPrecessor = " ";
		if (contentFieldSets.containsAny()) {
			contentFieldSetsPrecessor = ", ";
		}
		
		return
		"UPDATE "
		+ TableType.ENTITY_TABLE.getNamePrefix() + tableName
		+ " SET SaveStamp = '"
		+ (entityUpdate.getSaveStamp() + 1)
		+ "'"
		+ contentFieldSetsPrecessor
		+ contentFieldSets
		+ " WHERE Id = '"
		+ entityUpdate.getId()
		+ "' AND SaveStamp = '"
		+  entityUpdate.getSaveStamp()
		+ ";"
		+ "IF @@RowCount = 0 BEGIN THROW error(100000, 'The data was changed in the meanwhile.', 0) END;";
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
