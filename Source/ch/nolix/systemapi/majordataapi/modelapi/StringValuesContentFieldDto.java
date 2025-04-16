package ch.nolix.systemapi.majordataapi.modelapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public record StringValuesContentFieldDto(String columnName, IContainer<String> optionalValues) {
}
