/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.containercontrol.tabcontainer;

import java.util.function.Consumer;

import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.systemapi.atomiccontrol.button.IButtonStyle;
import ch.nolix.systemapi.containercontrol.verticalstack.IVerticalStack;

/**
 * @author Silvan Wyss
 */
public interface ITabContainer
extends ch.nolix.systemapi.containercontrol.container.IContainer<ITabContainer, ITabContainerStyle> {
  ITabContainer addTab(ITabContainerTab tab);

  ITabContainer addTabs(IContainer<ITabContainerTab> tabs);

  ITabContainer addTabs(ITabContainerTab... tabs);

  boolean containsSelectedTab();

  ITabContainerTab getStoredFirstTabByHeader(String header);

  IButtonStyle getStoredMenuButtonStyle();

  ITabContainerTab getStoredSelectedTab();

  IContainer<ITabContainerTab> getStoredTabs();

  int getTabCount();

  IVerticalStack internalGetStoredRootVerticalStack();

  ITabContainer onMenuButtonStyle(Consumer<IButtonStyle> menuButtonStyleEditor);

  void selectFirstTab();

  void selectFirstTabByHeader(String header);
}
