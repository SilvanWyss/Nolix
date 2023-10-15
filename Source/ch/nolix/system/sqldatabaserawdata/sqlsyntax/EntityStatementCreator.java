//package declaration
package ch.nolix.system.sqldatabaserawdata.sqlsyntax;

import ch.nolix.system.sqldatabaserawschema.databasepropertytable.DatabaseProperty;
import ch.nolix.system.sqldatabaserawschema.databasepropertytable.DatabasePropertySystemTableColumn;
import ch.nolix.system.sqldatabaserawschema.structure.SystemDataTable;
import ch.nolix.system.sqldatabaserawschema.structure.TableType;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IContentFieldDto;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityHeadDto;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDto;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.INewEntityDto;
import ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi.IEntityStatementCreator;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
public final class EntityStatementCreator implements IEntityStatementCreator {

  // method
  @Override
  public String createStatementToDeleteEntity(
      final String tableName,
      final IEntityHeadDto entity) {
    return "DELETE FROM "
        + TableType.ENTITY_TABLE.getNamePrefix() + tableName
        + " WHERE Id = '"
        + entity.getId()
        + "' AND SaveStamp = '"
        + entity.getSaveStamp()
        + "';"
        + "IF @@RowCount = 0 BEGIN THROW error(100000, 'The data was changed in the meanwhile.', 0) END;";
  }

  // method
  @Override
  public String createStatementToExpectGivenSchemaTimestamp(final ITime schemaTimestamp) {
    return "IF NOT EXISTS (SELECT * FROM "
        + SystemDataTable.DATABASE_PROPERTY.getQualifiedName()
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

  // method
  @Override
  public String createStatementToExpectTableContainsEntity(final String tableName, final String entityId) {
    return "SELECT Id FROM "
        + TableType.ENTITY_TABLE.getNamePrefix() + tableName
        + " WHERE Id = '"
        + entityId
        + "'; "
        + "IF @@RowCount = 0 BEGIN THROW error(100000, 'The database does not contain a "
        + tableName
        + " with the id "
        + entityId
        + ".', 0) END;";
  }

  // method
  @Override
  public String createStatementToInsertNewEntity(final String tableName, final INewEntityDto newEntity) {
    return "INSERT INTO "
        + TableType.ENTITY_TABLE.getNamePrefix() + tableName
        + " (Id, SaveStamp, "
        + newEntity.getContentFields().to(IContentFieldDto::getColumnName).toStringWithSeparator(", ")
        + ") VALUES ('"
        + newEntity.getId()
        + "', '"
        + 1
        + "', "
        + newEntity.getContentFields().to(this::getValueOrNullInSqlOf).toStringWithSeparator(", ")
        + ");";
  }

  // method
  @Override
  public String createStatementToSetEntityAsUpdated(final String tableName, final IEntityHeadDto entity) {
    return "UPDATE"
        + TableType.ENTITY_TABLE.getNamePrefix() + tableName
        + " SET SaveStamp = '"
        + (Integer.valueOf(entity.getSaveStamp()) + 1)
        + " WHERE Id = '"
        + entity.getId()
        + " AND SaveStamp = "
        + entity.getSaveStamp()
        + ";"
        + "IF @@RowCount = 0 BEGIN THROW error(100000, 'The data was changed in the meanwhile.', 0) END;";
  }

  // method
  @Override
  public String createStatementToUpdateEntityOnTable(final String tableName, final IEntityUpdateDto entityUpdate) {

    final var contentFieldSets = entityUpdate.getUpdatedContentFields()
        .to(cf -> cf.getColumnName() + " = " + getValueOrNullInSqlOf(cf));

    var contentFieldSetsPrecessor = " ";
    if (contentFieldSets.containsAny()) {
      contentFieldSetsPrecessor = ", ";
    }

    return "UPDATE "
        + TableType.ENTITY_TABLE.getNamePrefix() + tableName
        + " SET SaveStamp = '"
        + (Integer.valueOf(entityUpdate.getSaveStamp()) + 1)
        + "'"
        + contentFieldSetsPrecessor
        + contentFieldSets.toStringWithSeparator(", ")
        + " WHERE Id = '"
        + entityUpdate.getId()
        + "' AND SaveStamp = '"
        + entityUpdate.getSaveStamp()
        + "';"
        + "IF @@RowCount = 0 BEGIN THROW error(100000, 'The data was changed in the meanwhile.', 0) END;";
  }

  // method
  private String getValueOrNullInSqlOf(final IContentFieldDto contentField) {

    final var string = contentField.getValueAsStringOrNull();

    if (string == null) {
      return "NULL";
    }

    return "'" + string + "'";
  }
}
