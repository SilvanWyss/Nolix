package ch.nolix.systemapi.rawdataapi.model;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public record EntityLoadingDto(String id, String saveStamp, IContainer<ContentFieldDto<Object>> contentFields) {
}
