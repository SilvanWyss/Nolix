//package declaration
package ch.nolix.system.webgui.atomiccontrol;

//own imports
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IValidationLabel;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;

//class
public final class ValidationLabelHtmlBuilder implements IControlHtmlBuilder<IValidationLabel> {

  //method
  @Override
  public IHtmlElement createHtmlElementForControl(final IValidationLabel control) {
    return HtmlElement.withTypeAndInnerText(
      HtmlElementTypeCatalogue.DIV,
      getHtmlDivInnerTextForControl(control));
  }

  //method
  private String getHtmlDivInnerTextForControl(final IValidationLabel control) {

    if (control.isEmpty()) {
      return "\u2800";
    }

    return (control.getError().getMessage() + "\u2800");
  }
}
