package ch.nolix.systemapi.rawdataapi.model;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public record EntityCreationDto(String id, IContainer<StringContentFieldDto> contentFields) {
}
