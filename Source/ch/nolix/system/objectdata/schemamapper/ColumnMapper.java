package ch.nolix.system.objectdata.schemamapper;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.system.objectdata.model.AbstractBaseReference;
import ch.nolix.system.objectdata.model.AbstractBaseValueField;
import ch.nolix.system.objectschema.model.Column;
import ch.nolix.systemapi.objectdata.model.IField;
import ch.nolix.systemapi.objectdata.schemamapper.IColumnMapper;
import ch.nolix.systemapi.objectdata.schemamapper.IContentModelMapper;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;

public final class ColumnMapper implements IColumnMapper {
  private static final IContentModelMapper CONTENT_MODEL_MAPPER = new ContentModelMapper();

  @Override
  public IColumn mapFieldToColumn(final IField field, final IContainer<ITable> tables) {
    if (field instanceof final AbstractBaseValueField<?> baseValueField) {
      return //
      new Column(
        field.getName(),
        field.getType(),
        DataType.forType(baseValueField.getValueType()),
        ImmutableList.createEmpty(),
        CONTENT_MODEL_MAPPER.mapFieldToContentModel(field, tables));
    }

    if (field instanceof final AbstractBaseReference<?> baseReference) {

      final var referenceableTableNames = baseReference.getReferenceableTableNames();
      final var referenceableTables = tables.getStoredSelected(t -> referenceableTableNames.containsAny(t.getName()));

      return //
      new Column(
        field.getName(),
        field.getType(),
        DataType.STRING,
        referenceableTables,
        CONTENT_MODEL_MAPPER.mapFieldToContentModel(field, tables));
    }

    return //
    new Column(
      field.getName(),
      field.getType(),
      DataType.STRING,
      ImmutableList.createEmpty(),
      CONTENT_MODEL_MAPPER.mapFieldToContentModel(field, tables));
  }
}
