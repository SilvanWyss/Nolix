package ch.nolix.systemapi.webguiapi.linearcontainerapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public interface ILinearContainer<LC extends ILinearContainer<LC, LCL>, LCL extends ILinearContainerStyle<LCL>>
extends ch.nolix.systemapi.webguiapi.basecontainerapi.IContainer<LC, LCL> {

  LC addControl(IControl<?, ?> control, IControl<?, ?>... controls);

  LC addControls(IContainer<? extends IControl<?, ?>> controls);

  void removeControl(IControl<?, ?> control);
}
