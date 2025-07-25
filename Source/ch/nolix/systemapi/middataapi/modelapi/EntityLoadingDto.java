package ch.nolix.systemapi.middataapi.modelapi;

import ch.nolix.coreapi.container.base.IContainer;

public record EntityLoadingDto(String id, String saveStamp, IContainer<ObjectValueFieldDto> contentFields) {
}
