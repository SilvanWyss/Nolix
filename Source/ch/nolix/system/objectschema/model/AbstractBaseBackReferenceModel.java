package ch.nolix.system.objectschema.model;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.system.objectschema.modelvalidator.ColumnValidator;
import ch.nolix.systemapi.objectschema.model.IBaseBackReferenceModel;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.modelvalidator.IColumnValidator;

public abstract class AbstractBaseBackReferenceModel implements IBaseBackReferenceModel {
  private static final DataType DATA_TYPE = DataType.STRING;

  private static final IColumnValidator COLUMN_VALIDATOR = new ColumnValidator();

  private final ImmutableList<? extends IColumn> backReferenceableColumns;

  protected AbstractBaseBackReferenceModel(final IContainer<? extends IColumn> backReferenceableColumns) {
    backReferenceableColumns.forEach(COLUMN_VALIDATOR::assertIsAbstractReferenceColumn);

    this.backReferenceableColumns = ImmutableList.forIterable(backReferenceableColumns);
  }

  @Override
  public final IContainer<? extends IColumn> getStoredBackReferenceableColumns() {
    return backReferenceableColumns;
  }

  @Override
  public final DataType getDataType() {
    return DATA_TYPE;
  }

  @Override
  public final boolean referencesTable(final ITable table) {
    return false;
  }

  @Override
  public final boolean referencesBackColumn(final IColumn column) {
    return getStoredBackReferenceableColumns().contains(column);
  }
}
