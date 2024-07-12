//package declaration
package ch.nolix.system.sqlrawdata.statementcreator;

//own imports
import ch.nolix.core.commontypetool.GlobalStringTool;
import ch.nolix.system.sqlrawschema.databasepropertytable.DatabasePropertyTableColumn;
import ch.nolix.system.sqlrawschema.entityheadtable.EntityHeadTableColumn;
import ch.nolix.system.sqlrawschema.structure.IndexTableType;
import ch.nolix.system.sqlrawschema.structure.MetaDataTableType;
import ch.nolix.system.sqlrawschema.structure.TableType;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IContentFieldDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityHeadDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.INewEntityDto;
import ch.nolix.systemapi.rawschemaapi.databaseproperty.DatabaseProperty;
import ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi.IEntityStatementCreator;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
public final class EntityStatementCreator implements IEntityStatementCreator {

  //method
  @Override
  public String createStatementToDeleteEntity(
    final String tableName,
    final IEntityHeadDto entity) {
    return "DELETE FROM "
    + TableType.ENTITY_TABLE.getQualifyingPrefix() + tableName
    + " WHERE Id = '"
    + entity.getId()
    + "' AND SaveStamp = '"
    + entity.getSaveStamp()
    + "';"
    + "IF @@RowCount = 0 BEGIN THROW error(100000, 'The data was changed in the meanwhile.', 0) END;";
  }

  //method
  @Override
  public String createStatementToDeleteEntityHead(final String entityId) {
    return "DELETE FROM "
    + IndexTableType.ENTITY_HEAD.getQualifiedName()
    + " WHERE EntityId = "
    + GlobalStringTool.getInSingleQuotes(entityId)
    + ";";
  }

  //method
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

  //method
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

  //method
  @Override
  public String createStatementToInsertEntity(final String tableName, final INewEntityDto newEntity) {
    return "INSERT INTO "
    + TableType.ENTITY_TABLE.getQualifyingPrefix() + tableName
    + " (Id, SaveStamp, "
    + newEntity.getContentFields().to(IContentFieldDto::getColumnName).toStringWithSeparator(", ")
    + ") VALUES ('"
    + newEntity.getId()
    + "', '"
    + 1
    + "', "
    + newEntity.getContentFields().to(this::getSqlValueRepresentationOfContentField).toStringWithSeparator(", ")
    + ");";
  }

  //method
  @Override
  public String createStatementToInsertEntityHead(final String tableId, final String entityId) {
    return "INSERT INTO "
    + IndexTableType.ENTITY_HEAD.getQualifiedName()
    + "("
    + EntityHeadTableColumn.TABLE_ID.getName()
    + ", "
    + EntityHeadTableColumn.ENTITY_ID.getName()
    + ") VALUES ("
    + GlobalStringTool.getInSingleQuotes(tableId)
    + ", "
    + GlobalStringTool.getInSingleQuotes(entityId)
    + ");";
  }

  //method
  @Override
  public String createStatementToSetEntityAsUpdated(final String tableName, final IEntityHeadDto entity) {
    return "UPDATE"
    + TableType.ENTITY_TABLE.getQualifyingPrefix() + tableName
    + " SET SaveStamp = '"
    + (Integer.valueOf(entity.getSaveStamp()) + 1)
    + " WHERE Id = '"
    + entity.getId()
    + " AND SaveStamp = "
    + entity.getSaveStamp()
    + ";"
    + "IF @@RowCount = 0 BEGIN THROW error(100000, 'The data was changed in the meanwhile.', 0) END;";
  }

  //method
  @Override
  public String createStatementToUpdateEntityOnTable(final String tableName, final IEntityUpdateDto entityUpdate) {

    final var contentFieldSets = entityUpdate.getUpdatedContentFields()
      .to(cf -> cf.getColumnName() + " = " + getSqlValueRepresentationOfContentField(cf));

    var contentFieldSetsPrecessor = " ";
    if (contentFieldSets.containsAny()) {
      contentFieldSetsPrecessor = ", ";
    }

    return "UPDATE "
    + TableType.ENTITY_TABLE.getQualifyingPrefix() + tableName
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

  //method
  private String getSqlValueRepresentationOfContentField(final IContentFieldDto contentField) {

    final var valueAsString = contentField.getOptionalValueAsString();

    if (valueAsString.isEmpty()) {
      return "NULL";
    }

    return "'" + valueAsString.get() + "'";
  }
}
