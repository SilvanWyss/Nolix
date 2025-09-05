package ch.nolix.systemtest.webgui.atomiccontrol.label;

import ch.nolix.system.webgui.atomiccontrol.label.Label;
import ch.nolix.system.webgui.atomiccontrol.label.LabelHtmlBuilder;
import ch.nolix.systemapi.webgui.atomiccontrol.labelapi.ILabel;
import ch.nolix.systemtest.webgui.basecontroltool.ControlHtmlBuilderTest;

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
