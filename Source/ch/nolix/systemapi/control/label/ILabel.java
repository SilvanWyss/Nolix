/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.label;

import ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute.FluentMutableTextHolder;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public interface ILabel extends Control<ILabel, ILabelStyle>, FluentMutableTextHolder<ILabel> {
  LabelRole getRole();

  boolean hasRole();

  void removeRole();

  ILabel setRole(LabelRole role);
}
