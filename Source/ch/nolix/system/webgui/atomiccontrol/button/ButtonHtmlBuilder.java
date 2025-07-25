package ch.nolix.system.webgui.atomiccontrol.button;

import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.coreapi.web.html.IHtmlElement;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.buttonapi.IButton;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;

public final class ButtonHtmlBuilder implements IControlHtmlBuilder<IButton> {

  @Override
  public IHtmlElement createHtmlElementForControl(final IButton button) {
    return HtmlElement.withTypeAndInnerText(
      HtmlElementTypeCatalog.BUTTON,
      button.getText());
  }
}
