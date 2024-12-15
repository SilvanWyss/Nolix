package ch.nolix.system.objectschema.contentmodel;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IContentModel;

public abstract class AbstractContentModel implements IContentModel {

  private final DataType dataType;

  protected AbstractContentModel(final DataType dataTye) {

    GlobalValidator.assertThat(dataTye).thatIsNamed(DataType.class).isNotNull();

    this.dataType = dataTye;
  }

  @Override
  public final DataType getDataType() {
    return dataType;
  }
}
