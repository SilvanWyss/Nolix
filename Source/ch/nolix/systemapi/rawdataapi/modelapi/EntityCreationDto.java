package ch.nolix.systemapi.rawdataapi.modelapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public record EntityCreationDto(String id, IContainer<StringContentFieldDto> contentFields) {
}
