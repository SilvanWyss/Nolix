package ch.nolix.systemapi.middataapi.modelapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public record EntityUpdateDto(
String id,
String saveStamp,
IContainer<StringContentFieldDto> updatedContentFields) {
}
