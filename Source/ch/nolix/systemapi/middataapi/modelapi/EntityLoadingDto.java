package ch.nolix.systemapi.middataapi.modelapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public record EntityLoadingDto(String id, String saveStamp, IContainer<ObjectValueFieldDto> contentFields) {
}
