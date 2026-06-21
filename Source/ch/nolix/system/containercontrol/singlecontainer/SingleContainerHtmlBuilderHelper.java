/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.containercontrol.singlecontainer;

import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.web.htmlelementmodel.IHtmlElement;
import ch.nolix.systemapi.containercontrol.singlecontainer.ISingleContainer;

/**
 * @author Silvan Wyss
 */
public final class SingleContainerHtmlBuilderHelper {
  private SingleContainerHtmlBuilderHelper() {
  }

  public static ExtendedIterable<IHtmlElement> createHtmlElementsForChildControlsOfSingleContainer(
    final ISingleContainer singleContainer) {
    if (singleContainer.containsAny()) {
      return ImmutableList.withElements(singleContainer.getStoredControl().getHtml());

    }

    return ImmutableList.createEmpty();
  }
}
