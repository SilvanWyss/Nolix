/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.middata.model;

public record MultiBackReferenceEntryDto(
String tableName,
String entityId,
String multiBackReferenceColumnId,
String backReferencedEntityId,
String backReferencedEntityTableId) {
}
