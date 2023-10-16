//package declaration
package ch.nolix.core.reflection;

//Java imports
import java.lang.reflect.Constructor;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//class
public final class GlobalClassHelper {

  //static method
  public static <T> T createInstanceFromDefaultConstructorOf(final Class<T> pClass) {
    return GlobalConstructorHelper.createInstanceFromDefaultConstructor(getDefaultConstructorOfClass(pClass));
  }

  //static method
  public static <T> Constructor<T> getDefaultConstructorOfClass(final Class<T> pClass) {
    try {

      final var defaultConstructor = pClass.getDeclaredConstructor();

      defaultConstructor.setAccessible(true);

      return defaultConstructor;
    } catch (final NoSuchMethodException noSuchMethodException) {
      throw WrapperException.forError(noSuchMethodException);
    }
  }

  //static method
  public static IContainer<Object> getPublicStaticFieldValuesOfClass(final Class<?> pClass) {

    final var publicStaticFields = new LinkedList<>();

    //Iterates the fields of the given Class.
    for (final var f : pClass.getDeclaredFields()) {

      //Handles the case that the current field is static.
      if (GlobalFieldHelper.isStatic(f) && GlobalMemberHelper.isPublic(f)) {
        try {
          publicStaticFields.addAtEnd(f.get(null));
        } catch (final IllegalAccessException illegalAccessException) {
          throw WrapperException.forError(illegalAccessException);
        }
      }
    }

    return publicStaticFields;
  }

  //constructor
  private GlobalClassHelper() {
  }
}
