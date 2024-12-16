package ch.nolix.systemapi.rawschemaapi.schemadto;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;

public record ValueModelDto(DataType dataType) implements IContentModelDto {
}
