package ch.nolix.systemapi.objectdata.structure;

public record CachedEntityDto<E>(String id, String nullableTableId, E nullableEntity) {
}
