package ch.nolix.system.objectdata.modelflyweight;

import ch.nolix.systemapi.objectdata.modelflyweight.IFieldFlyWeight;

/**
 * @author Silvan Wyss
 */
public final class VoidFieldFlyWeight implements IFieldFlyWeight {
  @Override
  public boolean isVoid() {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void noteUpdate() {
    //Does nothing.
  }
}
