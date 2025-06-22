package ch.nolix.system.objectschema.modelexaminer;

import ch.nolix.systemapi.midschemaapi.fieldproperty.BaseContentType;
import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.modelexaminerapi.IColumnExaminer;

/**
 * @author Silvan Wyss
 * @version 2024-12-30
 */
public final class ColumnExaminer implements IColumnExaminer {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isAbstractReferenceColumn(IColumn column) {
    return //
    column != null
    && column.getContentModel().getContentType().getBaseType() == BaseContentType.BASE_REFERENCE;
  }
}
