package ch.nolix.systemapi.rawdataapi.datadto;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public record EntityUpdateDto(String id, String saveStamp, IContainer<ContentFieldDto> updatedContentFields) {
}
