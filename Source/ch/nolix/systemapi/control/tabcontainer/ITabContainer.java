/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.tabcontainer;

import java.util.function.Consumer;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.control.button.IButtonStyle;
import ch.nolix.systemapi.control.verticalstack.IVerticalStack;

/**
 * @author Silvan Wyss
 */
public interface ITabContainer
extends ch.nolix.systemapi.control.container.Container<ITabContainer, ITabContainerStyle> {
  ITabContainer addTab(ITabContainerTab tab);

  ITabContainer addTabs(ExtendedIterable<ITabContainerTab> tabs);

  ITabContainer addTabs(ITabContainerTab... tabs);

  boolean containsSelectedTab();

  ITabContainerTab getStoredFirstTabByHeader(String header);

  IButtonStyle getStoredMenuButtonStyle();

  ITabContainerTab getStoredSelectedTab();

  ExtendedIterable<ITabContainerTab> getStoredTabs();

  int getTabCount();

  IVerticalStack internalGetStoredRootVerticalStack();

  ITabContainer onMenuButtonStyle(Consumer<IButtonStyle> menuButtonStyleEditor);

  void selectFirstTab();

  void selectFirstTabByHeader(String header);
}
