/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.commontype.arraytool;

import ch.nolix.baseapi.commontype.arraytool.IArrayTool;
import ch.nolix.baseapi.commontype.arraytool.IByteArrayMediator;

/**
 * @author Silvan Wyss
 */
public final class ArrayTool implements IArrayTool {
  /**
   * {@inheritDoc}
   */
  @Override
  public IByteArrayMediator onArray(final byte[] byteArray) {
    return ByteArrayMediator.forByteArray(byteArray);
  }
}
