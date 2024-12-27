package ch.nolix.applicationtest.relationaldoctest.backendtest.datamodeltest;

import org.junit.jupiter.api.Test;

import ch.nolix.application.relationaldoc.backend.datamodel.CategorizableField;
import ch.nolix.application.relationaldoc.backend.datamodel.CategorizableObject;
import ch.nolix.application.relationaldoc.backend.datamodel.SchemaCatalogue;
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.adapter.NodeDataAdapter;

final class AbstractableObjectOnDatabaseTest extends StandardTest {

  @Test
  void testCase_getStoredBaseTypes_whenDoesNotHaveBaseTypes() {

    //setup part 1: Create database.
    final var database = MutableNode.createEmpty();

    //setup part 2: Create dataAdapter.
    final var dataAdapter = NodeDataAdapter
      .forNodeDatabase(database)
      .withName("test_database")
      .andSchema(SchemaCatalogue.RELATIONAL_DOC_SCHEMA);

    //setup part 3: Create testUnit.
    final var testUnit = new CategorizableObject();
    dataAdapter.insertEntity(testUnit);

    //execution
    final var result = testUnit.getStoredBaseTypes();

    //verification
    expect(result).isEmpty();
  }

  @Test
  void testCase_getStoredBaseTypes_whenHasSeveralBaseTypes() {

    //setup part 1: Create database.
    final var database = MutableNode.createEmpty();

    //setup part 2: Create dataAdapter.
    final var dataAdapter = NodeDataAdapter
      .forNodeDatabase(database)
      .withName("test_database")
      .andSchema(SchemaCatalogue.RELATIONAL_DOC_SCHEMA);

    //setup part 3: Create baseType1.
    final var baseType1 = new CategorizableObject();
    dataAdapter.insertEntity(baseType1);
    baseType1.setName("base_type_1");
    baseType1.setAsAbstract();

    //setup part 4: Create baseType2.
    final var baseType2 = new CategorizableObject();
    dataAdapter.insertEntity(baseType2);
    baseType2.setName("base_type_2");
    baseType2.setAsAbstract();

    //setup part 3: Create testUnit.
    final var testUnit = new CategorizableObject();
    dataAdapter.insertEntity(testUnit);
    testUnit.setName("test_unit");
    testUnit.addBaseType(baseType1);
    testUnit.addBaseType(baseType2);

    //execution
    final var result = testUnit.getStoredBaseTypes();

    //verification
    expect(result).containsExactly(baseType1, baseType2);
  }

  @Test
  void testCase_getStoredBaseTypes_whenHasBaseTypeWithBaseType() {

    //setup part 1: Create database.
    final var database = MutableNode.createEmpty();

    //setup part 2: Create dataAdapter.
    final var dataAdapter = NodeDataAdapter
      .forNodeDatabase(database)
      .withName("test_database")
      .andSchema(SchemaCatalogue.RELATIONAL_DOC_SCHEMA);

    //setup part 3: Create level1Type.
    final var level1Oject = new CategorizableObject();
    dataAdapter.insertEntity(level1Oject);
    level1Oject.setName("level_1_object");
    level1Oject.setAsAbstract();

    //setup part 4: Create level2Type.
    final var level2Oject = new CategorizableObject();
    dataAdapter.insertEntity(level2Oject);
    level2Oject.setName("level_2_object");
    level2Oject.setAsAbstract();
    level2Oject.addBaseType(level1Oject);

    //setup part 3: Create testUnit.
    final var testUnit = new CategorizableObject();
    dataAdapter.insertEntity(testUnit);
    testUnit.setName("test_unit");
    testUnit.addBaseType(level2Oject);

    //execution
    final var result = testUnit.getStoredBaseTypes();

    //verification
    expect(result).containsExactly(level1Oject, level2Oject);
  }

  @Test
  void testCase_getStoredConcreteSubTypes_whenHasSeveralConcreteSubTypes() {

    //setup part 1: Create database.
    final var database = MutableNode.createEmpty();

    //setup part 2: Create dataAdapter.
    final var dataAdapter = NodeDataAdapter
      .forNodeDatabase(database)
      .withName("test_database")
      .andSchema(SchemaCatalogue.RELATIONAL_DOC_SCHEMA);

    //setup part 3: Create subType1.
    final var subType1 = new CategorizableObject();
    dataAdapter.insertEntity(subType1);
    subType1.setName("sub_type_1");

    //setup part 4: Create subType2.
    final var subType2 = new CategorizableObject();
    dataAdapter.insertEntity(subType2);
    subType2.setName("sub_type_2");

    //setup part 3: Create testUnit.
    final var testUnit = new CategorizableObject();
    testUnit.setName("test_unit");
    testUnit.setAsAbstract();
    subType1.addBaseType(testUnit);
    subType2.addBaseType(testUnit);

    //execution
    final var result = testUnit.getStoredConcreteSubTypes();

    //verification
    expect(result).containsExactly(subType1, subType2);
  }

  @Test
  void testCase_getStoredSubTypes_whenDoesNotHaveSubTypes() {

    //setup part 1: Create database.
    final var database = MutableNode.createEmpty();

    //setup part 2: Create dataAdapter.
    final var dataAdapter = NodeDataAdapter
      .forNodeDatabase(database)
      .withName("test_database")
      .andSchema(SchemaCatalogue.RELATIONAL_DOC_SCHEMA);

    //setup part 3: Create testUnit.
    final var testUnit = new CategorizableObject();
    dataAdapter.insertEntity(testUnit);

    //execution
    final var result = testUnit.getStoredDirectSubTypes();

    //verification
    expect(result).isEmpty();
  }

  @Test
  void testCase_getStoredSubTypes_whenHasSeveralSubTypes() {

    //setup part 1: Create database.
    final var database = MutableNode.createEmpty();

    //setup part 2: Create dataAdapter.
    final var dataAdapter = NodeDataAdapter
      .forNodeDatabase(database)
      .withName("test_database")
      .andSchema(SchemaCatalogue.RELATIONAL_DOC_SCHEMA);

    //setup part 3: Create subType1.
    final var subType1 = new CategorizableObject();
    dataAdapter.insertEntity(subType1);
    subType1.setName("sub_type_1");

    //setup part 4: Create subType2.
    final var subType2 = new CategorizableObject();
    dataAdapter.insertEntity(subType2);
    subType2.setName("sub_type_2");

    //setup part 3: Create testUnit.
    final var testUnit = new CategorizableObject();
    dataAdapter.insertEntity(testUnit);
    testUnit.setName("test_unit");
    testUnit.setAsAbstract();
    subType1.addBaseType(testUnit);
    subType2.addBaseType(testUnit);

    //execution
    final var result = testUnit.getStoredSubTypes();

    //verification
    expect(result).containsExactly(subType1, subType2);
  }

  @Test
  void testCase_isSaved_whenNewFieldWasAdded() {

    //setup part 1: Create database.
    final var database = MutableNode.createEmpty();

    //setup part 2: Create dataAdapter.
    final var dataAdapter = NodeDataAdapter
      .forNodeDatabase(database)
      .withName("test_database")
      .andSchema(SchemaCatalogue.RELATIONAL_DOC_SCHEMA);

    //setup part 3: Create testUnit.
    final var testUnit = new CategorizableObject();
    dataAdapter.insertEntity(testUnit);
    final var field = new CategorizableField();
    dataAdapter.insertEntity(field);
    testUnit.addField(field);

    //execution & verification
    expectRunning(dataAdapter::saveChanges).doesNotThrowException();
  }
}
