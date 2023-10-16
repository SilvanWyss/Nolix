//package declaration
package ch.nolix.core.provider.implprovider;

//Java imports
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

//own imports
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.providerapi.implproviderapi.IImplProviderMediator;

//class
public final class SingleImplProvider<IM> implements IImplProviderMediator<IM> {

  //attribute
  private final Class<IM> implementation;

  //constructor
  SingleImplProvider(final Class<IM> implementation) {

    GlobalValidator.assertThat(implementation).thatIsNamed("implementation").isNotNull();

    this.implementation = implementation;
  }

  //method
  @Override
  public IM createInstance() {
    try {
      return getDefaultConstructor().newInstance();
    } catch (final
        InstantiationException
        | IllegalAccessException
        | InvocationTargetException exception) {
      throw WrapperException.forError(exception);
    }
  }

  //method
  @Override
  public IM createInstance(final Object... arguments) {
    try {
      return getConstructorFor(arguments).newInstance(arguments);
    } catch (final
        InstantiationException
        | IllegalAccessException
        | InvocationTargetException exception) {
      throw WrapperException.forError(exception);
    }
  }

  //method
  private Constructor<IM> getConstructorFor(final Object... arguments) {
    try {

      final var constructor = implementation.getDeclaredConstructor(getParameterTypes(arguments));
      constructor.trySetAccessible();

      return constructor;
    } catch (final NoSuchMethodException noSuchMethodException) {
      throw WrapperException.forError(noSuchMethodException);
    }
  }

  //method
  private Constructor<IM> getDefaultConstructor() {
    try {

      final var defaultConstructor = implementation.getDeclaredConstructor();
      defaultConstructor.trySetAccessible();

      return defaultConstructor;
    } catch (final NoSuchMethodException noSuchMethodException) {
      throw WrapperException.forError(noSuchMethodException);
    }
  }

  //method
  private Class<?>[] getParameterTypes(final Object[] objects) {

    final var parameterTypes = new Class<?>[objects.length];

    for (var i = 0; i < objects.length; i++) {
      parameterTypes[i] = objects[i].getClass();
    }

    return parameterTypes;
  }
}
