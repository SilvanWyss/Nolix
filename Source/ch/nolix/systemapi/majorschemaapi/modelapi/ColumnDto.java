package ch.nolix.systemapi.majorschemaapi.modelapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.midschemaapi.modelapi.IContentModelDto;

public record ColumnDto(String id, String name, IContainer<IContentModelDto> contentModels) {
}
