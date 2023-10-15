//package declaration
package ch.nolix.systemtest.webguitest.atomiccontroltest;

import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
//own imports
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.system.webgui.atomiccontrol.Button;
import ch.nolix.system.webgui.atomiccontrol.ButtonHtmlBuilder;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IButton;
import ch.nolix.systemtest.webguitest.basecontrolservicetest.ControlHtmlBuilderTest;

//class
public final class ButtonHtmlBuilderTest extends ControlHtmlBuilderTest<ButtonHtmlBuilder, IButton> {

  // method
  @Override
  protected IButton createControl() {
    return new Button();
  }

  // method
  @Override
  protected ButtonHtmlBuilder createTestUnit() {
    return new ButtonHtmlBuilder();
  }

  // method
  @Override
  protected void expectSpecificPropertiesOnHtmlElementCreatedOfNewControl(final IHtmlElement htmlElement) {
    expect(htmlElement.getType()).isEqualTo(HtmlElementTypeCatalogue.BUTTON);
    expect(htmlElement.getInnerText()).isEqualTo(Button.DEFAULT_TEXT);
  }
}
