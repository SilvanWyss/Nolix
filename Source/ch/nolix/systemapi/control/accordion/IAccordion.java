/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.accordion;

import java.util.function.Consumer;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.control.container.Container;
import ch.nolix.systemapi.control.linearcontainer.ILinearContainerStyle;

/**
 * @author Silvan Wyss
 */
public interface IAccordion extends Container<IAccordion, IAccordionStyle> {
  /**
   * Adds the given tab to the current {@link IAccordion}.
   * 
   * @param tab
   * @return the current {@link IAccordion}
   */
  IAccordion addTab(IAccordionTab tab);

  /**
   * Adds the given tabs to the current {@link IAccordion}.
   * 
   * @param tabs
   * @return the current {@link IAccordion}
   */
  IAccordion addTabs(IAccordionTab... tabs);

  /**
   * Adds the given tabs to the current {@link IAccordion}.
   * 
   * @param tabs
   * @return the current {@link IAccordion}
   */
  IAccordion addTabs(ExtendedIterable<IAccordionTab> tabs);

  /**
   * Expands the first {@link IAccordionTab} of the current {@link IAccordion}.
   * 
   * @throws RuntimeException if the current {@link IAccordion} does not contain a
   *                          {@link IAccordionTab}
   */
  void expandFirstTab();

  /**
   * Expands the {@link IAccordionTab} with the given header of the current
   * {@link IAccordion}.
   * 
   * @param header
   * @throws RuntimeException if the current {@link IAccordion} does not contain a
   *                          {@link IAccordionTab} with the given header
   */
  void expandTabByHeader(String header);

  /**
   * @return the style of the headers of the {@link IAccordionTab}s of the current
   *         {@link IAccordion}
   */
  ILinearContainerStyle<?> getHeaderStyle();

  /**
   * @param header
   * @return the {@link IAccordionTab} with the given header of the current
   *         {@link IAccordion}
   * @throws RuntimeException if the current {@link IAccordion} does not contain a
   *                          {@link IAccordionTab} with the given header
   */
  IAccordionTab getStoredTabByHeader(String header);

  /**
   * @return the {@link IAccordionTab}s of the current {@link IAccordion}
   */
  ExtendedIterable<IAccordionTab> getStoredTabs();

  /**
   * @return the number of {@link IAccordionTab}s of the current
   *         {@link IAccordion}
   */
  int getTabCount();

  /**
   * @return the {@link TabExpansionBehavior} of the current {@link IAccordion}
   */
  TabExpansionBehavior getTabExpansionBehavior();

  /**
   * Lets the given headerStyleEditor edit the style of the headers of the
   * {@link IAccordionTab}s of the current {@link IAccordion}.
   * 
   * @param headerStyleEditor
   * @return the style of the headers of the {@link IAccordionTab}s of the current
   *         {@link IAccordion}
   */
  ILinearContainerStyle<?> onHeaderStyle(Consumer<ILinearContainerStyle<?>> headerStyleEditor);

  /**
   * Sets the given tabExpansionBehavior to the current {@link IAccordion}.
   * 
   * @param tabExpansionBehavior
   * @return the current {@link IAccordion}
   */
  IAccordion setTabExpansionBehaviour(TabExpansionBehavior tabExpansionBehavior);
}
