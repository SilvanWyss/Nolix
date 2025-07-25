package ch.nolix.system.webgui.atomiccontrol.uploader;

import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.web.css.ICssProperty;
import ch.nolix.coreapi.web.css.ICssRule;
import ch.nolix.system.webgui.basecontroltool.AbstractControlCssBuilder;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.uploaderapi.IUploader;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.uploaderapi.IUploaderStyle;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public final class UploaderCssBuilder extends AbstractControlCssBuilder<IUploader, IUploaderStyle> {

  @Override
  protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    final IUploader button,
    final ILinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  @Override
  protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
    final IUploader button,
    final ControlState state,
    final ILinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
    final IUploader control,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }

  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final IUploader button,
    final ControlState state,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }
}
