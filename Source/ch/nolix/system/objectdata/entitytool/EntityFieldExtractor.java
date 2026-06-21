/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.entitytool;

import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.base.container.linkedlist.LinkedList;
import ch.nolix.base.errorcontrol.generalexception.WrapperException;
import ch.nolix.base.reflection.reflectiontool.ReflectionTool;
import ch.nolix.baseapi.container.list.ILinkedList;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.system.objectdata.model.AbstractField;
import ch.nolix.systemapi.objectdata.entitytool.IEntityFieldExtractor;
import ch.nolix.systemapi.objectdata.model.IEntity;

/**
 * @author Silvan Wyss
 */
public final class EntityFieldExtractor implements IEntityFieldExtractor<IEntity, AbstractField> {
  private static ExtendedIterable<AbstractField> extractStoredFieldsFromEntityWhenEntityIsNotNull(final IEntity entity) {
    final ILinkedList<AbstractField> fields = LinkedList.createEmpty();

    fillUpFieldsFromEntityIntoList(entity, fields);

    return fields;
  }

  private static void fillUpFieldsFromEntityIntoList(final IEntity entity, final ILinkedList<AbstractField> list) {
    Class<?> entityClass = entity.getClass();

    while (entityClass != null) {
      fillUpFieldsFromEntityAndEntityClassIntoList(entity, entityClass, list);

      entityClass = entityClass.getSuperclass();
    }
  }

  private static void fillUpFieldsFromEntityAndEntityClassIntoList(
    final IEntity entity,
    final Class<?> entityClass,
    final ILinkedList<AbstractField> list) {
    for (final var f : entityClass.getDeclaredFields()) {
      if (isField(f)) {
        fillUpFieldFromEntityAndJavaFieldIntoList(entity, f, list);
      }
    }
  }

  private static boolean isField(final java.lang.reflect.Field javaField) {
    return ReflectionTool.canStoreValueOfTypeOrSuperType(javaField, AbstractField.class);
  }

  private static void fillUpFieldFromEntityAndJavaFieldIntoList(
    final IEntity entity,
    final java.lang.reflect.Field javaField,
    final ILinkedList<AbstractField> list) {
    javaField.setAccessible(true);

    try {
      final var field = (AbstractField) javaField.get(entity);

      list.addAtEnd(field);
    } catch (final IllegalAccessException illegalAccessException) {
      throw WrapperException.forError(illegalAccessException);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<AbstractField> extractStoredFieldsFromEntity(final IEntity entity) {
    if (entity == null) {
      return ImmutableList.createEmpty();
    }

    return extractStoredFieldsFromEntityWhenEntityIsNotNull(entity);
  }
}
