package ch.nolix.systemapi.webguiapi.containerapi;

import ch.nolix.systemapi.webguiapi.basecontainerapi.IContainer;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public interface ISingleContainer extends IContainer<ISingleContainer, ISingleContainerStyle> {

  IControl<?, ?> getStoredControl();

  ISingleContainer setControl(IControl<?, ?> control);
}
