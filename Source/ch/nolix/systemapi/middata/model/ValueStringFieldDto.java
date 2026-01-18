/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.middata.model;

public record ValueStringFieldDto(
String columnName,
String nullableValueString,
String nullableAdditionalValue) {
}
