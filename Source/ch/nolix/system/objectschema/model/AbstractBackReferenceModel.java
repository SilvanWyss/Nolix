package ch.nolix.system.objectschema.model;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.system.objectschema.modelvalidator.ColumnValidator;
import ch.nolix.systemapi.objectschemaapi.modelapi.IAbstractBackReferenceModel;
import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;
import ch.nolix.systemapi.objectschemaapi.modelvalidatorapi.IColumnValidator;

public abstract class AbstractBackReferenceModel implements IAbstractBackReferenceModel {

  private static final DataType DATA_TYPE = DataType.STRING;

  private static final IColumnValidator COLUMN_VALIDATOR = new ColumnValidator();

  private final IColumn backReferencedColumn;

  protected AbstractBackReferenceModel(final IColumn backReferencedColumn) {

    COLUMN_VALIDATOR.assertIsAbstractReferenceColumn(backReferencedColumn);

    this.backReferencedColumn = backReferencedColumn;
  }

  @Override
  public final IColumn getBackReferencedColumn() {
    return backReferencedColumn;
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
    return (getBackReferencedColumn() == column);
  }
}