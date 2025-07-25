package ch.nolix.system.webgui.atomiccontrol.validationlabel;

import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.coreapi.web.html.IHtmlElement;
import ch.nolix.systemapi.webgui.atomiccontrol.validationlabelapi.IValidationLabel;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;

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
