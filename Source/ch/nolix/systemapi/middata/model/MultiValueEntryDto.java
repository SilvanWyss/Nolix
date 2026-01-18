/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.middata.model;

public record MultiValueEntryDto(
String tableName,
String entityId,
String multiValueColumnId,
String value) {
}
