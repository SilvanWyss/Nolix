package ch.nolix.systemtest.webgui.atomiccontrol.imagecontrol;

import ch.nolix.system.webgui.atomiccontrol.imagecontrol.ImageControl;
import ch.nolix.system.webgui.atomiccontrol.imagecontrol.ImageControlHtmlBuilder;
import ch.nolix.systemapi.webgui.atomiccontrol.imagecontrolapi.IImageControl;
import ch.nolix.systemtest.webgui.basecontroltool.ControlHtmlBuilderTest;

final class ImageControlHtmlBuilderTest extends ControlHtmlBuilderTest<ImageControlHtmlBuilder, IImageControl> {
  @Override
  protected IImageControl createControl() {
    return new ImageControl();
  }

  @Override
  protected ImageControlHtmlBuilder createTestUnit() {
    return new ImageControlHtmlBuilder();
  }

  @Override
  protected String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl() {
    return "<img alt=\"\" />";
  }
}
