//package declaration
package ch.nolix.system.objectdata.schema;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.schemaapi.ISchema;

//class
public final class Schema implements ISchema {

  //constant
  public static final Schema EMPTY_SCHEMA = new Schema(ImmutableList.createEmpty());

  //multi-attribute
  private final IContainer<Class<? extends IEntity>> entityTypes;

  //constructor
  private Schema(final IContainer<Class<? extends IEntity>> entityTypes) {

    assertContainsDifferentEntityTypesOnly(entityTypes);

    this.entityTypes = entityTypes;
  }

  //static method
  @SuppressWarnings("unchecked")
  public static Schema withEntityType(
    final Class<?> entityType,
    final Class<?>... entityTypes) {

    final var allEntityTypes = new LinkedList<Class<? extends IEntity>>();

    allEntityTypes.addAtEnd((Class<IEntity>) entityType);

    for (final var et : entityTypes) {
      allEntityTypes.addAtEnd((Class<IEntity>) et);
    }

    return new Schema(allEntityTypes);
  }

  //static method
  public static Schema withEntityTypes(IContainer<Class<? extends IEntity>> entityTypes) {
    return new Schema(entityTypes);
  }

  //method
  @Override
  public Class<? extends IEntity> getEntityTypeByName(final String name) {
    return getEntityTypes().getStoredFirst(et -> et.getSimpleName().equals(name));
  }

  //method
  @Override
  public IContainer<Class<? extends IEntity>> getEntityTypes() {
    return entityTypes;
  }

  //method
  private void assertContainsDifferentEntityTypesOnly(
    final IContainer<Class<? extends IEntity>> entityTypes) {
    if (!containsDifferentEntityTypesOnly(entityTypes)) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        "list of entity types",
        entityTypes,
        "does not contain different entity types only");
    }
  }

  //method
  private boolean containsDifferentEntityTypesOnly(
    final IContainer<Class<? extends IEntity>> entityTypes) {
    return entityTypes.getStoredGroups(Class::getSimpleName).containsAsManyAs(entityTypes);
  }
}
