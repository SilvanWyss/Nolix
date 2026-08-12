/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.midschemainfo.model;

import ch.nolix.systemapi.database.databaseproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

/**
 * @author Silvan Wyss
 * @param id
 * @param name
 * @param oneBasedOrdinalIndex
 * @param fieldType
 * @param dataType
 */
public record ColumnInfoDto(
String id,
String name,
int oneBasedOrdinalIndex,
FieldType fieldType,
DataType dataType) {
}
