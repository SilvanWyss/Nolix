package ch.nolix.system.webcontainercontrol.singlecontainer;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.htmlelementmodel.HtmlElement;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.coreapi.web.htmlelementmodel.IHtmlElement;
import ch.nolix.systemapi.webcontainercontrol.singlecontainer.ISingleContainer;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;

/**
 * @author Silvan Wyss
 */
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

    return ImmutableList.withElements(singleContainer.getStoredControl().getHtml());
  }
}
