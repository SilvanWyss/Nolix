package ch.nolix.systemapi.middata.model;

import ch.nolix.coreapi.container.base.IContainer;

public record EntityLoadingDto(String id, String saveStamp, IContainer<FieldDto> contentFields) {
}
