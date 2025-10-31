package ch.nolix.system.webatomiccontrol.label;

import ch.nolix.core.web.htmlelementmodel.HtmlElement;
import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.coreapi.web.htmlelementmodel.IHtmlElement;
import ch.nolix.systemapi.webatomiccontrol.label.ILabel;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;

public final class LabelHtmlBuilder implements IControlHtmlBuilder<ILabel> {
  @Override
  public IHtmlElement createHtmlElementForControl(final ILabel control) {
    return HtmlElement.withTypeAndInnerText(
      HtmlElementTypeCatalog.DIV,
      control.getText());
  }
}
