package ch.nolix.systemtest.webatomiccontrol.validationlabel;

import ch.nolix.system.webatomiccontrol.validationlabel.ValidationLabel;
import ch.nolix.system.webatomiccontrol.validationlabel.ValidationLabelHtmlBuilder;
import ch.nolix.systemapi.webatomiccontrol.validationlabel.IValidationLabel;
import ch.nolix.systemtest.webgui.basecontroltool.ControlHtmlBuilderTest;

final class ValidationLabelHtmlBuilderTest
extends ControlHtmlBuilderTest<ValidationLabelHtmlBuilder, IValidationLabel> {
  @Override
  protected IValidationLabel createControl() {
    return new ValidationLabel();
  }

  @Override
  protected ValidationLabelHtmlBuilder createTestUnit() {
    return new ValidationLabelHtmlBuilder();
  }

  @Override
  protected String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl() {
    return "<div>\u2800</div>";
  }
}
