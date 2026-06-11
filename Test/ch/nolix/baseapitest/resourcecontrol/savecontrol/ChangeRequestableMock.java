/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.resourcecontrol.savecontrol;

import ch.nolix.baseapi.resourcecontrol.savecontrol.ChangeRequestable;

/**
 * @author Silvan Wyss
 */
public final class ChangeRequestableMock implements ChangeRequestable {
  private final boolean hasChanges;

  private ChangeRequestableMock(final boolean hasChanges) {
    this.hasChanges = hasChanges;
  }

  public static ChangeRequestableMock withHasChangesFlag(final boolean hasChanges) {
    return new ChangeRequestableMock(hasChanges);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasChanges() {
    return hasChanges;
  }
}
