package ch.nolix.system.objectschema.modelexaminer;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.BaseFieldType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.modelexaminer.IColumnExaminer;

/**
 * @author Silvan Wyss
 */
public final class ColumnExaminer implements IColumnExaminer {
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canSetContentModel(
    final IColumn column,
    final FieldType fieldType,
    final DataType dataType,
    final IContainer<? extends ITable> referenceableTables,
    final IContainer<? extends IColumn> backReferenceableColumns) {
    return //
    column != null
    && column.isEmpty()
    && fieldType != null
    && dataType != null
    && referenceableTables != null
    && backReferenceableColumns != null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isBaseReferenceColumn(final IColumn column) {
    return //
    column != null
    && column.getFieldType().getBaseType() == BaseFieldType.BASE_REFERENCE;
  }
}
