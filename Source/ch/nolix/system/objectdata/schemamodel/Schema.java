package ch.nolix.system.objectdata.schemamodel;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.variableapi.PluralLowerCaseVariableCatalog;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.schemamodelapi.ISchema;

public final class Schema implements ISchema {

  public static final Schema EMPTY_SCHEMA = new Schema(ImmutableList.createEmpty());

  private final ImmutableList<Class<? extends IEntity>> entityTypes;

  private Schema(final IContainer<Class<? extends IEntity>> entityTypes) {

    Validator.assertThat(entityTypes)
      .thatIsNamed(PluralLowerCaseVariableCatalog.ENTITY_TYPES)
      .containsDistinctNonNullElemensOnly();

    this.entityTypes = ImmutableList.forIterable(entityTypes);
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
  public IContainer<Class<? extends IEntity>> getEntityTypes() {
    return entityTypes;
  }
}
