//package declaration
package ch.nolix.system.objectdata.changesetsaver;

//own imports
import ch.nolix.system.objectdata.datatool.DatabaseTool;
import ch.nolix.system.objectdata.datatool.EntityTool;
import ch.nolix.system.objectdata.propertytool.PropertyTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IDatabase;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.objectdataapi.dataapi.IReference;
import ch.nolix.systemapi.objectdataapi.datatoolapi.IDatabaseTool;
import ch.nolix.systemapi.objectdataapi.datatoolapi.IEntityTool;
import ch.nolix.systemapi.objectdataapi.propertytoolapi.IPropertyTool;
import ch.nolix.systemapi.rawdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;

//class
public final class ChangeSetSaveValidator {

  //constant
  private static final IDatabaseTool DATABASE_TOOL = new DatabaseTool();

  //constant
  private static final IEntityTool ENTITY_TOOL = new EntityTool();

  //constant
  private static final IPropertyTool PROPERTY_TOOL = new PropertyTool();

  //method
  public void addExpectationToDatabaseThatNewlyReferencedEntitiesExist(
    final IDatabase database,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {

    final var entitiesInLocalData = DATABASE_TOOL.getStoredEntitiesInLocalData(database);

    for (final var e : entitiesInLocalData) {
      addExpectationToDatabaseThatNewlyReferencedEntitiesExist(e, dataAndSchemaAdapter);
    }
  }

  //method
  private void addExpectationToDatabaseThatNewlyReferencedEntitiesExist(
    final IEntity entity,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {
    if (ENTITY_TOOL.isNewOrEdited(entity)) {
      addExpectationToDatabaseThatNewlyReferencedEntitiesExistWhenEntityIsNewOrEdited(entity, dataAndSchemaAdapter);
    }
  }

  //method
  private void addExpectationToDatabaseThatNewlyReferencedEntitiesExistWhenEntityIsNewOrEdited(
    final IEntity entity,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {
    for (final var p : entity.internalGetStoredProperties()) {
      addExpectationToDatabaseThatNewlyReferencedEntitiesExist(p, dataAndSchemaAdapter);
    }
  }

  //method
  private void addExpectationToDatabaseThatNewlyReferencedEntitiesExist(
    final IField field,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {
    if (PROPERTY_TOOL.isNewOrEdited(field)) {
      addExpectationToDatabaseThatNewlyReferencedEntitiesExistWhenPropertyIsNewOrEdited(field,
        dataAndSchemaAdapter);
    }
  }

  //method
  private void addExpectationToDatabaseThatNewlyReferencedEntitiesExistWhenPropertyIsNewOrEdited(
    final IField field,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {
    switch (field.getType()) {
      case REFERENCE:

        final var reference = (IReference<?>) field;

        addExpectationToDatabaseThatNewlyReferencedEntitiesExistWhenReferenceIsNewOrEdited(reference,
          dataAndSchemaAdapter);

        break;
      case OPTIONAL_REFERENCE:

        final var optionalReference = (IOptionalReference<?>) field;

        addExpectationToDatabaseThatNewlyReferencedEntitiesExistWhenOptionalReferenceIsNewOrEdited(
          optionalReference,
          dataAndSchemaAdapter);

        break;
      case MULTI_REFERENCE:

        final var multiReference = (IMultiReference<?>) field;

        addExpectationToDatabaseThatNewlyReferencedEntitiesExistWhenMultiReferenceIsNewOrEdited(
          multiReference,
          dataAndSchemaAdapter);

        break;
      default:
        //Does nothing.
    }
  }

  private void addExpectationToDatabaseThatNewlyReferencedEntitiesExistWhenMultiReferenceIsNewOrEdited(
    final IMultiReference<?> multiReference,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {

    final var referencedTableName = multiReference.getReferencedTableName();

    for (final var le : multiReference.getStoredNewAndDeletedEntries()) {
      dataAndSchemaAdapter.expectTableContainsEntity(
        referencedTableName,
        le.getReferencedEntityId());
    }
  }

  //method
  private void addExpectationToDatabaseThatNewlyReferencedEntitiesExistWhenOptionalReferenceIsNewOrEdited(
    final IOptionalReference<?> optionalReference,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {
    if (optionalReference.containsAny()) {
      dataAndSchemaAdapter.expectTableContainsEntity(
        optionalReference.getReferencedTableName(),
        optionalReference.getReferencedEntityId());
    }
  }

  //method
  private void addExpectationToDatabaseThatNewlyReferencedEntitiesExistWhenReferenceIsNewOrEdited(
    final IReference<?> reference,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {
    dataAndSchemaAdapter.expectTableContainsEntity(
      reference.getReferencedTableName(),
      reference.getReferencedEntityId());
  }
}
