package ch.nolix.systemapi.middataapi.modelapi;

import ch.nolix.coreapi.container.base.IContainer;

public record EntityUpdateDto(
String id,
String saveStamp,
IContainer<StringValueFieldDto> updatedContentFields) {
}
