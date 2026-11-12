/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.control.validationlabel;

import ch.nolix.system.control.validationlabel.ValidationLabel;
import ch.nolix.system.control.validationlabel.ValidationLabelCssBuilder;
import ch.nolix.systemapi.control.validationlabel.IValidationLabel;
import ch.nolix.systemtest.webgui.basecontroltool.ControlCssBuilderTest;

/**
 * @author Silvan Wyss
 */
final class ValidationLabelCssBuilderTest extends ControlCssBuilderTest<ValidationLabelCssBuilder, IValidationLabel> {
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
  protected ValidationLabelCssBuilder createTestUnit() {
    return new ValidationLabelCssBuilder();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected int getExpectedCssRuleCountForNewControl() {
    return 4;
  }
}
