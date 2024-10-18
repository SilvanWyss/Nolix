package ch.nolix.systemtest.webguitest.atomiccontroltest;

import ch.nolix.system.webgui.atomiccontrol.ValidationLabel;
import ch.nolix.system.webgui.atomiccontrol.ValidationLabelHtmlBuilder;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IValidationLabel;
import ch.nolix.systemtest.webguitest.basecontroltooltest.ControlHtmlBuilderTest;

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
