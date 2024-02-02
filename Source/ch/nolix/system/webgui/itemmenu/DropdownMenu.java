//package declaration
package ch.nolix.system.webgui.itemmenu;

//Java imports
import java.util.Optional;

//own imports
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.webgui.main.HtmlElementEvent;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlCssBuilder;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenuStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;

//class
public final class DropdownMenu extends ItemMenu<IDropdownMenu, IDropdownMenuStyle> implements IDropdownMenu {

  //constant
  private static final DropdownMenuHtmlBuilder HTML_BUILDER = new DropdownMenuHtmlBuilder();

  //constant
  private static final DropdownMenuCssBuilder CSS_BUILDER = new DropdownMenuCssBuilder();

  //constructor
  public DropdownMenu() {

    //Info: Reset is technically optional, but required to achieve a well-defined
    //initial state.
    reset();

    getStoredStyle()
      .setBackgroundColorForState(ControlState.BASE, Color.AQUAMARINE)
      .setBackgroundColorForState(ControlState.HOVER, Color.MEDIUM_AQUA_MARINE)
      .setBackgroundColorForState(ControlState.FOCUS, Color.MEDIUM_AQUA_MARINE);
  }

  //method
  @Override
  public Optional<String> getOptionalJavaScriptUserInputFunction() {
    return Optional.of("if (x.selectedIndex == -1) {return '';} return x.options[x.selectedIndex].text;");
  }

  //method
  @Override
  public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
    list.addAtEnd(HtmlElementEvent.withHtmlElementIdAndHtmlEvent(getInternalId(), "onchange"));
  }

  //method
  @Override
  protected IDropdownMenuStyle createStyle() {
    return new DropdownMenuStyle();
  }

  //method
  @Override
  protected IControlHtmlBuilder<IDropdownMenu> getHtmlBuilder() {
    return HTML_BUILDER;
  }

  //method
  @Override
  protected IControlCssBuilder<IDropdownMenu, IDropdownMenuStyle> getCssBuilder() {
    return CSS_BUILDER;
  }
}
