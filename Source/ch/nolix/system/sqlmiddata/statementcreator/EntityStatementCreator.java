/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmiddata.statementcreator;

import ch.nolix.base.commontype.stringtool.StringTool;
import ch.nolix.system.sqlmiddata.sqlmapper.SqlPartsMapper;
import ch.nolix.systemapi.middata.model.EntityCreationDto;
import ch.nolix.systemapi.middata.model.EntityDeletionDto;
import ch.nolix.systemapi.middata.model.EntityUpdateDto;
import ch.nolix.systemapi.midschema.databasestructure.DatabaseProperty;
import ch.nolix.systemapi.sqlmiddata.statementcreator.IEntityStatementCreator;
import ch.nolix.systemapi.sqlmiddatabasestructure.column.DatabasePropertyColumn;
import ch.nolix.systemapi.sqlmiddatabasestructure.column.EntityIndexColumn;
import ch.nolix.systemapi.sqlmiddatabasestructure.table.DataTable;
import ch.nolix.systemapi.sqlmiddatabasestructure.table.MetaDataTable;
import ch.nolix.systemapi.time.main.ITime;

/**
 * @author Silvan Wyss
 */
public final class EntityStatementCreator implements IEntityStatementCreator {
  private static final SqlPartsMapper SQL_PARTS_MAPPER = new SqlPartsMapper();

  private static final SqlValueAssignmentMapper SQL_VALUE_ASSIGNMENT_MAPPER = new SqlValueAssignmentMapper();

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

  /**
   * {@inheritDoc}
   */
  @Override
  public String createStatementToDeleteEntityIndex(final String entityId) {
    return //
    "DELETE FROM "
    + DataTable.ENTITY_INDEX
    + " WHERE "
    + EntityIndexColumn.ENTITY_ID
    + " = '"
    + entityId
    + "';";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createStatementToExpectGivenSchemaTimestamp(final ITime schemaTimestamp) {
    return //
    "IF NOT EXISTS (SELECT * FROM "
    + MetaDataTable.DATABASE_PROPERTY
    + " WHERE "
    + DatabasePropertyColumn.KEY
    + " = '"
    + DatabaseProperty.SCHEMA_TIMESTAMP
    + "' AND "
    + DatabasePropertyColumn.VALUE
    + " = '"
    + schemaTimestamp.getSpecification().getSingleChildNodeHeader()
    + "') BEGIN THROW 100000, 'The schema was changed in the meanwhile.', 0; END;";
  }

  /**
   * {@inheritDoc}
   */
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

  /**
   * {@inheritDoc}
   */
  @Override
  public String createStatementToInsertEntity(final String tableName, final EntityCreationDto newEntity) {
    final var contentFields = newEntity.contentFields();
    final var contentColumnNames = contentFields.toMultiples(SQL_PARTS_MAPPER::mapValueStringFieldDtoToColumnNames);
    final var values = contentFields.toMultiples(SQL_PARTS_MAPPER::mapValueStringFieldDtoToSqlValueLiterals);

    return //
    "INSERT INTO " + tableName
    + " (Id, SaveStamp, "
    + contentColumnNames.toStringWithDelimiter(", ")
    + ") VALUES ('"
    + newEntity.id() + "', '" + 1 + "', "
    + values.toStringWithDelimiter(", ")
    + ");";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createStatementToInsertEntityIndex(final String tableId, final String entityId) {
    return //
    "INSERT INTO "
    + DataTable.ENTITY_INDEX
    + "("
    + EntityIndexColumn.ENTITY_ID
    + ", "
    + EntityIndexColumn.TABLE_ID
    + ") VALUES ("
    + StringTool.getInSingleQuotes(tableId)
    + ", "
    + StringTool.getInSingleQuotes(entityId)
    + ");";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createStatementToUpdateEntityOnTable(final String tableName, final EntityUpdateDto entityUpdate) {
    final var updatedContentFields = entityUpdate.updatedContentFields();

    final var updatedContentFieldSqlValueAssignments = //
    updatedContentFields.toMultiples(SQL_VALUE_ASSIGNMENT_MAPPER::mapValueStringFieldDtoToSqlValueAssignemnts);

    var contentFieldSetsPrecessor = " ";

    if (updatedContentFieldSqlValueAssignments.containsAny()) {
      contentFieldSetsPrecessor = ", ";
    }

    return //
    "UPDATE "
    + tableName
    + " SET SaveStamp = '"
    + (Integer.valueOf(entityUpdate.saveStamp()) + 1)
    + "'"
    + contentFieldSetsPrecessor
    + updatedContentFieldSqlValueAssignments.toStringWithDelimiter(", ")
    + " WHERE Id = '"
    + entityUpdate.id()
    + "' AND SaveStamp = '"
    + entityUpdate.saveStamp()
    + "';"
    + "IF @@RowCount = 0 BEGIN THROW error(100000, 'The data was changed in the meanwhile.', 0) END;";
  }
}
