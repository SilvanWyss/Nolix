package ch.nolix.systemtest.objectdata.model;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.model.Column;
import ch.nolix.system.objectdata.model.FieldMapper;
import ch.nolix.system.objectdata.model.Table;
import ch.nolix.systemapi.databaseobject.property.DatabaseObjectState;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

final class FieldMapperTest extends StandardTest {
  @Test
  void testCase_mapColumnToField() {
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
    expect(result.belongsToDatabase()).isFalse();
    expect(result.belongsToTable()).isFalse();
    expect(result.belongsToEntity()).isFalse();
    expect(result.getStoredParentColumn()).is(column);

    //verification part 2: Verifies attributes.
    expect(result.getName()).isEqualTo("name");
    expect(result.getType()).is(FieldType.VALUE_FIELD);

    //verification part 3: Verifies states.
    expect(result.getState()).is(DatabaseObjectState.NEW);
    expect(result.isEmpty());
  }
}
