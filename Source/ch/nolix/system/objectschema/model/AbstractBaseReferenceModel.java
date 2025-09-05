package ch.nolix.system.objectschema.model;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.objectschema.model.IBaseReferenceModel;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;

public abstract class AbstractBaseReferenceModel implements IBaseReferenceModel {
  private static final DataType DATA_TYPE = DataType.STRING;

  private final ImmutableList<? extends ITable> referenceableTables;

  protected AbstractBaseReferenceModel(final IContainer<? extends ITable> referenceableTables) {
    this.referenceableTables = ImmutableList.forIterable(referenceableTables);
  }

  @Override
  public final DataType getDataType() {
    return DATA_TYPE;
  }

  @Override
  public final IContainer<? extends ITable> getReferenceableTables() {
    return referenceableTables;
  }

  @Override
  public final boolean referencesBackColumn(final IColumn column) {
    return false;
  }

  @Override
  public final boolean referencesTable(final ITable table) {
    return referenceableTables.contains(table);
  }
}
