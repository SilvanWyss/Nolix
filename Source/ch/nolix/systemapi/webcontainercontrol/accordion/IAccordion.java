package ch.nolix.systemapi.webcontainercontrol.accordion;

import java.util.function.Consumer;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.webcontainercontrol.linearcontainer.ILinearContainerStyle;
import ch.nolix.systemapi.webcontainercontrol.tabcontainer.TabExpansionBehavior;

/**
 * @author Silvan Wyss
 */
public interface IAccordion
extends ch.nolix.systemapi.webcontainercontrol.container.IContainer<IAccordion, IAccordionStyle> {
  IAccordion addTab(IAccordionTab tab, IAccordionTab... tabs);

  IAccordion addTabs(IContainer<IAccordionTab> tabs);

  void expandFirstTab();

  void expandTabByHeader(String header);

  ILinearContainerStyle<?> getHeaderStyle();

  IAccordionTab getStoredTabByHeader(String header);

  IContainer<IAccordionTab> getStoredTabs();

  int getTabCount();

  TabExpansionBehavior getTabExpansionBehavior();

  ILinearContainerStyle<?> onHeaderStyle(Consumer<ILinearContainerStyle<?>> headerStyleEditor);

  IAccordion setTabExpansionBehaviour(TabExpansionBehavior tabExpansionBehavior);
}
