//package declaration
package ch.nolix.system.webgui.atomiccontrol;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.system.webgui.basecontrolservice.ControlCssBuilder;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IUploader;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IUploaderStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class UploaderCssBuilder extends ControlCssBuilder<IUploader, IUploaderStyle> {

  //method
  @Override
  protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    final IUploader button,
    final LinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  //method
  @Override
  protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
    final IUploader button,
    final ControlState state,
    final LinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  //method
  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
    final IUploader control,
    final LinkedList<CssProperty> list) {
    //Does nothing.
  }

  //method
  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final IUploader button,
    final ControlState state,
    final LinkedList<ICssProperty> list) {
    //Does nothing.
  }
}
