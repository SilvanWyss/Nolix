/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.atomiccontrol.imagecontrol;

import ch.nolix.system.atomiccontrol.imagecontrol.ImageControl;
import ch.nolix.system.atomiccontrol.imagecontrol.ImageControlHtmlBuilder;
import ch.nolix.systemapi.atomiccontrol.imagecontrol.IImageControl;
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
