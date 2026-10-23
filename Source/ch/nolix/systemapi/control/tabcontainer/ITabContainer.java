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
  /**
   * Adds the given tab to the current {@link ITabContainer}.
   * 
   * @param tab
   * @return the current {@link ITabContainer}
   */
  ITabContainer addTab(ITabContainerTab tab);

  /**
   * Adds the given tabs to the current {@link ITabContainer}.
   * 
   * @param tabs
   * @return the current {@link ITabContainer}
   */
  ITabContainer addTabs(ExtendedIterable<ITabContainerTab> tabs);

  /**
   * Adds the given tabs to the current {@link ITabContainer}.
   * 
   * @param tabs
   * @return the current {@link ITabContainer}
   */
  ITabContainer addTabs(ITabContainerTab... tabs);

  /**
   * @return true if the current {@link ITabContainer} contains a selected
   *         {@link ITabContainerTab}, false otherwise
   */
  boolean containsSelectedTab();

  /**
   * @param header
   * @return the first {@link ITabContainerTab} with the given header of the
   *         current {@link ITabContainer}
   * @throws RuntimeException if the current {@link ITabContainer} does not
   *                          contain a {@link ITabContainerTab} with the given
   *                          header
   */
  ITabContainerTab getStoredFirstTabByHeader(String header);

  /**
   * @return the style of the menu buttons of the current {@link ITabContainer}
   */
  IButtonStyle getStoredMenuButtonStyle();

  /**
   * @return the selected {@link ITabContainerTab} of the current
   *         {@link ITabContainer}
   * @throws RuntimeException if the current {@link ITabContainer} does not
   *                          contain a selected {@link ITabContainerTab}
   */
  ITabContainerTab getStoredSelectedTab();

  /**
   * @return the {@link ITabContainerTab}s of the current {@link ITabContainer}
   */
  ExtendedIterable<ITabContainerTab> getStoredTabs();

  /**
   * @return the number of {@link ITabContainerTab}s of the current
   *         {@link ITabContainer}
   */
  int getTabCount();

  /**
   * @return the root {@link IVerticalStack} of the current {@link ITabContainer}
   */
  IVerticalStack internalGetStoredRootVerticalStack();

  /**
   * Lets the given menuButtonStyleEditor edit the style of the menu buttons of
   * the current {@link ITabContainer}.
   * 
   * @param menuButtonStyleEditor
   * @return the current {@link ITabContainer}
   */
  ITabContainer onMenuButtonStyle(Consumer<IButtonStyle> menuButtonStyleEditor);

  /**
   * Selects the first {@link ITabContainerTab} of the current
   * {@link ITabContainer}.
   * 
   * @throws RuntimeException if the current {@link ITabContainer} does not
   *                          contain a {@link ITabContainerTab}
   */
  void selectFirstTab();

  /**
   * Selects the first {@link ITabContainerTab} with the given header of the
   * current {@link ITabContainer}.
   * 
   * @param header
   * @throws RuntimeException if the current {@link ITabContainer} does not
   *                          contain a {@link ITabContainerTab} with the given
   *                          header
   */
  void selectFirstTabByHeader(String header);
}
