package ch.nolix.system.webgui.atomiccontrol.validationlabel;

import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalog;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.validationlabelapi.IValidationLabel;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;

public final class ValidationLabelHtmlBuilder implements IControlHtmlBuilder<IValidationLabel> {

  @Override
  public IHtmlElement createHtmlElementForControl(final IValidationLabel control) {
    return HtmlElement.withTypeAndInnerText(
      HtmlElementTypeCatalog.DIV,
      getHtmlDivInnerTextForControl(control));
  }

  private String getHtmlDivInnerTextForControl(final IValidationLabel control) {

    if (control.isEmpty()) {
      return "\u2800";
    }

    return (control.getError().getMessage() + "\u2800");
  }
}
