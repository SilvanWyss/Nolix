//package declaration
package ch.nolix.core.programstructure.caching;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

//class
public final class CachingProperty<V> {

  //attribute
  private final Supplier<V> valueCreator;

  //optional attribute
  private final BooleanSupplier needToRefreshFunction;

  //optional attribute
  private V value;

  //constructor
  public CachingProperty(final Supplier<V> valueCreator) {

    GlobalValidator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();

    this.valueCreator = valueCreator;
    needToRefreshFunction = null;
  }

  //constructor
  public CachingProperty(final Supplier<V> valueCreator,
      final java.util.function.BooleanSupplier needToRefreshFunction) {

    GlobalValidator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
    GlobalValidator.assertThat(needToRefreshFunction).thatIsNamed("need-to-refresh-function").isNotNull();

    this.valueCreator = valueCreator;
    this.needToRefreshFunction = needToRefreshFunction;
  }

  //method
  public V getValue() {

    refreshIfNeeded();

    return value;
  }

  //method
  private void refresh() {
    value = valueCreator.get();
  }

  //method
  private void refreshIfNeeded() {
    if (refreshIsNeeded()) {
      refresh();
    }
  }

  //method
  private boolean refreshIsNeeded() {
    return (value == null || (needToRefreshFunction != null && needToRefreshFunction.getAsBoolean()));
  }
}
