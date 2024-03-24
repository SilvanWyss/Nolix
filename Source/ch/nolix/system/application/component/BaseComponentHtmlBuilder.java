//package declaration
package ch.nolix.system.application.component;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.systemapi.applicationapi.componentapi.IComponent;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;

//class
public final class BaseComponentHtmlBuilder implements IControlHtmlBuilder<IComponent> {

  //method
  @Override
  public IHtmlElement createHtmlElementForControl(final IComponent control) {

    final var childControls = control.getStoredChildControls();

    return switch (childControls.getCount()) {
      case 0 ->
        HtmlElement.withType(HtmlElementTypeCatalogue.DIV);
      case 1 ->
        HtmlElement.withTypeAndChildElement(
          HtmlElementTypeCatalogue.DIV,
          childControls.getStoredFirst().getHtml());
      default ->
        throw InvalidArgumentException.forArgument(control);
    };
  }
}
