/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.middata.model;

public record MultiReferenceEntryDto(
String tableName,
String entityId,
String multiReferenceColumnId,
String referencedEntityId,
String referencedEntityTableId) {
}
