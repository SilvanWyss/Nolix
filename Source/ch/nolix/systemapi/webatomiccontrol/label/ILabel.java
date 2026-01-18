/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webatomiccontrol.label;

import ch.nolix.systemapi.webgui.main.IControl;

/**
 * @author Silvan Wyss
 */
public interface ILabel extends IControl<ILabel, ILabelStyle> {
  LabelRole getRole();

  String getText();

  boolean hasRole();

  void removeRole();

  ILabel setRole(LabelRole role);

  ILabel setText(String text);
}
