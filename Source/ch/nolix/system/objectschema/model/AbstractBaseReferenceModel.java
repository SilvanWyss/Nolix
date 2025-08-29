package ch.nolix.system.objectschema.model;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.objectschema.model.IBaseReferenceModel;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;

public abstract class AbstractBaseReferenceModel implements IBaseReferenceModel {

  private static final DataType DATA_TYPE = DataType.STRING;

  private final ITable referencedTable;

  protected AbstractBaseReferenceModel(final ITable referencedTable) {

    Validator.assertThat(referencedTable).thatIsNamed("referenced table").isNotNull();

    this.referencedTable = referencedTable;
  }

  @Override
  public final DataType getDataType() {
    return DATA_TYPE;
  }

  @Override
  public final ITable getReferencedTable() {
    return referencedTable;
  }

  @Override
  public final boolean referencesTable(final ITable table) {
    return (getReferencedTable() == table);
  }

  @Override
  public final boolean referencesBackColumn(final IColumn column) {
    return false;
  }
}
