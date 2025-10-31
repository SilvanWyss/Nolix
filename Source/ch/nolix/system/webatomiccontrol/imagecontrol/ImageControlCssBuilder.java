package ch.nolix.system.webatomiccontrol.imagecontrol;

import ch.nolix.core.web.cssmodel.CssProperty;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.web.css.CssPropertyNameCatalog;
import ch.nolix.coreapi.web.cssmodel.ICssProperty;
import ch.nolix.coreapi.web.cssmodel.ICssRule;
import ch.nolix.system.webgui.controltool.AbstractControlCssBuilder;
import ch.nolix.systemapi.webatomiccontrol.imagecontrol.IImageControl;
import ch.nolix.systemapi.webatomiccontrol.imagecontrol.IImageControlStyle;
import ch.nolix.systemapi.webgui.main.ControlState;

public final class ImageControlCssBuilder
extends AbstractControlCssBuilder<IImageControl, IImageControlStyle> {
  @Override
  protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    final IImageControl imageControl,
    final ILinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  @Override
  protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
    final IImageControl imageControl,
    final ControlState state,
    final ILinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
    final IImageControl control,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }

  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final IImageControl imageControl,
    final ControlState state,
    final ILinkedList<ICssProperty> list) {
    list.addAtEnd(CssProperty.withNameAndValue(CssPropertyNameCatalog.DISPLAY, "block"));
  }
}
