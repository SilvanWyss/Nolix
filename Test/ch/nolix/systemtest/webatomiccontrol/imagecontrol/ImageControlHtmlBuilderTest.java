/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.webatomiccontrol.imagecontrol;

import ch.nolix.system.webatomiccontrol.imagecontrol.ImageControl;
import ch.nolix.system.webatomiccontrol.imagecontrol.ImageControlHtmlBuilder;
import ch.nolix.systemapi.webatomiccontrol.imagecontrol.IImageControl;
import ch.nolix.systemtest.webgui.basecontroltool.ControlHtmlBuilderTest;

/**
 * @author Silvan Wyss
 */
final class ImageControlHtmlBuilderTest extends ControlHtmlBuilderTest<ImageControlHtmlBuilder, IImageControl> {
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
