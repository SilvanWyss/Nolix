/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.middata.model;

public record MultiBackReferenceEntryDeletionDto(
String tableName,
String entityId,
String multiBackReferenceColumnId,
String backReferencedEntityId) {
}
