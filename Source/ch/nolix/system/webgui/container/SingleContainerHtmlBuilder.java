package ch.nolix.system.webgui.container;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.coreapi.web.html.IHtmlElement;
import ch.nolix.systemapi.webguiapi.containerapi.ISingleContainer;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;

public final class SingleContainerHtmlBuilder implements IControlHtmlBuilder<ISingleContainer> {

  @Override
  public IHtmlElement createHtmlElementForControl(final ISingleContainer control) {
    return HtmlElement.withTypeAndChildElements(
      HtmlElementTypeCatalog.DIV,
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
