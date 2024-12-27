package ch.nolix.systemapi.rawdataapi.dto;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public record EntityCreationDto(String id, IContainer<StringContentFieldDto> contentFields) {
}
