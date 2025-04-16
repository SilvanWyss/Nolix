package ch.nolix.systemapi.majordataapi.modelapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public record EntityUpdateDto(String id, String saveStamp, IContainer<StringValuesContentFieldDto> contentFields) {
}
