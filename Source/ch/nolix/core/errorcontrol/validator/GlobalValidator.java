//package declaration
package ch.nolix.core.errorcontrol.validator;

//Java imports
import java.lang.reflect.Method;
import java.math.BigDecimal;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.independent.container.List;
import ch.nolix.core.independent.containertool.GlobalArrayTool;

//class
/**
 * The {@link GlobalValidator} provides methods to validate arguments. Of the
 * {@link GlobalValidator} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2016-12-01
 */
public final class GlobalValidator { //NOSONAR: The GlobalValidator bundles all validation methods.

  //constructor
  /**
   * Prevents that an instance of the {@link GlobalValidator} can be created.
   */
  private GlobalValidator() {
  }

  //static method
  /**
   * @param argument
   * @param <A>      is the type of the given argument.
   * @return a new {@link ExtendedArgumentMediator} for given argument.
   */
  public static <A> ExtendedArgumentMediator<A> assertThat(final A argument) {
    return new ExtendedArgumentMediator<>(argument);
  }

  //static method
  /**
   * @param argument
   * @return a new {@link ExtendedBigDecimalMediator} for the given argument.
   */
  public static ExtendedBigDecimalMediator assertThat(final BigDecimal argument) {
    return new ExtendedBigDecimalMediator(argument);
  }

  //static method
  /**
   * @param argument
   * @return a new {@link ExtendedByteMediator} for the given argument.
   */
  public static ExtendedByteMediator assertThat(final byte argument) {
    return new ExtendedByteMediator(argument);
  }

  //static method
  /**
   * @param argument
   * @return a new {@link ExtendedContainerMediator} for the given argument.
   */
  public static ExtendedContainerMediator<Byte> assertThat(final byte[] argument) {
    return new ExtendedContainerMediator<>(GlobalArrayTool.createIterable(argument));
  }

  //static method
  /**
   * @param argument
   * @param <T>      is the type of the given argument
   * @return a new {@link ExtendedTypeMediator} for the given argument.
   */
  public static <T> ExtendedTypeMediator<T> assertThat(final Class<T> argument) {
    return ExtendedTypeMediator.forArgument(argument);
  }

  //static method
  /**
   * @param argument
   * @return a new {@link ExtendedDoubleMediator} for the given argument.
   */
  public static ExtendedDoubleMediator assertThat(final double argument) {
    return new ExtendedDoubleMediator(argument);
  }

  //static method
  /**
   * @param argument
   * @return a new {@link ExtendedContainerMediator} for the given argument.
   */
  public static ExtendedContainerMediator<Double> assertThat(final double[] argument) {

    //Handles the case that the given argument is null.
    if (argument == null) {
      final List<Double> argumentVector = null;
      return new ExtendedContainerMediator<>(argumentVector);
    }

    //Handles the case that the given argument is not null.
    return new ExtendedContainerMediator<>(GlobalArrayTool.createIterable(argument));
  }

  //static method
  /**
   * @param argument
   * @return a new {@link ExtendedLongMediator} for the given argument.
   */
  public static ExtendedLongMediator assertThat(final int argument) {
    return ExtendedLongMediator.forArgument(argument);
  }

  //static method
  /**
   * @param argument
   * @return a new {@link ExtendedContainerMediator} for the given argument.
   */
  public static ExtendedContainerMediator<Long> assertThat(final int[] argument) {

    //Handles the case that the given argument is null.
    if (argument == null) {
      final List<Long> argumentVector = null;
      return new ExtendedContainerMediator<>(argumentVector);
    }

    //Handles the case that the given argument is not null.
    return new ExtendedContainerMediator<>(GlobalArrayTool.createIterable(argument));
  }

  //static method
  /**
   * @param argument
   * @param <A>      is the type of the elements of the given argument.
   * @return a new {@link ExtendedContainerMediator} for the given argument.
   */
  public static <A> ExtendedContainerMediator<A> assertThat(final Iterable<A> argument) {
    return new ExtendedContainerMediator<>(argument);
  }

  //static method
  /**
   * @param argument
   * @return a new {@link ExtendedLongMediator} for the given argument.
   */
  public static ExtendedLongMediator assertThat(final long argument) {
    return ExtendedLongMediator.forArgument(argument);
  }

  //static method
  /**
   * @param argument
   * @return a new {@link ExtendedContainerMediator} for the given argument.
   */
  public static ExtendedContainerMediator<Long> assertThat(final long[] argument) {

    //Handles the case that the given argument is null.
    if (argument == null) {
      final List<Long> argumentVector = null;
      return new ExtendedContainerMediator<>(argumentVector);
    }

    //Handles the case that the given argument is not null.
    return new ExtendedContainerMediator<>(GlobalArrayTool.createIterable(argument));
  }

  //static method
  /**
   * @param argument
   * @return a new {@link ExtendedMethodMediator} for the given argument.
   */
  public static ExtendedMethodMediator assertThat(final Method argument) {
    return new ExtendedMethodMediator(argument);
  }

  //static method
  /**
   * @param argument
   * @return a new {@link ExtendedStringMediator} for the given argument.
   */
  public static ExtendedStringMediator assertThat(final String argument) {
    return ExtendedStringMediator.forArgument(argument);
  }

  //static method
  /**
   * @param argument
   * @return a new {@link ExtendedBitMediator} for the given argument.
   */
  public static ExtendedBitMediator assertThatTheBit(final boolean argument) {
    return new ExtendedBitMediator(argument);
  }

  //static method
  /**
   * @param arguments
   * @return a new {@link MultiDoubleMediator} for the given arguments.
   * @throws ArgumentIsNullException if the given arguments is null.
   */
  public static MultiDoubleMediator assertThatTheDoubles(final Iterable<Double> arguments) {
    return new MultiDoubleMediator(arguments);
  }

  //static method
  /**
   * @param arguments
   * @param <E>       is the type of the given arguments.
   * @return a new {@link MultiArgumentMediator} for the given arguments.
   * @throws ArgumentIsNullException if the given arguments is null.
   */
  public static <E> MultiArgumentMediator<E> assertThatTheElements(final E[] arguments) {
    return new MultiArgumentMediator<>(arguments);
  }

  //static method
  /**
   * @param arguments
   * @param <E>       is the type of the given arguments.
   * @return a new {@link MultiArgumentMediator} for the given arguments.
   * @throws ArgumentIsNullException if the given arguments is null.
   */
  public static <E> MultiArgumentMediator<E> assertThatTheElements(final Iterable<E> arguments) {
    return new MultiArgumentMediator<>(arguments);
  }

  //static method
  /**
   * @param arguments
   * @return a new {@link MultiLongMediator} for the given arguments.
   * @throws ArgumentIsNullException if the given arguments is null.
   */
  public static MultiLongMediator assertThatTheLongs(final Iterable<Long> arguments) {
    return new MultiLongMediator(arguments);
  }

  //static method
  /**
   * @param arguments
   * @return a new {@link MultiStringMediator} for the given arguments.
   * @throws ArgumentIsNullException if the given arguments is null.
   */
  public static MultiStringMediator assertThatTheStrings(final Iterable<String> arguments) {
    return new MultiStringMediator(arguments);
  }
}
