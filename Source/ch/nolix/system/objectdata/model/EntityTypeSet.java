/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.model;

import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.base.container.linkedlist.LinkedList;
import ch.nolix.base.errorcontrol.validator.Validator;
import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.container.list.ILinkedList;
import ch.nolix.baseapi.misc.variable.PluralLowerCaseVariableCatalog;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IEntityTypeSet;

/**
 * @author Silvan Wyss
 */
public final class EntityTypeSet implements IEntityTypeSet {
  public static final EntityTypeSet EMPTY_SCHEMA = new EntityTypeSet(ImmutableList.createEmpty());

  private final ImmutableList<Class<? extends IEntity>> entityTypes;

  private EntityTypeSet(final IContainer<Class<? extends IEntity>> entityTypes) {
    Validator.assertThat(entityTypes)
      .thatIsNamed(PluralLowerCaseVariableCatalog.ENTITY_TYPES)
      .containsDistinctNonNullElemensOnly();

    this.entityTypes = ImmutableList.fromIterable(entityTypes);
  }

  @SuppressWarnings("unchecked")
  public static EntityTypeSet withEntityType(
    final Class<?> entityType,
    final Class<?>... entityTypes) {
    final ILinkedList<Class<? extends IEntity>> allEntityTypes = LinkedList.createEmpty();

    allEntityTypes.addAtEnd((Class<IEntity>) entityType);

    for (final var t : entityTypes) {
      allEntityTypes.addAtEnd((Class<IEntity>) t);
    }

    return new EntityTypeSet(allEntityTypes);
  }

  public static EntityTypeSet withEntityTypes(IContainer<Class<? extends IEntity>> entityTypes) {
    return new EntityTypeSet(entityTypes);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<Class<? extends IEntity>> getEntityTypes() {
    return entityTypes;
  }
}
