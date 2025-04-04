package ch.nolix.system.objectschema.model;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.midschemamodelmapper.ContentModelDtoMapper;
import ch.nolix.system.objectschema.modelmutationvalidator.ColumnMutationValidator;
import ch.nolix.systemapi.objectschemaapi.midschemamodelmapperapi.IContentModelDtoMapper;
import ch.nolix.systemapi.objectschemaapi.modelapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.modeleditorapi.IColumnEditor;

/**
 * @author Silvan Wyss
 * @version 2021-07-11
 */
public final class ColumnEditor implements IColumnEditor<Column> {

  private static final ColumnMutationValidator COLUMN_MUTATION_VALIDATOR = new ColumnMutationValidator();

  private static final IContentModelDtoMapper CONTENT_MODEL_DTO_MAPPER = new ContentModelDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteColumn(final Column column) {

    COLUMN_MUTATION_VALIDATOR.assertCanBeDeleted(column);

    if (column.belongsToTable()) {
      column.getStoredParentTable().removeColumnAttribute(column);
    }

    column.getStoredMidSchemaAdapter().deleteColumn(column.getStoredParentTable().getName(), column.getName());

    column.internalSetDeleted();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setContentModelsToColumn(final Column column, final IContainer<IContentModel> contentModels) {

    //TODO: Adjust
    final var contentModel = contentModels.getStoredFirst();

    COLUMN_MUTATION_VALIDATOR.assertCanSetContentModel(column, contentModel);

    column.setContentModelsAttribute(contentModels);

    if (column.isConnectedWithRealDatabase()) {

      final var table = column.getStoredParentTable();
      final var tableName = table.getName();
      final var columnName = column.getName();
      final var contentModelDto = CONTENT_MODEL_DTO_MAPPER.mapContentModelToContentModelDto(contentModel);

      column.getStoredMidSchemaAdapter().setContentModel(tableName, columnName, contentModelDto);
    }

    column.internalSetEdited();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setNameToColumn(final Column column, final String name) {

    COLUMN_MUTATION_VALIDATOR.assertCanSetName(column, name);

    final var oldName = column.getName();
    final var backReferencingColumns = column.getStoredBackReferencingColumns();

    column.setNameAttribute(name);

    if (column.isConnectedWithRealDatabase()) {

      final var tableName = column.getStoredParentTable().getName();

      column.getStoredMidSchemaAdapter().renameColumn(tableName, oldName, name);
    }

    for (final var brc : backReferencingColumns) {
      ((Column) brc).internalSetContentModelToDatabase();
    }

    column.internalSetEdited();
  }
}
