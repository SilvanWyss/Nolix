/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.control.label;

import ch.nolix.system.control.label.Label;
import ch.nolix.system.control.label.LabelCssBuilder;
import ch.nolix.systemapi.control.label.ILabel;
import ch.nolix.systemtest.webgui.basecontroltool.ControlCssBuilderTest;

/**
 * @author Silvan Wyss
 */
final class LabelCssBuilderTest extends ControlCssBuilderTest<LabelCssBuilder, ILabel> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected ILabel createControl() {
    return new Label();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected LabelCssBuilder createTestUnit() {
    return new LabelCssBuilder();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected int getExpectedCssRuleCountForNewControl() {
    return 4;
  }
}
