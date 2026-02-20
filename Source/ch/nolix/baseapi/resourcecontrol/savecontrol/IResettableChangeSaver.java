/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.resourcecontrol.savecontrol;

import ch.nolix.baseapi.resourcecontrol.closecontroller.GroupCloseable;
import ch.nolix.baseapi.state.statemutation.Resettable;

/**
 * @author Silvan Wyss
 */
public interface IResettableChangeSaver extends GroupCloseable, IChangeSaver, Resettable {
  int getSaveCount();
}
