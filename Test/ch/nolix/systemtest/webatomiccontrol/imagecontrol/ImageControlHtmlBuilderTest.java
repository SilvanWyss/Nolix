package ch.nolix.systemtest.webatomiccontrol.imagecontrol;

import ch.nolix.system.webatomiccontrol.imagecontrol.ImageControl;
import ch.nolix.system.webatomiccontrol.imagecontrol.ImageControlHtmlBuilder;
import ch.nolix.systemapi.webatomiccontrol.imagecontrol.IImageControl;
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
