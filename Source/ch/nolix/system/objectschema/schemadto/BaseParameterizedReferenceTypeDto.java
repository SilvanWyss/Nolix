package ch.nolix.system.objectschema.schemadto;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IAbstractReferenceModelDto;

public final class BaseParameterizedReferenceTypeDto extends ParameterizedFieldTypeDto
implements IAbstractReferenceModelDto {

  private final String referencedTableId;

  public BaseParameterizedReferenceTypeDto(
    final ContentType contentType,
    final DataType dataType,
    final String referencedTableId) {

    super(contentType, dataType);

    if (referencedTableId == null) {
      throw ArgumentIsNullException.forArgumentName("referenced table id");
    }

    this.referencedTableId = referencedTableId;
  }

  @Override
  public String getReferencedTableId() {
    return referencedTableId;
  }
}
