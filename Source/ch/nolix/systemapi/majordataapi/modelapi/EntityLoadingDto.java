package ch.nolix.systemapi.majordataapi.modelapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public record EntityLoadingDto(String id, String saveStamp, IContainer<ObjectValuesContentFieldDto> contentFields) {
}
