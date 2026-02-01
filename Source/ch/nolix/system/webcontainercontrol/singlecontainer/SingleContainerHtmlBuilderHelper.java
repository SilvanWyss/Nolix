/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webcontainercontrol.singlecontainer;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.web.htmlelementmodel.IHtmlElement;
import ch.nolix.systemapi.webcontainercontrol.singlecontainer.ISingleContainer;

/**
 * @author Silvan Wyss
 */
public final class SingleContainerHtmlBuilderHelper {
  private SingleContainerHtmlBuilderHelper() {
  }

  public static IContainer<IHtmlElement> createHtmlElementsForChildControlsOfSingleContainer(
    final ISingleContainer singleContainer) {
    if (singleContainer.containsAny()) {
      return ImmutableList.withElements(singleContainer.getStoredControl().getHtml());

    }

    return ImmutableList.createEmpty();
  }
}
