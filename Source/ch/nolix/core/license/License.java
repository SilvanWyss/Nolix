package ch.nolix.core.license;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnacceptedKeyException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.StringCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @version 2017-05-16
 */
public abstract class License implements INameHolder {

  private boolean activated;

  /**
   * Activates the current {@link License} with the given key.
   * 
   * @param key
   * @throws InvalidArgumentException if the current {@link License} is already
   *                                  activated.
   * @throws UnacceptedKeyException   if the current {@link License} does no
   *                                  accepts the given key.
   */
  public final void activate(final String key) {

    assertIsNotActivated();
    assertAccepts(key);

    activated = true;
  }

  /**
   * @throws InvalidArgumentException if the current {@link License} is not
   *                                  activated.
   */
  public final void assetIsActivated() {
    if (!isActivated()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not actiaved");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getName() {
    return getClass().getName();
  }

  /**
   * @return true if the current {@link License} is activated.
   */
  public final boolean isActivated() {
    return activated;
  }

  /**
   * @param filteredKey
   * @return true if the current {@link License} accepts the given filteredKey.
   */
  protected abstract boolean acceptsFilteredKey(String filteredKey);

  /**
   * @param key
   * @return true if the current {@link License} accepts the given key.
   */
  private boolean accepts(final String key) {
    return acceptsFilteredKey(getFilteredKey(key));
  }

  /**
   * @param key
   * @throws UnacceptedKeyException if the current {@link License} does no accepts
   *                                the given key.
   */
  private void assertAccepts(final String key) {
    if (!accepts(key)) {
      throw UnacceptedKeyException.forKey(key);
    }
  }

  /**
   * @throws InvalidArgumentException if the current {@link License} is activated.
   */
  private void assertIsNotActivated() {
    if (isActivated()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is actiaved");
    }
  }

  /**
   * @param key
   * @return a filtered key for the given key.
   * @throws ArgumentIsNullException if the given key is null.
   */
  private String getFilteredKey(final String key) {

    Validator.assertThat(key).thatIsNamed(LowerCaseVariableCatalog.KEY).isNotNull();

    return key
      .replace(StringCatalog.MINUS, StringCatalog.EMPTY_STRING)
      .replace(StringCatalog.SPACE, StringCatalog.EMPTY_STRING)
      .replace(StringCatalog.TABULATOR, StringCatalog.EMPTY_STRING);
  }
}
