/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.attributeapi.mandatoryattributeapi;

import ch.nolix.baseapi.attribute.mandatoryattribute.ITitleHolder;

/**
 * @author Silvan Wyss
 */
public final class MockTitleHolder implements ITitleHolder {
  private final String title;

  private MockTitleHolder(final String title) {
    this.title = title;
  }

  public static MockTitleHolder withTitle(final String title) {
    return new MockTitleHolder(title);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getTitle() {
    return title;
  }
}
