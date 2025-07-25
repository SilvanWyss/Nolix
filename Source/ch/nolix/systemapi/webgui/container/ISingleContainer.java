package ch.nolix.systemapi.webgui.container;

import ch.nolix.systemapi.webgui.basecontainer.IContainer;
import ch.nolix.systemapi.webgui.main.IControl;

public interface ISingleContainer extends IContainer<ISingleContainer, ISingleContainerStyle> {

  IControl<?, ?> getStoredControl();

  ISingleContainer setControl(IControl<?, ?> control);
}
