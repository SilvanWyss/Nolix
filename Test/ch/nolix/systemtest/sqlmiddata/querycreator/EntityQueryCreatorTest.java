/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.sqlmiddata.querycreator;

import org.junit.jupiter.api.Test;

import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.datamodel.fieldproperty.DataType;
import ch.nolix.system.sqlmiddata.querycreator.EntityQueryCreator;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.midschemainfo.model.ColumnInfoDto;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;

/**
 * @author Silvan Wyss
 */
final class EntityQueryCreatorTest extends StandardTest {
  @Test
  void testCase_createQueryToCountEntitiesWithGivenValueAtGivenColumn() {
    //setup
    final var testUnit = new EntityQueryCreator();

    //execution
    final var result = testUnit.createQueryToCountEntitiesWithGivenValueAtGivenColumn("MyTable", "MyColumn",
      "my_value");

    //verification
    final var expectedResult = "SELECT COUNT(MyColumn) FROM MyTable WHERE MyColumn = 'my_value';";
    expect(result).isEqualTo(expectedResult);
  }

  @Test
  void testCase_createQueryToLoadEntitiesOfTable() {
    //setup
    final var testUnit = new EntityQueryCreator();
    final var tableView = new TableInfoDto(
      "ttttttt1",
      "Cat",
      ImmutableList.withElements(
        new ColumnInfoDto("ccccccc1", "name", 0, FieldType.VALUE_FIELD, DataType.STRING),
        new ColumnInfoDto("ccccccc2", "year_of_birth", 0, FieldType.VALUE_FIELD, DataType.INTEGER_4BYTE)));

    //execution
    final var result = testUnit.createQueryToLoadEntitiesOfTable(tableView);

    //verification
    final var expectedResult = "SELECT Id, SaveStamp, name, year_of_birth FROM Cat;";
    expect(result).isEqualTo(expectedResult);
  }

  @Test
  void testCase_createQueryToLoadEntity() {
    //setup
    final var testUnit = new EntityQueryCreator();
    final var tableView = new TableInfoDto(
      "ttttttt1",
      "Cat",
      ImmutableList.withElements(
        new ColumnInfoDto("ccccccc1", "name", 0, FieldType.VALUE_FIELD, DataType.STRING),
        new ColumnInfoDto("ccccccc2", "year_of_birth", 0, FieldType.VALUE_FIELD, DataType.INTEGER_4BYTE)));

    //execution
    final var result = testUnit.createQueryToLoadEntity("eeeeeee1", tableView);

    //verification
    final var expectedResult = "SELECT Id, SaveStamp, name, year_of_birth FROM Cat WHERE Id = 'eeeeeee1';";
    expect(result).isEqualTo(expectedResult);
  }

  @Test
  void testCase_createQueryToLoadSchemaTimestamp() {
    //setup
    final var testUnit = new EntityQueryCreator();

    //execution
    final var result = testUnit.createQueryToLoadSchemaTimestamp();

    //verification setup
    final var expectedResult = "SELECT Value_ FROM DatabaseProperty WHERE ValueKey = 'SchemaTimestamp';";

    //verification
    expect(result).isEqualTo(expectedResult);
  }
}
