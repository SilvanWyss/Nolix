/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.modelflyweight;

import ch.nolix.systemapi.objectdata.modelflyweight.IEntityFlyWeight;

/**
 * @author Silvan Wyss
 */
public final class VoidEntityFlyWeight implements IEntityFlyWeight {
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isVoid() {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void noteInsertIntoDatabase() {
    //Does nothing.
  }
}
