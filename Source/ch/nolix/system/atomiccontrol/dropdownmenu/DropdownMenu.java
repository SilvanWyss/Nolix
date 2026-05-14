/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.atomiccontrol.dropdownmenu;

import java.util.Optional;

import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.container.list.ILinkedList;
import ch.nolix.system.atomiccontrol.itemmenu.AbstractItemMenu;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.webgui.main.HtmlElementEvent;
import ch.nolix.systemapi.atomiccontrol.dropdownmenu.IDropdownMenu;
import ch.nolix.systemapi.atomiccontrol.dropdownmenu.IDropdownMenuStyle;
import ch.nolix.systemapi.webgui.controlstyle.IControlStyle;
import ch.nolix.systemapi.webgui.controltool.IControlCssBuilder;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;
import ch.nolix.systemapi.webgui.main.ControlState;
import ch.nolix.systemapi.webgui.main.IControl;
import ch.nolix.systemapi.webgui.main.IHtmlElementEvent;

/**
 * @author Silvan Wyss
 */
public final class DropdownMenu extends AbstractItemMenu<IDropdownMenu, IDropdownMenuStyle> implements IDropdownMenu {
  private static final DropdownMenuHtmlBuilder HTML_BUILDER = new DropdownMenuHtmlBuilder();

  private static final DropdownMenuCssBuilder CSS_BUILDER = new DropdownMenuCssBuilder();

  public DropdownMenu() {
    //A reset is required to achieve a well-defined initial state, although everything would work without a reset.
    reset();

    getStoredStyle()
      .forStateSetBackgroundColor(ControlState.BASE, X11ColorCatalog.AQUAMARINE)
      .forStateSetBackgroundColor(ControlState.HOVER, X11ColorCatalog.MEDIUM_AQUA_MARINE)
      .forStateSetBackgroundColor(ControlState.FOCUS, X11ColorCatalog.MEDIUM_AQUA_MARINE);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<String> getOptionalJavaScriptUserInputFunction() {
    return Optional.of("if (x.selectedIndex == -1) {return '';} return x.options[x.selectedIndex].text;");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <T extends IControl<T, X>, X extends IControlStyle<X>> IContainer<T> getStoredStructureControls() {
    return ImmutableList.createEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
    list.addAtEnd(HtmlElementEvent.withHtmlElementIdAndHtmlEvent(getInternalId(), "onchange"));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IDropdownMenuStyle createStyle() {
    return new DropdownMenuStyle();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IControlHtmlBuilder<IDropdownMenu> getHtmlBuilder() {
    return HTML_BUILDER;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IControlCssBuilder<IDropdownMenu, IDropdownMenuStyle> getCssBuilder() {
    return CSS_BUILDER;
  }
}
