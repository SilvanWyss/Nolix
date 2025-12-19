package ch.nolix.core.environment.license;

import ch.nolix.core.errorcontrol.invalidargumentexception.UnacceptedKeyException;
import ch.nolix.coreapi.environment.licenseapi.ILicense;
import ch.nolix.coreapi.environment.licenseapi.ILicenseValidator;

/**
 * @author Silvan Wyss
 */
public abstract class License //NOSONAR: A license class is expected to be abstract.
implements ILicense {
  private static final ILicenseValidator LICENSE_VALIDATOR = new LicenseValidator();

  private boolean activated;

  /**
   * {@inheritDoc}
   */
  @Override
  public final void activateWithKey(final String key) {
    LICENSE_VALIDATOR.assertIsNotActivated(this);
    assertAcceptsKey(key);

    activated = true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getName() {
    return getClass().getName();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isActivated() {
    return activated;
  }

  /**
   * @param refinedKey
   * @return true if the current {@link License} accepts the given refinedKey,
   *         false otherwise.
   */
  protected abstract boolean acceptsRefinedKey(String refinedKey);

  /**
   * @param key
   * @return true if the current {@link License} accepts the given key, false
   *         otherwise.
   */
  private boolean acceptsKey(final String key) {
    final var refinedKey = KeyRefinder.getRefinedKeyFromKey(key);

    return acceptsRefinedKey(refinedKey);
  }

  /**
   * @param key
   * @throws UnacceptedKeyException if the current {@link License} does no accepts
   *                                the given key.
   */
  private void assertAcceptsKey(final String key) {
    if (!acceptsKey(key)) {
      throw UnacceptedKeyException.forKey(key);
    }
  }
}
