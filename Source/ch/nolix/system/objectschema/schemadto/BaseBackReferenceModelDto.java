package ch.nolix.system.objectschema.schemadto;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.BaseContentType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IAbstractBackReferenceModelDto;

public final class BaseBackReferenceModelDto extends AbstractContentModelDto
implements IAbstractBackReferenceModelDto {

  private final String backReferencedColumnId;

  //For a better performance, this implementation does not use all comfortable methods.
  public BaseBackReferenceModelDto(
    final ContentType contentType,
    final DataType dataType,
    final String backReferencedColumnId) {

    super(contentType, dataType);

    if (contentType.getBaseType() != BaseContentType.BASE_BACK_REFERENCE) {
      throw InvalidArgumentException.forArgument(contentType);
    }

    if (backReferencedColumnId == null) {
      throw ArgumentIsNullException.forArgumentName("back referenced column id");
    }

    this.backReferencedColumnId = backReferencedColumnId;
  }

  @Override
  public String getBackReferencedColumnId() {
    return backReferencedColumnId;
  }
}
