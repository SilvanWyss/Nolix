/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.commontypetool.arraytool;

import ch.nolix.baseapi.commontypetool.arraytool.IArrayTool;
import ch.nolix.baseapi.commontypetool.arraytool.IByteArrayMediator;

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
