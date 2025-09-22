package ch.nolix.systemapi.objectdata.structure;

public record EntityCache<E>(String entityId, String nullableTableId, E nullableEntity) {
}
