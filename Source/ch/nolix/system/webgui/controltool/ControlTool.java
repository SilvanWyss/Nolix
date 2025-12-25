package ch.nolix.system.webgui.controltool;

import ch.nolix.core.container.arraylist.ArrayList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.IArrayList;
import ch.nolix.systemapi.webgui.controltool.IControlTool;
import ch.nolix.systemapi.webgui.main.IControl;

/**
 * @author Silvan Wyss
 */
public final class ControlTool implements IControlTool {
  @Override
  public IContainer<IControl<?, ?>> getListWithControlAndChildControlsRecursively(final IControl<?, ?> control) {
    final IArrayList<IControl<?, ?>> list = ArrayList.withElements(control);

    ControlToolHelper.fillUpChildControlsOfControlIntoListRecursively(control, list);

    return list;
  }
}
