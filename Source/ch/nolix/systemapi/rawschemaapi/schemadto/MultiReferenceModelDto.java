package ch.nolix.systemapi.rawschemaapi.schemadto;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public record MultiReferenceModelDto(IContainer<String> referencedTableIds) implements IContentModelDto {
}
