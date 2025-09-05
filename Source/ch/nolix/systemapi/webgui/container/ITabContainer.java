package ch.nolix.systemapi.webgui.container;

import java.util.function.Consumer;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.webgui.atomiccontrol.labelapi.ILabelStyle;

public interface ITabContainer
extends ch.nolix.systemapi.webgui.basecontainer.IContainer<ITabContainer, ITabContainerStyle> {
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
