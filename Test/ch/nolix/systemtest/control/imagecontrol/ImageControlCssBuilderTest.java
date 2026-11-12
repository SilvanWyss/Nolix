/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.control.imagecontrol;

import ch.nolix.system.control.imagecontrol.ImageControl;
import ch.nolix.system.control.imagecontrol.ImageControlCssBuilder;
import ch.nolix.systemapi.control.imagecontrol.IImageControl;
import ch.nolix.systemtest.webgui.basecontroltool.ControlCssBuilderTest;

/**
 * @author Silvan Wyss
 */
final class ImageControlCssBuilderTest extends ControlCssBuilderTest<ImageControlCssBuilder, IImageControl> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected IImageControl createControl() {
    return new ImageControl();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected ImageControlCssBuilder createTestUnit() {
    return new ImageControlCssBuilder();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected int getExpectedCssRuleCountForNewControl() {
    return 4;
  }
}
