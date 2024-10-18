package ch.nolix.system.webgui.container;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.systemapi.webguiapi.containerapi.ISingleContainer;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;

public final class SingleContainerHtmlBuilder implements IControlHtmlBuilder<ISingleContainer> {

  @Override
  public IHtmlElement createHtmlElementForControl(final ISingleContainer control) {
    return HtmlElement.withTypeAndChildElements(
      HtmlElementTypeCatalogue.DIV,
      createHtmlElementsForChildControlsOfSingleContainer(control));
  }

  private IContainer<IHtmlElement> createHtmlElementsForChildControlsOfSingleContainer(
    final ISingleContainer singleContainer) {

    if (singleContainer.isEmpty()) {
      return ImmutableList.createEmpty();
    }

    return ImmutableList.withElement(singleContainer.getStoredControl().getHtml());
  }
}
