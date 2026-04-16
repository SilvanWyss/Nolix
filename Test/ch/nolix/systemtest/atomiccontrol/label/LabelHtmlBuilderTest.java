/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.atomiccontrol.label;

import ch.nolix.system.atomiccontrol.label.Label;
import ch.nolix.system.atomiccontrol.label.LabelHtmlBuilder;
import ch.nolix.systemapi.atomiccontrol.label.ILabel;
import ch.nolix.systemtest.webgui.basecontroltool.ControlHtmlBuilderTest;

/**
 * @author Silvan Wyss
 */
final class LabelHtmlBuilderTest extends ControlHtmlBuilderTest<LabelHtmlBuilder, ILabel> {
  /**
   * {@inheritDoc}
   */
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
