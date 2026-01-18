/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.middata.model;

public record FieldDto(String columnName, Object nullableValue, String nullableAdditionalValue) {
}
