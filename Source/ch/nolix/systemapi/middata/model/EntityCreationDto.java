package ch.nolix.systemapi.middata.model;

import ch.nolix.coreapi.container.base.IContainer;

public record EntityCreationDto(String id, IContainer<StringRepresentedFieldDto> contentFields) {
}
