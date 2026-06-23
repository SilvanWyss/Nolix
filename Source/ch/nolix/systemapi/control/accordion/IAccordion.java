/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.accordion;

import java.util.function.Consumer;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.control.linearcontainer.ILinearContainerStyle;

/**
 * @author Silvan Wyss
 */
public interface IAccordion
extends ch.nolix.systemapi.control.container.IContainer<IAccordion, IAccordionStyle> {
  IAccordion addTab(IAccordionTab tab);

  IAccordion addTabs(IAccordionTab... tabs);

  IAccordion addTabs(ExtendedIterable<IAccordionTab> tabs);

  void expandFirstTab();

  void expandTabByHeader(String header);

  ILinearContainerStyle<?> getHeaderStyle();

  IAccordionTab getStoredTabByHeader(String header);

  ExtendedIterable<IAccordionTab> getStoredTabs();

  int getTabCount();

  TabExpansionBehavior getTabExpansionBehavior();

  ILinearContainerStyle<?> onHeaderStyle(Consumer<ILinearContainerStyle<?>> headerStyleEditor);

  IAccordion setTabExpansionBehaviour(TabExpansionBehavior tabExpansionBehavior);
}
