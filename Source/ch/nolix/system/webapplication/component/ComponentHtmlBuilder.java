/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webapplication.component;

import ch.nolix.base.html.htmlmodel.HtmlElement;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.html.htmlcatalog.HtmlElementTypeCatalog;
import ch.nolix.baseapi.html.htmlmodel.IHtmlElement;
import ch.nolix.systemapi.webapplication.component.IComponent;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;

/**
 * @author Silvan Wyss
 */
public final class ComponentHtmlBuilder implements IControlHtmlBuilder<IComponent> {
  /**
   * {@inheritDoc}
   */
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
