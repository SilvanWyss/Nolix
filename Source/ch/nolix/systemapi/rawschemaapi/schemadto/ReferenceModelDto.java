package ch.nolix.systemapi.rawschemaapi.schemadto;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public record ReferenceModelDto(IContainer<String> referencedTableIds) {
}
