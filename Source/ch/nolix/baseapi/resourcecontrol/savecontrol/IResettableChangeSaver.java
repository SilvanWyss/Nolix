/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.resourcecontrol.savecontrol;

import ch.nolix.baseapi.generalstate.statemutation.Resettable;
import ch.nolix.baseapi.resourcecontrol.closecontroller.GroupCloseable;

/**
 * @author Silvan Wyss
 */
public interface IResettableChangeSaver extends GroupCloseable, IChangeSaver, Resettable {
  int getSaveCount();
}
