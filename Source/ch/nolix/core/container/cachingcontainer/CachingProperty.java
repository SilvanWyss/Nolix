package ch.nolix.core.container.cachingcontainer;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import ch.nolix.core.errorcontrol.validator.Validator;

public final class CachingProperty<V> {

  private final Supplier<V> valueCreator;

  private final BooleanSupplier needToRefreshFunction;

  private V value;

  public CachingProperty(final Supplier<V> valueCreator) {

    Validator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();

    this.valueCreator = valueCreator;
    needToRefreshFunction = null;
  }

  public CachingProperty(final Supplier<V> valueCreator, final BooleanSupplier needToRefreshFunction) {

    Validator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
    Validator.assertThat(needToRefreshFunction).thatIsNamed("need-to-refresh-function").isNotNull();

    this.valueCreator = valueCreator;
    this.needToRefreshFunction = needToRefreshFunction;
  }

  public V getValue() {

    refreshIfNeeded();

    return value;
  }

  private void refresh() {
    value = valueCreator.get();
  }

  private void refreshIfNeeded() {
    if (refreshIsNeeded()) {
      refresh();
    }
  }

  private boolean refreshIsNeeded() {
    return (value == null || (needToRefreshFunction != null && needToRefreshFunction.getAsBoolean()));
  }
}
