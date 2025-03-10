package ch.nolix.systemapi.rawdataapi.modelapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public record EntityUpdateDto(
String id,
String saveStamp,
IContainer<StringContentFieldDto> updatedContentFields) {
}
