/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.containercontrol.tabcontainer;

import java.util.function.Consumer;

import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.systemapi.atomiccontrol.label.ILabelStyle;

/**
 * @author Silvan Wyss
 */
public interface ITabContainer
extends ch.nolix.systemapi.containercontrol.container.IContainer<ITabContainer, ITabContainerStyle> {
  ITabContainer addTab(ITabContainerTab tab);

  ITabContainer addTabs(IContainer<ITabContainerTab> tabs);

  ITabContainer addTabs(ITabContainerTab... tabs);

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
