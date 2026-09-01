/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.objectdata.fieldexaminer;

import org.junit.jupiter.api.Test;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.adapter.NodeDataAdapter;
import ch.nolix.system.objectdata.fieldexaminer.ValueFieldExaminer;
import ch.nolix.system.objectdata.model.Entity;
import ch.nolix.system.objectdata.model.EntityTypeSet;
import ch.nolix.system.objectdata.model.ValueField;

/**
 * @author Silvan Wyss
 */
final class ValueFieldExaminerTest extends StandardTest {
  private static final class Pet extends Entity {
    private final ValueField<String> name = ValueField.withValueType(String.class);

    public Pet() {
      initialize();
    }
  }

  @Test
  void testCase_canSetValue() {
    // setup
    final var pet = new Pet();
    final String valueToSet = "Garfield";
    final var testUnit = new ValueFieldExaminer();

    // execute
    final var result = testUnit.canSetValue(pet.name, valueToSet);

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_canSetValue_whenTheGivenValueIsClosed() {
    // setup
    final var pet = new Pet();
    try ( //
    final var databaseAdapter = //
    NodeDataAdapter
      .forTemporaryInMemoryDatabase()
      .withName("my_database")
      .andSchema(EntityTypeSet.withEntityType(Pet.class))) {
      databaseAdapter.insertEntity(pet);
    }
    final String valueToSet = "Garfield";
    final var testUnit = new ValueFieldExaminer();

    // setup verification
    expect(pet.name.isClosed()).isTrue();

    // execute
    final var result = testUnit.canSetValue(pet.name, valueToSet);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_canSetValue_whenTheGivenValueToSetIsNull() {
    // setup
    final var pet = new Pet();
    final String valueToSet = null;
    final var testUnit = new ValueFieldExaminer();

    // execute
    final var result = testUnit.canSetValue(pet.name, valueToSet);

    // verify
    expect(result).isFalse();
  }
}
