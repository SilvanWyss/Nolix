package ch.nolix.systemapi.rawdataapi.datadto;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public record EntityLoadingDto(String id, String saveStamp, IContainer<ContentFieldDto> contentFields) {
}
