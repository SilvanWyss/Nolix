package ch.nolix.systemtest.webgui.atomiccontrol.validationlabel;

import ch.nolix.system.webgui.atomiccontrol.validationlabel.ValidationLabel;
import ch.nolix.system.webgui.atomiccontrol.validationlabel.ValidationLabelHtmlBuilder;
import ch.nolix.systemapi.webgui.atomiccontrol.validationlabelapi.IValidationLabel;
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
