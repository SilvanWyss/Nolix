package ch.nolix.system.sqlrawdata.statementcreator;

import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.coreapi.commontypetoolapi.stringtoolapi.IStringTool;
import ch.nolix.system.sqlrawschema.databasepropertytable.DatabasePropertyTableColumn;
import ch.nolix.system.sqlrawschema.entityheadtable.EntityHeadTableColumn;
import ch.nolix.system.sqlrawschema.structure.IndexTableType;
import ch.nolix.system.sqlrawschema.structure.MetaDataTableType;
import ch.nolix.system.sqlrawschema.structure.TableType;
import ch.nolix.systemapi.rawdataapi.datadto.StringContentFieldDto;
import ch.nolix.systemapi.rawdataapi.datadto.EntityCreationDto;
import ch.nolix.systemapi.rawdataapi.datadto.EntityDeletionDto;
import ch.nolix.systemapi.rawdataapi.datadto.EntityUpdateDto;
import ch.nolix.systemapi.rawschemaapi.databaseproperty.DatabaseProperty;
import ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi.IEntityStatementCreator;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class EntityStatementCreator implements IEntityStatementCreator {

  private static final IStringTool STRING_TOOL = new StringTool();

  @Override
  public String createStatementToDeleteEntity(
    final String tableName,
    final EntityDeletionDto entity) {
    return "DELETE FROM "
    + TableType.ENTITY_TABLE.getQualifyingPrefix() + tableName
    + " WHERE Id = '"
    + entity.id()
    + "' AND SaveStamp = '"
    + entity.saveStamp()
    + "';"
    + "IF @@RowCount = 0 BEGIN THROW error(100000, 'The data was changed in the meanwhile.', 0) END;";
  }

  @Override
  public String createStatementToDeleteEntityHead(final String entityId) {
    return "DELETE FROM "
    + IndexTableType.ENTITY_HEAD.getQualifiedName()
    + " WHERE EntityId = "
    + STRING_TOOL.getInSingleQuotes(entityId)
    + ";";
  }

  @Override
  public String createStatementToExpectGivenSchemaTimestamp(final ITime schemaTimestamp) {
    return "IF NOT EXISTS (SELECT * FROM "
    + MetaDataTableType.DATABASE_PROPERTY.getQualifiedName()
    + " WHERE "
    + DatabasePropertyTableColumn.KEY.getLabel()
    + " = '"
    + DatabaseProperty.SCHEMA_TIMESTAMP.getLabel()
    + "' AND "
    + DatabasePropertyTableColumn.VALUE.getLabel()
    + " = '"
    + schemaTimestamp.getSpecification().getSingleChildNodeHeader()
    + "') BEGIN THROW 100000, 'The schema was changed in the meanwhile.', 0; END;";
  }

  @Override
  public String createStatementToExpectTableContainsEntity(final String tableName, final String entityId) {
    return "SELECT Id FROM "
    + TableType.ENTITY_TABLE.getQualifyingPrefix() + tableName
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
    return "INSERT INTO "
    + TableType.ENTITY_TABLE.getQualifyingPrefix() + tableName
    + " (Id, SaveStamp, "
    + newEntity.contentFields().to(StringContentFieldDto::columnName).toStringWithSeparator(", ")
    + ") VALUES ('"
    + newEntity.id()
    + "', '"
    + 1
    + "', "
    + newEntity.contentFields().to(this::getSqlValueRepresentationOfContentField).toStringWithSeparator(", ")
    + ");";
  }

  @Override
  public String createStatementToInsertEntityHead(final String tableId, final String entityId) {
    return "INSERT INTO "
    + IndexTableType.ENTITY_HEAD.getQualifiedName()
    + "("
    + EntityHeadTableColumn.TABLE_ID.getName()
    + ", "
    + EntityHeadTableColumn.ENTITY_ID.getName()
    + ") VALUES ("
    + STRING_TOOL.getInSingleQuotes(tableId)
    + ", "
    + STRING_TOOL.getInSingleQuotes(entityId)
    + ");";
  }

  @Override
  public String createStatementToUpdateEntityOnTable(final String tableName, final EntityUpdateDto entityUpdate) {

    final var contentFieldSets = entityUpdate.updatedContentFields()
      .to(f -> f.columnName() + " = " + getSqlValueRepresentationOfContentField(f));

    var contentFieldSetsPrecessor = " ";
    if (contentFieldSets.containsAny()) {
      contentFieldSetsPrecessor = ", ";
    }

    return "UPDATE "
    + TableType.ENTITY_TABLE.getQualifyingPrefix() + tableName
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

  private String getSqlValueRepresentationOfContentField(final StringContentFieldDto contentField) {

    final var valueAsString = contentField.optionalContent();

    if (valueAsString.isEmpty()) {
      return "NULL";
    }

    return "'" + valueAsString.get() + "'";
  }
}
