package ch.nolix.system.webgui.atomiccontrol.label;

import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalog;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.labelapi.ILabel;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;

public final class LabelHtmlBuilder implements IControlHtmlBuilder<ILabel> {

  @Override
  public IHtmlElement createHtmlElementForControl(final ILabel control) {
    return HtmlElement.withTypeAndInnerText(
      HtmlElementTypeCatalog.DIV,
      control.getText());
  }
}
