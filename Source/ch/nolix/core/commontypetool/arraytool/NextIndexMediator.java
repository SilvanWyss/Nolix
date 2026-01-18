/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.commontypetool.arraytool;

import ch.nolix.coreapi.commontypetool.arraytool.INextIndexMediator;

/**
 * @author Silvan Wyss
 */
public final class NextIndexMediator implements INextIndexMediator {
  private final int nextIndex;

  private NextIndexMediator(final int nextIndex) {
    this.nextIndex = nextIndex;
  }

  public static NextIndexMediator forNextIndex(final int nextIndex) {
    return new NextIndexMediator(nextIndex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int andGetNextIndex() {
    return nextIndex;
  }
}
