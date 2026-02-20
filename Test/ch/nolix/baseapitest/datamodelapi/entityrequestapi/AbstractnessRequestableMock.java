/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.datamodelapi.entityrequestapi;

import ch.nolix.baseapi.datamodel.entityrequest.AbstractnessRequestable;

/**
 * @author Silvan Wyss
 */
public final class AbstractnessRequestableMock implements AbstractnessRequestable {
  private final boolean isAbstract;

  private AbstractnessRequestableMock(final boolean isAbstract) {
    this.isAbstract = isAbstract;
  }

  public static AbstractnessRequestableMock withIsAbstractFlag(final boolean isAbstractFlag) {
    return new AbstractnessRequestableMock(isAbstractFlag);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isAbstract() {
    return isAbstract;
  }
}
