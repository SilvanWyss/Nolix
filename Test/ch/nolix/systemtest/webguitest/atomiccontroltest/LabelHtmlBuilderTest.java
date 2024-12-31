package ch.nolix.systemtest.webguitest.atomiccontroltest;

import ch.nolix.system.webgui.atomiccontrol.label.Label;
import ch.nolix.system.webgui.atomiccontrol.label.LabelHtmlBuilder;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.labelapi.ILabel;
import ch.nolix.systemtest.webguitest.basecontroltooltest.ControlHtmlBuilderTest;

final class LabelHtmlBuilderTest extends ControlHtmlBuilderTest<LabelHtmlBuilder, ILabel> {

  @Override
  protected ILabel createControl() {
    return new Label();
  }

  @Override
  protected LabelHtmlBuilder createTestUnit() {
    return new LabelHtmlBuilder();
  }

  @Override
  protected String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl() {
    return "<div>-</div>";
  }
}
