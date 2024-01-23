//package declaration
package ch.nolix.core.reflection;

//Java imports
import java.lang.reflect.Constructor;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//class
public final class GlobalClassTool {

  //constructor
  private GlobalClassTool() {
  }

  //static method
  public static <T> T createInstanceFromDefaultConstructorOf(final Class<T> paramClass) {
    return GlobalConstructorTool.createInstanceFromDefaultConstructor(getDefaultConstructorOfClass(paramClass));
  }

  //static method
  public static <T> Constructor<T> getDefaultConstructorOfClass(final Class<T> paramClass) {
    try {

      final var defaultConstructor = paramClass.getDeclaredConstructor();

      defaultConstructor.setAccessible(true);

      return defaultConstructor;
    } catch (final NoSuchMethodException noSuchMethodException) {
      throw WrapperException.forError(noSuchMethodException);
    }
  }

  //static method
  public static IContainer<Object> getPublicStaticFieldValuesOfClass(final Class<?> paramClass) {

    final var publicStaticFields = new LinkedList<>();

    //Iterates the fields of the given Class.
    for (final var f : paramClass.getDeclaredFields()) {

      //Handles the case that the current field is static.
      if (GlobalFieldTool.isStatic(f) && GlobalMemberTool.isPublic(f)) {
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
