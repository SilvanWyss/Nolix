package ch.nolix.systemapi.webguiapi.containerapi;

import java.util.function.Consumer;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ILabelStyle;

public interface ITabContainer
extends ch.nolix.systemapi.webguiapi.basecontainerapi.IContainer<ITabContainer, ITabContainerStyle> {

  ITabContainer addTab(ITabContainerTab tab, ITabContainerTab... tabs);

  ITabContainer addTabs(IContainer<ITabContainerTab> tabs);

  boolean containsSelectedTab();

  ILabelStyle getStoredHeaderStyle();

  ITabContainerTab getStoredSelectedTab();

  ITabContainerTab getStoredTabByHeader(String header);

  IContainer<ITabContainerTab> getStoredTabs();

  int getTabCount();

  ITabContainer onHeaderStyle(Consumer<ILabelStyle> headerStyleEditor);

  void selectFirstTab();

  void selectTabByHeader(String header);
}
