/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.commontype.arraytool;

import ch.nolix.baseapi.commontype.arraytool.IByteArrayWithStartIndexMediator;
import ch.nolix.baseapi.commontype.arraytool.INextIndexMediator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 */
public final class ByteArrayWithStartIndexMediator implements IByteArrayWithStartIndexMediator {
  private final byte[] byteArray;

  private int index;

  private ByteArrayWithStartIndexMediator(final byte[] byteArray, final int startIndex) {
    if (byteArray == null) {
      throw ArgumentIsNullException.forArgumentName("byte array");
    }

    final var maxStartIndex = byteArray.length - 1;

    if (startIndex < 0 || startIndex > maxStartIndex) {
      throw //
      ArgumentIsOutOfRangeException.forArgumentAndArgumentNameAndRangeWithMinAndMax(
        startIndex,
        LowerCaseVariableNameCatalog.START_INDEX,
        0,
        maxStartIndex);
    }

    this.byteArray = byteArray; //NOSONAR: A ByteArrayMediatorWithStartIndex operates on the original instance.
    index = startIndex;
  }

  public static ByteArrayWithStartIndexMediator forByteArrayAndStartIndex(final byte[] byteArray,
    final int startIndex) {
    return new ByteArrayWithStartIndexMediator(byteArray, startIndex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public INextIndexMediator write(final byte[] bytes) {
    for (var i = 0; i < bytes.length; i++) {
      byteArray[index + i] = bytes[i];
    }

    index += bytes.length;

    return NextIndexMediator.forNextIndex(index);
  }
}
