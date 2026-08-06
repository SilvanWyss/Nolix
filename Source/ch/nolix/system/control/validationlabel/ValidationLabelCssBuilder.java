/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.validationlabel;

import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.web.cssmodel.ICssProperty;
import ch.nolix.baseapi.web.cssmodel.ICssRule;
import ch.nolix.system.webgui.controltool.AbstractControlCssBuilder;
import ch.nolix.systemapi.control.validationlabel.IValidationLabel;
import ch.nolix.systemapi.control.validationlabel.IValidationLabelStyle;
import ch.nolix.systemapi.webgui.webguiproperty.ControlState;

/**
 * @author Silvan Wyss
 */
public final class ValidationLabelCssBuilder
extends AbstractControlCssBuilder<IValidationLabel, IValidationLabelStyle> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpCssPropertiesForControlAndAllStatesIntoList(
    final IValidationLabel control,
    final ILinkedList<ICssProperty> list) {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpAdditionalCssRulesForControlAndStateIntoList(
    final IValidationLabel text,
    final ControlState state,
    final ILinkedList<? super ICssRule> list) {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpAdditionalCssRulesForControlAndAllStatesIntoList(
    final IValidationLabel text,
    final ILinkedList<? super ICssRule> list) {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpCssPropertiesForControlAndStateIntoList(
    final IValidationLabel text,
    final ControlState state,
    final ILinkedList<ICssProperty> list) {
    // Does nothing.
  }
}
