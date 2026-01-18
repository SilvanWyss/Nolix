/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webcontainercontrol.tabcontainer;

import java.util.function.Consumer;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.webatomiccontrol.label.ILabelStyle;

/**
 * @author Silvan Wyss
 */
public interface ITabContainer
extends ch.nolix.systemapi.webcontainercontrol.container.IContainer<ITabContainer, ITabContainerStyle> {
  ITabContainer addTab(ITabContainerTab tab, ITabContainerTab... tabs);

  ITabContainer addTabs(IContainer<ITabContainerTab> tabs);

  boolean containsSelectedTab();

  ILabelStyle getStoredHeaderStyle();

  ITabContainerTab getStoredSelectedTab();

  ITabContainerTab getStoredTabByHeader(String header);

  IContainer<ITabContainerTab> getStoredTabs();

  int getTabCount();

  ITabContainer onHeaderStyle(Consumer<ILabelStyle> headerStyleEditor);

  void selectFirstTab();

  void selectTabByHeader(String header);
}
