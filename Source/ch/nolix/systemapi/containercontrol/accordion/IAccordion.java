/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.containercontrol.accordion;

import java.util.function.Consumer;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.systemapi.containercontrol.linearcontainer.ILinearContainerStyle;

/**
 * @author Silvan Wyss
 */
public interface IAccordion
extends ch.nolix.systemapi.containercontrol.container.IContainer<IAccordion, IAccordionStyle> {
  IAccordion addTab(IAccordionTab tab);

  IAccordion addTabs(IAccordionTab... tabs);

  IAccordion addTabs(IWellOrderContainer<IAccordionTab> tabs);

  void expandFirstTab();

  void expandTabByHeader(String header);

  ILinearContainerStyle<?> getHeaderStyle();

  IAccordionTab getStoredTabByHeader(String header);

  IWellOrderContainer<IAccordionTab> getStoredTabs();

  int getTabCount();

  TabExpansionBehavior getTabExpansionBehavior();

  ILinearContainerStyle<?> onHeaderStyle(Consumer<ILinearContainerStyle<?>> headerStyleEditor);

  IAccordion setTabExpansionBehaviour(TabExpansionBehavior tabExpansionBehavior);
}
