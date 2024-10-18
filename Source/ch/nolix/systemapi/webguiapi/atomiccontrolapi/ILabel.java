package ch.nolix.systemapi.webguiapi.atomiccontrolapi;

import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public interface ILabel extends IControl<ILabel, ILabelStyle> {

  LabelRole getRole();

  String getText();

  boolean hasRole();

  void removeRole();

  ILabel setRole(LabelRole role);

  ILabel setText(String text);
}
