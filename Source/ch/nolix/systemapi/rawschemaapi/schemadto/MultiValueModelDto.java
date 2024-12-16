package ch.nolix.systemapi.rawschemaapi.schemadto;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;

public record MultiValueModelDto(DataType dataType) implements IContentModelDto {
}
