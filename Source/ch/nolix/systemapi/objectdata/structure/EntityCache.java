package ch.nolix.systemapi.objectdata.structure;

public record EntityCache<E>(String id, String nullableTableId, E nullableEntity) {
}
