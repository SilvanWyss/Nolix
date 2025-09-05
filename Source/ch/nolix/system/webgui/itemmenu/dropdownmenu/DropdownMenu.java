package ch.nolix.system.webgui.itemmenu.dropdownmenu;

import java.util.Optional;

import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.webgui.itemmenu.base.AbstractItemMenu;
import ch.nolix.system.webgui.main.HtmlElementEvent;
import ch.nolix.systemapi.webgui.controltool.IControlCssBuilder;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;
import ch.nolix.systemapi.webgui.itemmenu.dropdownmenuapi.IDropdownMenu;
import ch.nolix.systemapi.webgui.itemmenu.dropdownmenuapi.IDropdownMenuStyle;
import ch.nolix.systemapi.webgui.main.ControlState;
import ch.nolix.systemapi.webgui.main.IHtmlElementEvent;

public final class DropdownMenu extends AbstractItemMenu<IDropdownMenu, IDropdownMenuStyle> implements IDropdownMenu {
  private static final DropdownMenuHtmlBuilder HTML_BUILDER = new DropdownMenuHtmlBuilder();

  private static final DropdownMenuCssBuilder CSS_BUILDER = new DropdownMenuCssBuilder();

  public DropdownMenu() {
    //A reset is required to achieve a well-defined initial state, although everything would work without a reset.
    reset();

    getStoredStyle()
      .setBackgroundColorForState(ControlState.BASE, X11ColorCatalog.AQUAMARINE)
      .setBackgroundColorForState(ControlState.HOVER, X11ColorCatalog.MEDIUM_AQUA_MARINE)
      .setBackgroundColorForState(ControlState.FOCUS, X11ColorCatalog.MEDIUM_AQUA_MARINE);
  }

  @Override
  public Optional<String> getOptionalJavaScriptUserInputFunction() {
    return Optional.of("if (x.selectedIndex == -1) {return '';} return x.options[x.selectedIndex].text;");
  }

  @Override
  public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
    list.addAtEnd(HtmlElementEvent.withHtmlElementIdAndHtmlEvent(getInternalId(), "onchange"));
  }

  @Override
  protected IDropdownMenuStyle createStyle() {
    return new DropdownMenuStyle();
  }

  @Override
  protected IControlHtmlBuilder<IDropdownMenu> getHtmlBuilder() {
    return HTML_BUILDER;
  }

  @Override
  protected IControlCssBuilder<IDropdownMenu, IDropdownMenuStyle> getCssBuilder() {
    return CSS_BUILDER;
  }
}
