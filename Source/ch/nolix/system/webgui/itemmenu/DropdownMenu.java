package ch.nolix.system.webgui.itemmenu;

import java.util.Optional;

import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.system.graphic.color.X11ColorCatalogue;
import ch.nolix.system.webgui.main.HtmlElementEvent;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlCssBuilder;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenuStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;

public final class DropdownMenu extends ItemMenu<IDropdownMenu, IDropdownMenuStyle> implements IDropdownMenu {

  private static final DropdownMenuHtmlBuilder HTML_BUILDER = new DropdownMenuHtmlBuilder();

  private static final DropdownMenuCssBuilder CSS_BUILDER = new DropdownMenuCssBuilder();

  public DropdownMenu() {

    //A reset is required to achieve a well-defined initial state, although everything would work without a reset.
    reset();

    getStoredStyle()
      .setBackgroundColorForState(ControlState.BASE, X11ColorCatalogue.AQUAMARINE)
      .setBackgroundColorForState(ControlState.HOVER, X11ColorCatalogue.MEDIUM_AQUA_MARINE)
      .setBackgroundColorForState(ControlState.FOCUS, X11ColorCatalogue.MEDIUM_AQUA_MARINE);
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
