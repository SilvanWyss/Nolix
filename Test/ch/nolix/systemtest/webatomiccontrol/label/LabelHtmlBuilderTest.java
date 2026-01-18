package ch.nolix.systemtest.webatomiccontrol.label;

import ch.nolix.system.webatomiccontrol.label.Label;
import ch.nolix.system.webatomiccontrol.label.LabelHtmlBuilder;
import ch.nolix.systemapi.webatomiccontrol.label.ILabel;
import ch.nolix.systemtest.webgui.basecontroltool.ControlHtmlBuilderTest;

/**
 * @author Silvan Wyss
 */
final class LabelHtmlBuilderTest extends ControlHtmlBuilderTest<LabelHtmlBuilder, ILabel> {
  @Override
  protected ILabel createControl() {
    return new Label();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected LabelHtmlBuilder createTestUnit() {
    return new LabelHtmlBuilder();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl() {
    return "<div>-</div>";
  }
}
