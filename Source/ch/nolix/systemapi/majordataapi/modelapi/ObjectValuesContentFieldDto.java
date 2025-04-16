package ch.nolix.systemapi.majordataapi.modelapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public record ObjectValuesContentFieldDto(String columnName, IContainer<Object> optionalValues) {
}
