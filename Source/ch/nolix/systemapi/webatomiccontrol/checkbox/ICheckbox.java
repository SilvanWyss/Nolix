package ch.nolix.systemapi.webatomiccontrol.checkbox;

import ch.nolix.systemapi.webgui.main.IControl;

public interface ICheckbox extends IControl<ICheckbox, ICheckboxStyle> {
  ICheckbox check();

  boolean isChecked();

  ICheckbox unCheck();
}
