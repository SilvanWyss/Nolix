/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.commontype.arraytool;

import ch.nolix.baseapi.commontype.arraytool.INextIndexMediator;

/**
 * @author Silvan Wyss
 */
public final class NextIndexMediator implements INextIndexMediator {
  private final int nextIndex;

  private NextIndexMediator(final int nextIndex) {
    this.nextIndex = nextIndex;
  }

  /**
   * @param nextIndex
   * @return a new {@link NextIndexMediator} for the given nextIndex
   */
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
