package ch.nolix.systemapi.webcontainercontrol.singlecontainer;

import ch.nolix.systemapi.webcontainercontrol.container.IContainer;
import ch.nolix.systemapi.webgui.main.IControl;

public interface ISingleContainer extends IContainer<ISingleContainer, ISingleContainerStyle> {
  IControl<?, ?> getStoredControl();

  ISingleContainer setControl(IControl<?, ?> control);
}
