//package declaration
package ch.nolix.systemtest.webguitest.atomiccontroltest;

//own imports
import ch.nolix.system.webgui.atomiccontrol.ValidationLabel;
import ch.nolix.system.webgui.atomiccontrol.ValidationLabelHtmlBuilder;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IValidationLabel;
import ch.nolix.systemtest.webguitest.basecontroltooltest.ControlHtmlBuilderTest;

//class
final class ValidationLabelHtmlBuilderTest
extends ControlHtmlBuilderTest<ValidationLabelHtmlBuilder, IValidationLabel> {

  //method
  @Override
  protected IValidationLabel createControl() {
    return new ValidationLabel();
  }

  //method
  @Override
  protected ValidationLabelHtmlBuilder createTestUnit() {
    return new ValidationLabelHtmlBuilder();
  }

  //method
  @Override
  protected String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl() {
    return "<div>\u2800</div>";
  }
}
