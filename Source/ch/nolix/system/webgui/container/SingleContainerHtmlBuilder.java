//package declaration
package ch.nolix.system.webgui.container;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.systemapi.webguiapi.containerapi.ISingleContainer;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;

//class
public final class SingleContainerHtmlBuilder implements IControlHtmlBuilder<ISingleContainer> {

  //method
  @Override
  public IHtmlElement createHtmlElementForControl(final ISingleContainer control) {
    return HtmlElement.withTypeAndChildElements(
      HtmlElementTypeCatalogue.DIV,
      createHtmlElementsForChildControlsOfSingleContainer(control));
  }

  //method
  private IContainer<IHtmlElement> createHtmlElementsForChildControlsOfSingleContainer(
    final ISingleContainer singleContainer) {

    if (singleContainer.isEmpty()) {
      return new ImmutableList<>();
    }

    return ImmutableList.withElement(singleContainer.getStoredControl().getHtml());
  }
}
