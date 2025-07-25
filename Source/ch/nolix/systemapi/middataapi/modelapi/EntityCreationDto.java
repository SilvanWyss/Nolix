package ch.nolix.systemapi.middataapi.modelapi;

import ch.nolix.coreapi.container.base.IContainer;

public record EntityCreationDto(String id, IContainer<StringValueFieldDto> contentFields) {
}
