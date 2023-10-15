//package declaration
package ch.nolix.systemapi.webguiapi.containerapi;

import java.util.function.Consumer;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ILabelStyle;

//interface
public interface ITabContainer
    extends ch.nolix.systemapi.webguiapi.basecontainerapi.IContainer<ITabContainer, ITabContainerStyle> {

  // method declaration
  ITabContainer addTab(ITabContainerTab tab, ITabContainerTab... tabs);

  // method declaration
  ITabContainer addTabs(IContainer<ITabContainerTab> tabs);

  // method declaration
  boolean containsSelectedTab();

  // method declaration
  ILabelStyle getStoredHeaderStyle();

  // method declaration
  ITabContainerTab getStoredSelectedTab();

  // method declaration
  ITabContainerTab getStoredTabByHeader(String header);

  // method declaration
  IContainer<ITabContainerTab> getStoredTabs();

  // method declaration
  int getTabCount();

  // method declaration
  ITabContainer onHeaderStyle(Consumer<ILabelStyle> headerStyleEditor);

  // method declaration
  void selectFirstTab();

  // method declaration
  void selectTabByHeader(String header);
}
