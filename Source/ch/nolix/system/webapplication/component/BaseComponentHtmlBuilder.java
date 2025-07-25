package ch.nolix.system.webapplication.component;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.coreapi.web.html.IHtmlElement;
import ch.nolix.systemapi.webapplication.component.IComponent;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;

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
