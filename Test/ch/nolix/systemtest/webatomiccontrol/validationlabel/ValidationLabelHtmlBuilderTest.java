/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.webatomiccontrol.validationlabel;

import ch.nolix.system.webatomiccontrol.validationlabel.ValidationLabel;
import ch.nolix.system.webatomiccontrol.validationlabel.ValidationLabelHtmlBuilder;
import ch.nolix.systemapi.webatomiccontrol.validationlabel.IValidationLabel;
import ch.nolix.systemtest.webgui.basecontroltool.ControlHtmlBuilderTest;

/**
 * @author Silvan Wyss
 */
final class ValidationLabelHtmlBuilderTest
extends ControlHtmlBuilderTest<ValidationLabelHtmlBuilder, IValidationLabel> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected IValidationLabel createControl() {
    return new ValidationLabel();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected ValidationLabelHtmlBuilder createTestUnit() {
    return new ValidationLabelHtmlBuilder();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl() {
    return "<div>\u2800</div>";
  }
}
