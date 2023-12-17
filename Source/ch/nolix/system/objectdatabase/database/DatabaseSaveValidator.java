//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.system.objectdatabase.databasehelper.DatabaseHelper;
import ch.nolix.system.objectdatabase.databasehelper.EntityHelper;
import ch.nolix.system.objectdatabase.propertyhelper.MultiReferenceEntryHelper;
import ch.nolix.system.objectdatabase.propertyhelper.PropertyHelper;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IReference;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.IDatabaseHelper;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.IEntityHelper;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IMultiReferenceEntryHelper;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IPropertyHelper;

//class
public final class DatabaseSaveValidator {

  //constant
  private static final IDatabaseHelper DATABASE_HELPER = new DatabaseHelper();

  //constant
  private static final IEntityHelper ENTITY_HELPER = new EntityHelper();

  //constant
  private static final IPropertyHelper PROPERTY_HELPER = new PropertyHelper();

  //constant
  private static final IMultiReferenceEntryHelper MULTI_REFERENCE_ENTRY_HELPER = new MultiReferenceEntryHelper();

  //method
  public void addExpectationToDatabaseThatNewlyReferencedEntitiesExist(final Database database) {

    final var entitiesInLocalData = DATABASE_HELPER.getStoredEntitiesInLocalData(database);

    for (final var e : entitiesInLocalData) {
      addExpectationToDatabaseThatNewlyReferencedEntitiesExist(e, database);
    }
  }

  //method
  private void addExpectationToDatabaseThatNewlyReferencedEntitiesExist(
    final IEntity entity,
    final Database database) {
    if (ENTITY_HELPER.isNewOrEdited(entity)) {
      addExpectationToDatabaseThatNewlyReferencedEntitiesExistWhenEntityIsNewOrEdited(entity, database);
    }
  }

  //method
  private void addExpectationToDatabaseThatNewlyReferencedEntitiesExistWhenEntityIsNewOrEdited(
    final IEntity entity,
    final Database database) {
    for (final var p : entity.technicalGetRefProperties()) {
      addExpectationToDatabaseThatNewlyReferencedEntitiesExist(database, p);
    }
  }

  //method
  private void addExpectationToDatabaseThatNewlyReferencedEntitiesExist(
    final Database database,
    final IProperty property) {
    if (PROPERTY_HELPER.isNewOrEdited(property)) {
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

        database.internalGetRefDataAndSchemaAdapter().expectTableContainsEntity(
          reference.getReferencedTableName(),
          reference.getReferencedEntityId());

        break;
      case OPTIONAL_REFERENCE:

        final var optionalReference = (OptionalReference<?>) property;

        if (optionalReference.containsAny()) {
          database.internalGetRefDataAndSchemaAdapter().expectTableContainsEntity(
            optionalReference.getReferencedTableName(),
            optionalReference.getReferencedEntityId());
        }

        break;
      case MULTI_REFERENCE:

        final var multiReference = (MultiReference<?>) property;
        final var referencedTableName = multiReference.getReferencedTableName();

        for (final var le : multiReference.getStoredLocalEntries()) {
          if (MULTI_REFERENCE_ENTRY_HELPER.isNewOrEdited(le)) {
            database.internalGetRefDataAndSchemaAdapter().expectTableContainsEntity(
              referencedTableName,
              le.getReferencedEntityId());
          }
        }

        break;
      default:
        //Does nothing.
    }
  }
}
