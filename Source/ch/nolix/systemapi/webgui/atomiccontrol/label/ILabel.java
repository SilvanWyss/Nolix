package ch.nolix.systemapi.webgui.atomiccontrol.label;

import ch.nolix.systemapi.webgui.main.IControl;

public interface ILabel extends IControl<ILabel, ILabelStyle> {
  LabelRole getRole();

  String getText();

  boolean hasRole();

  void removeRole();

  ILabel setRole(LabelRole role);

  ILabel setText(String text);
}
