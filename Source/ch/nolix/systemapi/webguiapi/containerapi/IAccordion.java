//package declaration
package ch.nolix.systemapi.webguiapi.containerapi;

import java.util.function.Consumer;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.guiapi.processproperty.TabExpansionBehavior;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.ILinearContainerStyle;

//interface
public interface IAccordion
    extends ch.nolix.systemapi.webguiapi.basecontainerapi.IContainer<IAccordion, IAccordionStyle> {

  // method declaration
  IAccordion addTab(IAccordionTab tab, IAccordionTab... tabs);

  // method declaration
  IAccordion addTabs(IContainer<IAccordionTab> tabs);

  // method declaration
  void expandFirstTab();

  // method declaration
  void expandTabByHeader(String header);

  // method declaration
  ILinearContainerStyle<?> getHeaderStyle();

  // method declaration
  IAccordionTab getStoredTabByHeader(String header);

  // method declaration
  IContainer<IAccordionTab> getStoredTabs();

  // method declaration
  int getTabCount();

  // method declaration
  TabExpansionBehavior getTabExpansionBehavior();

  // method declaration
  ILinearContainerStyle<?> onHeaderStyle(Consumer<ILinearContainerStyle<?>> headerStyleEditor);

  // method declaration
  IAccordion setTabExpansionBehaviour(TabExpansionBehavior tabExpansionBehavior);
}
