/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.commontype.arraytool;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.commontype.arraytool.IByteArrayWithStartIndexMediator;
import ch.nolix.baseapi.commontype.arraytool.INextIndexMediator;
import ch.nolix.baseapi.misc.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 */
public final class ByteArrayWithStartIndexMediator implements IByteArrayWithStartIndexMediator {
  private final byte[] byteArray;

  private int index;

  private ByteArrayWithStartIndexMediator(final byte[] byteArray, final int startIndex) {
    Validator.assertThat(byteArray).thatIsNamed("byte array").isNotNull();

    Validator
      .assertThat(startIndex)
      .thatIsNamed(LowerCaseVariableNameCatalog.START_INDEX)
      .isBetween(0, byteArray.length);

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
