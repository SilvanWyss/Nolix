/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webgui.controltool;

import ch.nolix.base.container.arraylist.ArrayList;
import ch.nolix.baseapi.container.list.IArrayList;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.webgui.controltool.IControlTool;
import ch.nolix.systemapi.webgui.main.IControl;

/**
 * @author Silvan Wyss
 */
public final class ControlTool implements IControlTool {
  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<IControl<?, ?>> getListWithControlAndChildControlsRecursively(final IControl<?, ?> control) {
    final IArrayList<IControl<?, ?>> list = ArrayList.withElements(control);

    ControlToolHelper.fillUpChildControlsOfControlIntoListRecursively(control, list);

    return list;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<IControl<?, ?>> getListWithControlAndStructureControlsRecursively(final IControl<?, ?> control) {
    final IArrayList<IControl<?, ?>> list = ArrayList.withElements(control);

    ControlToolHelper.fillUpStructureControlsOfControlIntoListRecursively(control, list);

    return list;
  }
}
