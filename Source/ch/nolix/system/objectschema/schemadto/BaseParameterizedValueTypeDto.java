package ch.nolix.system.objectschema.schemadto;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IAbstractValueModelDto;

public final class BaseParameterizedValueTypeDto extends ParameterizedFieldTypeDto
implements IAbstractValueModelDto {

  public BaseParameterizedValueTypeDto(final ContentType contentType, final DataType dataType) {
    super(contentType, dataType);
  }
}
