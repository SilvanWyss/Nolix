/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.atomiccontrol.label;

import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public interface ILabel extends Control<ILabel, ILabelStyle> {
  LabelRole getRole();

  String getText();

  boolean hasRole();

  void removeRole();

  ILabel setRole(LabelRole role);

  ILabel setText(String text);
}
