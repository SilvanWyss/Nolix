package ch.nolix.systemapi.middataapi.modelapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public record EntityCreationDto(String id, IContainer<StringValueFieldDto> contentFields) {
}
