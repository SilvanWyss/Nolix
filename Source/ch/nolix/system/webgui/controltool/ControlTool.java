/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webgui.controltool;

import ch.nolix.base.datastructure.arraylist.ArrayList;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.IArrayList;
import ch.nolix.systemapi.webgui.controltool.IControlTool;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public final class ControlTool implements IControlTool {
  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<Control<?, ?>> getListWithControlAndChildControlsRecursively(final Control<?, ?> control) {
    final IArrayList<Control<?, ?>> list = ArrayList.withElements(control);

    ControlToolHelper.fillUpChildControlsOfControlIntoListRecursively(control, list);

    return list;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<Control<?, ?>> getListWithControlAndStructureControlsRecursively(
    final Control<?, ?> control) {
    final IArrayList<Control<?, ?>> list = ArrayList.withElements(control);

    ControlToolHelper.fillUpStructureControlsOfControlIntoListRecursively(control, list);

    return list;
  }
}
