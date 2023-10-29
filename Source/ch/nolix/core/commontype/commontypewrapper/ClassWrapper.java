//package declaration
package ch.nolix.core.commontype.commontypewrapper;

//Java imports
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

//own imports
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
public final class ClassWrapper<T> {

  //attribute
  private final Class<T> mClass;

  //constructor
  public ClassWrapper(final Class<T> pClass) {

    GlobalValidator.assertThat(pClass).thatIsNamed(LowerCaseCatalogue.CLASS).isNotNull();

    mClass = pClass;
  }

  //method
  public T createInstance() {
    try {
      return getConstructor().newInstance();
    } catch (final
    InstantiationException
    | IllegalAccessException
    | IllegalArgumentException
    | InvocationTargetException exception) {
      throw WrapperException.forError(exception);
    }
  }

  //method
  public Constructor<T> getConstructor() {
    try {
      return mClass.getConstructor();
    } catch (final NoSuchMethodException | SecurityException exception) {
      throw WrapperException.forError(exception);
    }
  }
}
