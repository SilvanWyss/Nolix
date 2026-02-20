/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.attributeapi.mandatoryattributeapi;

import ch.nolix.baseapi.attribute.mandatoryattribute.INameHolder;

/**
 * @author Silvan Wyss
 */
public final class MockNameHolder implements INameHolder {
  private final String name;

  private MockNameHolder(final String name) {
    this.name = name;
  }

  public static MockNameHolder withName(final String name) {
    return new MockNameHolder(name);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return name;
  }
}
