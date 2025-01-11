package ch.nolix.system.application.component;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalog;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.systemapi.applicationapi.componentapi.IComponent;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;

public final class BaseComponentHtmlBuilder implements IControlHtmlBuilder<IComponent> {

  @Override
  public IHtmlElement createHtmlElementForControl(final IComponent control) {

    final var childControls = control.getStoredChildControls();

    return switch (childControls.getCount()) {
      case 0 ->
        HtmlElement.withType(HtmlElementTypeCatalog.DIV);
      case 1 ->
        HtmlElement.withTypeAndChildElement(
          HtmlElementTypeCatalog.DIV,
          childControls.getStoredFirst().getHtml());
      default ->
        throw InvalidArgumentException.forArgument(control);
    };
  }
}
