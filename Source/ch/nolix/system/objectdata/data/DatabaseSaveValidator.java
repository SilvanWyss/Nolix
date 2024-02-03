//package declaration
package ch.nolix.system.objectdata.data;

import ch.nolix.system.objectdata.datatool.DatabaseTool;
import ch.nolix.system.objectdata.datatool.EntityTool;
import ch.nolix.system.objectdata.propertytool.MultiReferenceEntryTool;
import ch.nolix.system.objectdata.propertytool.PropertyTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;
import ch.nolix.systemapi.objectdataapi.dataapi.IReference;
import ch.nolix.systemapi.objectdataapi.datatoolapi.IDatabaseTool;
import ch.nolix.systemapi.objectdataapi.datatoolapi.IEntityTool;
import ch.nolix.systemapi.objectdataapi.propertytoolapi.IMultiReferenceEntryTool;
import ch.nolix.systemapi.objectdataapi.propertytoolapi.IPropertyTool;

//class
public final class DatabaseSaveValidator {

  //constant
  private static final IDatabaseTool DATABASE_TOOL = new DatabaseTool();

  //constant
  private static final IEntityTool ENTITY_TOOL = new EntityTool();

  //constant
  private static final IPropertyTool PROPERTY_TOOL = new PropertyTool();

  //constant
  private static final IMultiReferenceEntryTool MULTI_REFERENCE_ENTRY_TOOL = new MultiReferenceEntryTool();

  //method
  public void addExpectationToDatabaseThatNewlyReferencedEntitiesExist(final Database database) {

    final var entitiesInLocalData = DATABASE_TOOL.getStoredEntitiesInLocalData(database);

    for (final var e : entitiesInLocalData) {
      addExpectationToDatabaseThatNewlyReferencedEntitiesExist(e, database);
    }
  }

  //method
  private void addExpectationToDatabaseThatNewlyReferencedEntitiesExist(
    final IEntity entity,
    final Database database) {
    if (ENTITY_TOOL.isNewOrEdited(entity)) {
      addExpectationToDatabaseThatNewlyReferencedEntitiesExistWhenEntityIsNewOrEdited(entity, database);
    }
  }

  //method
  private void addExpectationToDatabaseThatNewlyReferencedEntitiesExistWhenEntityIsNewOrEdited(
    final IEntity entity,
    final Database database) {
    for (final var p : entity.technicalGetStoredProperties()) {
      addExpectationToDatabaseThatNewlyReferencedEntitiesExist(database, p);
    }
  }

  //method
  private void addExpectationToDatabaseThatNewlyReferencedEntitiesExist(
    final Database database,
    final IProperty property) {
    if (PROPERTY_TOOL.isNewOrEdited(property)) {
      addExpectationToDatabaseThatNewlyReferencedEntitiesExistWhenPropertyIsNewOrEdited(property, database);
    }
  }

  //method
  private void addExpectationToDatabaseThatNewlyReferencedEntitiesExistWhenPropertyIsNewOrEdited(
    final IProperty property,
    final Database database) {
    switch (property.getType()) {
      case REFERENCE:

        final var reference = (IReference<?>) property;

        addExpectationToDatabaseThatNewlyReferencedEntitiesExistWhenReferenceIsNewOrEdited(reference, database);

        break;
      case OPTIONAL_REFERENCE:

        final var optionalReference = (IOptionalReference<?>) property;

        addExpectationToDatabaseThatNewlyReferencedEntitiesExistWhenOptionalReferenceIsNewOrEdited(
          optionalReference,
          database);

        break;
      case MULTI_REFERENCE:

        final var multiReference = (MultiReference<?>) property;

        addExpectationToDatabaseThatNewlyReferencedEntitiesExistWhenMultiReferenceIsNewOrEdited(
          multiReference,
          database);

        break;
      default:
        //Does nothing.
    }
  }

  private void addExpectationToDatabaseThatNewlyReferencedEntitiesExistWhenMultiReferenceIsNewOrEdited(
    final IMultiReference<?> multiReference,
    final Database database) {

    final var referencedTableName = multiReference.getReferencedTableName();

    for (final var le : multiReference.getStoredLocalEntries()) {
      if (MULTI_REFERENCE_ENTRY_TOOL.isNewOrEdited(le)) {
        database.internalGetRefDataAndSchemaAdapter().expectTableContainsEntity(
          referencedTableName,
          le.getReferencedEntityId());
      }
    }
  }

  //method
  private void addExpectationToDatabaseThatNewlyReferencedEntitiesExistWhenOptionalReferenceIsNewOrEdited(
    final IOptionalReference<?> optionalReference,
    final Database database) {
    if (optionalReference.containsAny()) {
      database.internalGetRefDataAndSchemaAdapter().expectTableContainsEntity(
        optionalReference.getReferencedTableName(),
        optionalReference.getReferencedEntityId());
    }
  }

  //method
  private void addExpectationToDatabaseThatNewlyReferencedEntitiesExistWhenReferenceIsNewOrEdited(
    final IReference<?> reference,
    final Database database) {
    database.internalGetRefDataAndSchemaAdapter().expectTableContainsEntity(
      reference.getReferencedTableName(),
      reference.getReferencedEntityId());
  }
}
