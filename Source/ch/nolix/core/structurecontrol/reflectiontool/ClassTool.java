package ch.nolix.core.structurecontrol.reflectiontool;

import java.lang.reflect.Constructor;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.structurecontrol.reflectionexaminer.FieldExaminer;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.structurecontrolapi.reflectionexaminerapi.IFieldExaminer;
import ch.nolix.coreapi.structurecontrolapi.reflectiontoolapi.IClassTool;

public final class ClassTool implements IClassTool {

  private static final IFieldExaminer FIELD_EXAMINER = new FieldExaminer();

  private static final ConstructorTool CONSTRUCTOR_TOOL = new ConstructorTool();

  @Override
  public <T> T createInstanceFromDefaultConstructorOf(final Class<T> paramClass) {
    return CONSTRUCTOR_TOOL.createInstanceFromDefaultConstructor(getDefaultConstructorOfClass(paramClass));
  }

  @Override
  public <T> Constructor<T> getDefaultConstructorOfClass(final Class<T> paramClass) {
    try {

      final var defaultConstructor = paramClass.getDeclaredConstructor();

      defaultConstructor.setAccessible(true);

      return defaultConstructor;
    } catch (final NoSuchMethodException noSuchMethodException) {
      throw WrapperException.forError(noSuchMethodException);
    }
  }

  @Override
  public IContainer<Object> getStoredPublicStaticFieldValuesOfClass(final Class<?> paramClass) {

    final var publicStaticFields = LinkedList.createEmpty();

    //Iterates the fields of the given Class.
    for (final var f : paramClass.getDeclaredFields()) {

      //Handles the case that the current field is .
      if (FIELD_EXAMINER.isStatic(f) && ReflectionTool.isPublic(f)) {
        try {
          publicStaticFields.addAtEnd(f.get(null));
        } catch (final IllegalAccessException illegalAccessException) {
          throw WrapperException.forError(illegalAccessException);
        }
      }
    }

    return publicStaticFields;
  }
}
