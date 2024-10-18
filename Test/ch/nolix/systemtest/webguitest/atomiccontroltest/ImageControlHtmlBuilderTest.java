package ch.nolix.systemtest.webguitest.atomiccontroltest;

import ch.nolix.system.webgui.atomiccontrol.ImageControl;
import ch.nolix.system.webgui.atomiccontrol.ImageControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IImageControl;
import ch.nolix.systemtest.webguitest.basecontroltooltest.ControlHtmlBuilderTest;

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
    return "<img />";
  }
}
