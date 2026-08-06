/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.entitytool;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.errorcontrol.generalexception.WrapperException;
import ch.nolix.base.reflection.reflectiontool.ReflectionTool;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.systemapi.objectdata.entitytool.IEntityFieldExtractor;
import ch.nolix.systemapi.objectdata.model.Field;

/**
 * @author Silvan Wyss
 */
public final class EntityFieldExtractor implements IEntityFieldExtractor {
  private static <F extends Field> ExtendedIterable<F> extractStoredFieldsFromEntityWhenEntityIsNotNull(
    final Object entity,
    final Class<F> fieldClass) {
    final ILinkedList<F> fields = LinkedList.createEmpty();

    fillUpFieldsFromEntityIntoList(entity, fieldClass, fields);

    return fields;
  }

  private static <F extends Field> void fillUpFieldsFromEntityIntoList(
    final Object entity,
    final Class<F> fieldClass,
    final ILinkedList<F> list) {
    Class<?> entityClass = entity.getClass();

    while (entityClass != null) {
      fillUpFieldsFromEntityAndEntityClassIntoList(entity, entityClass, fieldClass, list);

      entityClass = entityClass.getSuperclass();
    }
  }

  private static <F extends Field> void fillUpFieldsFromEntityAndEntityClassIntoList(
    final Object entity,
    final Class<?> entityClass,
    final Class<F> fieldClass,
    final ILinkedList<F> list) {
    for (final var f : entityClass.getDeclaredFields()) {
      if (isField(f, fieldClass)) {
        fillUpFieldFromEntityAndJavaFieldIntoList(entity, f, list);
      }
    }
  }

  private static <F extends Field> boolean isField(final java.lang.reflect.Field javaField, final Class<F> fieldClass) {
    return ReflectionTool.canStoreValueOfTypeOrSuperType(javaField, fieldClass);
  }

  private static <F extends Field> void fillUpFieldFromEntityAndJavaFieldIntoList(
    final Object entity,
    final java.lang.reflect.Field javaField,
    final ILinkedList<F> list) {
    javaField.setAccessible(true);

    try {
      @SuppressWarnings("unchecked")
      final var field = (F) javaField.get(entity);

      list.addAtEnd(field);
    } catch (final IllegalAccessException illegalAccessException) {
      throw WrapperException.forError(illegalAccessException);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <F extends Field> ExtendedIterable<F> extractStoredFieldsFromEntity(
    final Object entity,
    Class<F> fieldClass) {
    if (entity == null) {
      return ImmutableList.createEmpty();
    }

    return extractStoredFieldsFromEntityWhenEntityIsNotNull(entity, fieldClass);
  }
}
