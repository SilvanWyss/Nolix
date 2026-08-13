/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.objectdata.model;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.model.AbstractField;
import ch.nolix.system.objectdata.model.Column;
import ch.nolix.system.objectdata.model.FieldMapper;
import ch.nolix.system.objectdata.model.Table;
import ch.nolix.systemapi.database.databaseobject.DatabaseObjectState;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

/**
 * @author Silvan Wyss
 */
final class FieldMapperTest extends StandardTest {
  @Test
  void testCase_mapColumnToField_whenGivenColumnIsForValueFields() {
    // setup step 1: create tableMock.
    final var tableMock = Mockito.mock(Table.class);

    // setup step 1: create column.
    @SuppressWarnings("unchecked")
    final var column = //
    Column.withParentTableAndIdAndNameAndFieldTypeAndDataTypeClassAndReferenceableTablesAndBackReferenceableColumns(
      tableMock,
      "id",
      "name",
      FieldType.VALUE_FIELD,
      Integer.class,
      ImmutableList.createEmpty(),
      ImmutableList.createEmpty());

    // execute
    final var result = FieldMapper.mapColumnToField(column);

    // verify part 1: Verifies parents.
    verifyParentsOfCreatedField(result, column);

    // verify part 2: Verifies attributes.
    expect(result.getName()).isEqualTo("name");
    expect(result.getType()).is(FieldType.VALUE_FIELD);

    // verify part 3: Verifies states.
    verifyStateOfCreatedField(result);
  }

  @Test
  void testCase_mapColumnToField_whenGivenColumnIsForOptionalValueFields() {
    // setup step 1: create tableMock.
    final var tableMock = Mockito.mock(Table.class);

    // setup step 1: create column.
    @SuppressWarnings("unchecked")
    final var column = //
    Column.withParentTableAndIdAndNameAndFieldTypeAndDataTypeClassAndReferenceableTablesAndBackReferenceableColumns(
      tableMock,
      "id",
      "name",
      FieldType.OPTIONAL_VALUE_FIELD,
      Integer.class,
      ImmutableList.createEmpty(),
      ImmutableList.createEmpty());

    // execute
    final var result = FieldMapper.mapColumnToField(column);

    verifyParentsOfCreatedField(result, column);

    // verify part 2: Verifies attributes.
    expect(result.getName()).isEqualTo("name");
    expect(result.getType()).is(FieldType.OPTIONAL_VALUE_FIELD);

    verifyStateOfCreatedField(result);
  }

  @Test
  void testCase_mapColumnToField_whenGivenColumnIsForMultiValueFields() {
    // setup step 1: create tableMock.
    final var tableMock = Mockito.mock(Table.class);

    // setup step 1: create column.
    @SuppressWarnings("unchecked")
    final var column = //
    Column.withParentTableAndIdAndNameAndFieldTypeAndDataTypeClassAndReferenceableTablesAndBackReferenceableColumns(
      tableMock,
      "id",
      "name",
      FieldType.MULTI_VALUE_FIELD,
      Integer.class,
      ImmutableList.createEmpty(),
      ImmutableList.createEmpty());

    // execute
    final var result = FieldMapper.mapColumnToField(column);

    verifyParentsOfCreatedField(result, column);

    // verify part 2: Verifies attributes.
    expect(result.getName()).isEqualTo("name");
    expect(result.getType()).is(FieldType.MULTI_VALUE_FIELD);

    verifyStateOfCreatedField(result);
  }

  private void verifyParentsOfCreatedField(final AbstractField field, final Column column) {
    expect(field.belongsToDatabase()).isFalse();
    expect(field.belongsToTable()).isFalse();
    expect(field.belongsToEntity()).isFalse();
    expect(field.getStoredParentColumn()).is(column);
  }

  private void verifyStateOfCreatedField(final AbstractField field) {
    expect(field.getState()).is(DatabaseObjectState.NEW);
    expect(field.isEmpty());
  }
}
