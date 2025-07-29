package ch.nolix.system.webgui.atomiccontrol.button;

import ch.nolix.core.web.htmlelementmodel.HtmlElement;
import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.coreapi.web.htmlelementmodel.IHtmlElement;
import ch.nolix.systemapi.webgui.atomiccontrol.buttonapi.IButton;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;

public final class ButtonHtmlBuilder implements IControlHtmlBuilder<IButton> {

  @Override
  public IHtmlElement createHtmlElementForControl(final IButton button) {
    return HtmlElement.withTypeAndInnerText(
      HtmlElementTypeCatalog.BUTTON,
      button.getText());
  }
}
