package ch.nolix.systemapi.rawdataapi.dto;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public record EntityLoadingDto(String id, String saveStamp, IContainer<ContentFieldDto<Object>> contentFields) {
}
