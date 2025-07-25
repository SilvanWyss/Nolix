package ch.nolix.system.objectdata.model;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.programatomapi.variableapi.PluralLowerCaseVariableCatalog;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntityTypeSet;

public final class EntityTypeSet implements IEntityTypeSet {

  public static final EntityTypeSet EMPTY_SCHEMA = new EntityTypeSet(ImmutableList.createEmpty());

  private final ImmutableList<Class<? extends IEntity>> entityTypes;

  private EntityTypeSet(final IContainer<Class<? extends IEntity>> entityTypes) {

    Validator.assertThat(entityTypes)
      .thatIsNamed(PluralLowerCaseVariableCatalog.ENTITY_TYPES)
      .containsDistinctNonNullElemensOnly();

    this.entityTypes = ImmutableList.forIterable(entityTypes);
  }

  @SuppressWarnings("unchecked")
  public static EntityTypeSet withEntityType(
    final Class<?> entityType,
    final Class<?>... entityTypes) {

    final ILinkedList<Class<? extends IEntity>> allEntityTypes = LinkedList.createEmpty();

    allEntityTypes.addAtEnd((Class<IEntity>) entityType);

    for (final var et : entityTypes) {
      allEntityTypes.addAtEnd((Class<IEntity>) et);
    }

    return new EntityTypeSet(allEntityTypes);
  }

  public static EntityTypeSet withEntityTypes(IContainer<Class<? extends IEntity>> entityTypes) {
    return new EntityTypeSet(entityTypes);
  }

  @Override
  public IContainer<Class<? extends IEntity>> getEntityTypes() {
    return entityTypes;
  }
}
