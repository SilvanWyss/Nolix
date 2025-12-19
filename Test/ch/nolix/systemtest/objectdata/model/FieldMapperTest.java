package ch.nolix.systemtest.objectdata.model;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.model.AbstractField;
import ch.nolix.system.objectdata.model.Column;
import ch.nolix.system.objectdata.model.FieldMapper;
import ch.nolix.system.objectdata.model.Table;
import ch.nolix.systemapi.databaseobject.property.DatabaseObjectState;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

/**
 * @author Silvan Wyss
 */
final class FieldMapperTest extends StandardTest {
  @Test
  void testCase_mapColumnToField_whenGivenColumnIsForValueFields() {
    //setup part 1: Creates tableMock.
    final var tableMock = Mockito.mock(Table.class);

    //setup part 1: Creates column.
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

    //execution
    final var result = FieldMapper.mapColumnToField(column);

    //verification part 1: Verifies parents.
    verifyParentsOfCreatedField(result, column);

    //verification part 2: Verifies attributes.
    expect(result.getName()).isEqualTo("name");
    expect(result.getType()).is(FieldType.VALUE_FIELD);

    //verification part 3: Verifies states.
    verifyStateOfCreatedField(result);
  }

  @Test
  void testCase_mapColumnToField_whenGivenColumnIsForOptionalValueFields() {
    //setup part 1: Creates tableMock.
    final var tableMock = Mockito.mock(Table.class);

    //setup part 1: Creates column.
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

    //execution
    final var result = FieldMapper.mapColumnToField(column);

    verifyParentsOfCreatedField(result, column);

    //verification part 2: Verifies attributes.
    expect(result.getName()).isEqualTo("name");
    expect(result.getType()).is(FieldType.OPTIONAL_VALUE_FIELD);

    verifyStateOfCreatedField(result);
  }

  @Test
  void testCase_mapColumnToField_whenGivenColumnIsForMultiValueFields() {
    //setup part 1: Creates tableMock.
    final var tableMock = Mockito.mock(Table.class);

    //setup part 1: Creates column.
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

    //execution
    final var result = FieldMapper.mapColumnToField(column);

    verifyParentsOfCreatedField(result, column);

    //verification part 2: Verifies attributes.
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
