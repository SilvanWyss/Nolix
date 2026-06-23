/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.control.imagecontrol;

import ch.nolix.system.control.imagecontrol.ImageControl;
import ch.nolix.system.control.imagecontrol.ImageControlHtmlBuilder;
import ch.nolix.systemapi.control.imagecontrol.IImageControl;
import ch.nolix.systemtest.webgui.basecontroltool.ControlHtmlBuilderTest;

/**
 * @author Silvan Wyss
 */
final class ImageControlHtmlBuilderTest extends ControlHtmlBuilderTest<ImageControlHtmlBuilder, IImageControl> {
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
  protected ImageControlHtmlBuilder createTestUnit() {
    return new ImageControlHtmlBuilder();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl() {
    return "<img alt=\"\" />";
  }
}
