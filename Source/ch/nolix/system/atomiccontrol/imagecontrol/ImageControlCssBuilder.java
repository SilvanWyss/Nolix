/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.atomiccontrol.imagecontrol;

import ch.nolix.base.web.cssmodel.CssProperty;
import ch.nolix.baseapi.container.list.ILinkedList;
import ch.nolix.baseapi.web.css.CssPropertyNameCatalog;
import ch.nolix.baseapi.web.cssmodel.ICssProperty;
import ch.nolix.baseapi.web.cssmodel.ICssRule;
import ch.nolix.system.webgui.controltool.AbstractControlCssBuilder;
import ch.nolix.systemapi.atomiccontrol.imagecontrol.IImageControl;
import ch.nolix.systemapi.atomiccontrol.imagecontrol.IImageControlStyle;
import ch.nolix.systemapi.webgui.main.ControlState;

/**
 * @author Silvan Wyss
 */
public final class ImageControlCssBuilder
extends AbstractControlCssBuilder<IImageControl, IImageControlStyle> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    final IImageControl imageControl,
    final ILinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
    final IImageControl imageControl,
    final ControlState state,
    final ILinkedList<? super ICssRule> list) {
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
    final IImageControl control,
    final ILinkedList<ICssProperty> list) {
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final IImageControl imageControl,
    final ControlState state,
    final ILinkedList<ICssProperty> list) {
    list.addAtEnd(CssProperty.withNameAndValue(CssPropertyNameCatalog.DISPLAY, "block"));
  }
}
