package ch.nolix.core.datastructure.property;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programatom.function.FunctionService;
import ch.nolix.coreapi.datastructure.property.ILazyCalculatedProperty;

/**
 * @author Silvan Wyss
 * @version 2025-07-07
 * @param <V> is the type of the value of a {@link LazyCalculatedProperty}.
 */
public final class LazyCalculatedProperty<V> implements ILazyCalculatedProperty<V> {

  private final Supplier<V> valueCreator;

  private final BooleanSupplier needToUpdateSupplier;

  private V value;

  /**
   * Creates a new {@link LazyCalculatedProperty} with the given valueCreator and
   * needToUpdateSupplier.
   * 
   * @param valueCreator
   * @param needToUpdateSupplier
   * @throws ArgumentIsNullException if the given valueCreator is null.
   * @throws ArgumentIsNullException if the given needToUpdateSupplier is null.
   */
  private LazyCalculatedProperty(final Supplier<V> valueCreator, final BooleanSupplier needToUpdateSupplier) {

    Validator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
    Validator.assertThat(needToUpdateSupplier).thatIsNamed("need-to-update-supplier").isNotNull();

    this.valueCreator = valueCreator;
    this.needToUpdateSupplier = needToUpdateSupplier;
  }

  /**
   * @param valueCreator
   * @param needToUpdateSupplier
   * @param <V2>                 is the type of the value of the created
   *                             {@link LazyCalculatedProperty}.
   * @return a new {@link LazyCalculatedProperty} with the given valueCreator and
   *         needToUpdateSupplier.
   * @throws ArgumentIsNullException if the given valueCreator is null.
   * @throws ArgumentIsNullException if the given needToUpdateSupplier is null.
   */
  public static <V2> LazyCalculatedProperty<V2> forValueCreaterAndNeedToUpdateSupplier(
    final Supplier<V2> valueCreator,
    final BooleanSupplier needToUpdateSupplier) {
    return new LazyCalculatedProperty<>(valueCreator, needToUpdateSupplier);
  }

  /**
   * @param valueCreator
   * @param <V2>         is the type of the value of the created
   *                     {@link LazyCalculatedProperty}.
   * @return a new {@link LazyCalculatedProperty} with the given valueCreator.
   * @throws ArgumentIsNullException if the given valueCreator is null.
   */
  public static <V2> LazyCalculatedProperty<V2> forValueCreater(final Supplier<V2> valueCreator) {

    final BooleanSupplier needToUpdateSupplier = FunctionService::getTrue;

    return new LazyCalculatedProperty<>(valueCreator, needToUpdateSupplier);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public V getStoredValue() {

    updateIfRequired();

    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isUpToDate() {
    return !needToUpdateSupplier.getAsBoolean();
  }

  /**
   * Updates the current {@link LazyCalculatedProperty} if required.
   */
  private void updateIfRequired() {
    if (updateIsRequired()) {
      value = valueCreator.get();
    }
  }

  /**
   * @return true if the current {@link LazyCalculatedProperty} is required to
   *         update, false otherwise.
   */
  private boolean updateIsRequired() {
    return //
    value == null
    || isOutOfDate();
  }
}
