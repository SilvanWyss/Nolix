package ch.nolix.systemapi.webguiapi.containerapi;

import java.util.function.Consumer;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.ILinearContainerStyle;

public interface IAccordion
extends ch.nolix.systemapi.webguiapi.basecontainerapi.IContainer<IAccordion, IAccordionStyle> {

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
