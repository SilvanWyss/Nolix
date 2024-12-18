package ch.nolix.systemapi.rawdataapi.datadto;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public record EntityCreationDto(String id, IContainer<ContentFieldDto> contentFields) {
}
