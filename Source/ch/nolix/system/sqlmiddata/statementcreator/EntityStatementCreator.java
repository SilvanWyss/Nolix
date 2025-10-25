package ch.nolix.system.sqlmiddata.statementcreator;

import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.system.sqlmiddata.sqlmapper.SqlValueMapper;
import ch.nolix.systemapi.middata.model.EntityCreationDto;
import ch.nolix.systemapi.middata.model.EntityDeletionDto;
import ch.nolix.systemapi.middata.model.EntityUpdateDto;
import ch.nolix.systemapi.middata.model.ValueStringFieldDto;
import ch.nolix.systemapi.midschema.databasestructure.DatabaseProperty;
import ch.nolix.systemapi.sqlmiddata.sqlmapper.ISqlValueMapper;
import ch.nolix.systemapi.sqlmiddata.statementcreator.IEntityStatementCreator;
import ch.nolix.systemapi.sqlmidschema.databasestructure.DatabasePropertyColumn;
import ch.nolix.systemapi.sqlmidschema.databasestructure.EntityIndexColumn;
import ch.nolix.systemapi.sqlmidschema.databasestructure.FixTable;
import ch.nolix.systemapi.time.moment.ITime;

public final class EntityStatementCreator implements IEntityStatementCreator {
  private static final ISqlValueMapper SQL_VALUE_MAPPER = new SqlValueMapper();

  @Override
  public String createStatementToDeleteEntity(final String tableName, final EntityDeletionDto entity) {
    return //
    "DELETE FROM "
    + tableName
    + " WHERE Id = '"
    + entity.id()
    + "' AND SaveStamp = '"
    + entity.saveStamp()
    + "';"
    + "IF @@RowCount = 0 BEGIN THROW error(100000, 'The data was changed in the meanwhile.', 0) END;";
  }

  @Override
  public String createStatementToDeleteEntityIndex(final String entityId) {
    return //
    "DELETE FROM "
    + FixTable.ENTITY_INDEX.getName()
    + " WHERE "
    + EntityIndexColumn.ENTITY_ID.getName()
    + " = '"
    + entityId
    + "';";
  }

  @Override
  public String createStatementToExpectGivenSchemaTimestamp(final ITime schemaTimestamp) {
    return //
    "IF NOT EXISTS (SELECT * FROM "
    + FixTable.DATABASE_PROPERTY.getName()
    + " WHERE "
    + DatabasePropertyColumn.KEY.getName()
    + " = '"
    + DatabaseProperty.SCHEMA_TIMESTAMP.getName()
    + "' AND "
    + DatabasePropertyColumn.VALUE.getName()
    + " = '"
    + schemaTimestamp.getSpecification().getSingleChildNodeHeader()
    + "') BEGIN THROW 100000, 'The schema was changed in the meanwhile.', 0; END;";
  }

  @Override
  public String createStatementToExpectTableContainsEntity(final String tableName, final String entityId) {
    return //
    "SELECT Id FROM "
    + tableName
    + " WHERE Id = '"
    + entityId
    + "'; "
    + "IF @@RowCount = 0 BEGIN THROW error(100000, 'The database does not contain a "
    + tableName
    + " with the id "
    + entityId
    + ".', 0) END;";
  }

  @Override
  public String createStatementToInsertEntity(final String tableName, final EntityCreationDto newEntity) {
    return //
    "INSERT INTO "
    + tableName
    + " (Id, SaveStamp, "
    + newEntity.contentFields().to(ValueStringFieldDto::columnName).toStringWithSeparator(", ")
    + ") VALUES ('"
    + newEntity.id()
    + "', '"
    + 1
    + "', "
    + newEntity.contentFields().to(SQL_VALUE_MAPPER::mapStringContentFieldDtoToSqlValue).toStringWithSeparator(", ")
    + ");";
  }

  @Override
  public String createStatementToInsertEntityIndex(final String tableId, final String entityId) {
    return //
    "INSERT INTO "
    + FixTable.ENTITY_INDEX.getName()
    + "("
    + EntityIndexColumn.ENTITY_ID.getName()
    + ", "
    + EntityIndexColumn.TABLE_ID.getName()
    + ") VALUES ("
    + StringTool.getInSingleQuotes(tableId)
    + ", "
    + StringTool.getInSingleQuotes(entityId)
    + ");";
  }

  @Override
  public String createStatementToUpdateEntityOnTable(final String tableName, final EntityUpdateDto entityUpdate) {
    final var contentFieldSets = //
    entityUpdate.updatedContentFields()
      .to(f -> f.columnName() + " = " + SQL_VALUE_MAPPER.mapStringContentFieldDtoToSqlValue(f));

    var contentFieldSetsPrecessor = " ";

    if (contentFieldSets.containsAny()) {
      contentFieldSetsPrecessor = ", ";
    }

    return //
    "UPDATE "
    + tableName
    + " SET SaveStamp = '"
    + (Integer.valueOf(entityUpdate.saveStamp()) + 1)
    + "'"
    + contentFieldSetsPrecessor
    + contentFieldSets.toStringWithSeparator(", ")
    + " WHERE Id = '"
    + entityUpdate.id()
    + "' AND SaveStamp = '"
    + entityUpdate.saveStamp()
    + "';"
    + "IF @@RowCount = 0 BEGIN THROW error(100000, 'The data was changed in the meanwhile.', 0) END;";
  }
}
