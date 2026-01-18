/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapitest.attributeapi.mandatoryattributeapi;

import ch.nolix.coreapi.attribute.mandatoryattribute.ITitleHolder;

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
