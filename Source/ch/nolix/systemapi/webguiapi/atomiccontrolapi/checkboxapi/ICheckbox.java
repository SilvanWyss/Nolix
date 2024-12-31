package ch.nolix.systemapi.webguiapi.atomiccontrolapi.checkboxapi;

import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public interface ICheckbox extends IControl<ICheckbox, ICheckboxStyle> {

  ICheckbox check();

  boolean isChecked();

  ICheckbox unCheck();
}
