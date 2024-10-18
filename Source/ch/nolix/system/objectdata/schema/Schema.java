package ch.nolix.system.objectdata.schema;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.schemaapi.ISchema;

public final class Schema implements ISchema {

  public static final Schema EMPTY_SCHEMA = new Schema(ImmutableList.createEmpty());

  private final IContainer<Class<? extends IEntity>> entityTypes;

  private Schema(final IContainer<Class<? extends IEntity>> entityTypes) {

    assertContainsDifferentEntityTypesOnly(entityTypes);

    this.entityTypes = entityTypes;
  }

  @SuppressWarnings("unchecked")
  public static Schema withEntityType(
    final Class<?> entityType,
    final Class<?>... entityTypes) {

    final ILinkedList<Class<? extends IEntity>> allEntityTypes = LinkedList.createEmpty();

    allEntityTypes.addAtEnd((Class<IEntity>) entityType);

    for (final var et : entityTypes) {
      allEntityTypes.addAtEnd((Class<IEntity>) et);
    }

    return new Schema(allEntityTypes);
  }

  public static Schema withEntityTypes(IContainer<Class<? extends IEntity>> entityTypes) {
    return new Schema(entityTypes);
  }

  @Override
  public Class<? extends IEntity> getEntityTypeByName(final String name) {
    return getEntityTypes().getStoredFirst(et -> et.getSimpleName().equals(name));
  }

  @Override
  public IContainer<Class<? extends IEntity>> getEntityTypes() {
    return entityTypes;
  }

  private void assertContainsDifferentEntityTypesOnly(
    final IContainer<Class<? extends IEntity>> entityTypes) {
    if (!containsDifferentEntityTypesOnly(entityTypes)) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        "list of entity types",
        entityTypes,
        "does not contain different entity types only");
    }
  }

  private boolean containsDifferentEntityTypesOnly(
    final IContainer<Class<? extends IEntity>> entityTypes) {
    return entityTypes.getStoredGroups(Class::getSimpleName).containsAsManyAs(entityTypes);
  }
}
