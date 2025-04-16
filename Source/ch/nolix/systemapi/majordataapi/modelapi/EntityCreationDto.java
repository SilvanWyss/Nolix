package ch.nolix.systemapi.majordataapi.modelapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public record EntityCreationDto(String id, IContainer<StringValuesContentFieldDto> contentFields) {
}
