/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.environment.license;

import ch.nolix.baseapi.environment.license.License;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.UnacceptedKeyException;

/**
 * @author Silvan Wyss
 */
public abstract class AbstractLicense implements License {
  private static final LicenseValidator LICENSE_VALIDATOR = new LicenseValidator();

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
   * @return true if the current {@link AbstractLicense} accepts the given
   *         refinedKey, false otherwise
   */
  protected abstract boolean acceptsRefinedKey(String refinedKey);

  /**
   * @param key
   * @return true if the current {@link AbstractLicense} accepts the given key,
   *         false otherwise
   */
  private boolean acceptsKey(final String key) {
    final var refinedKey = KeyRefinder.getRefinedKeyFromKey(key);

    return acceptsRefinedKey(refinedKey);
  }

  /**
   * @param key
   * @throws UnacceptedKeyException if the current {@link AbstractLicense} does no
   *                                accepts the given key.
   */
  private void assertAcceptsKey(final String key) {
    if (!acceptsKey(key)) {
      throw UnacceptedKeyException.forKey(key);
    }
  }
}
