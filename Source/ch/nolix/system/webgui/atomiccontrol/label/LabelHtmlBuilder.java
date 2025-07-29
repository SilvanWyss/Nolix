package ch.nolix.system.webgui.atomiccontrol.label;

import ch.nolix.core.web.htmlelementmodel.HtmlElement;
import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.coreapi.web.htmlelementmodel.IHtmlElement;
import ch.nolix.systemapi.webgui.atomiccontrol.labelapi.ILabel;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;

public final class LabelHtmlBuilder implements IControlHtmlBuilder<ILabel> {

  @Override
  public IHtmlElement createHtmlElementForControl(final ILabel control) {
    return HtmlElement.withTypeAndInnerText(
      HtmlElementTypeCatalog.DIV,
      control.getText());
  }
}
