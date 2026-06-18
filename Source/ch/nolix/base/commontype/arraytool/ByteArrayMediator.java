/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.commontype.arraytool;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.commontype.arraytool.IByteArrayMediator;
import ch.nolix.baseapi.commontype.arraytool.IByteArrayWithStartIndexMediator;

/**
 * @author Silvan Wyss
 */
public final class ByteArrayMediator implements IByteArrayMediator {
  private final byte[] byteArray;

  private ByteArrayMediator(final byte[] byteArray) {
    Validator.assertThat(byteArray).thatIsNamed("byte array").isNotNull();

    this.byteArray = byteArray; //NOSONAR: A ByteArrayMediator operates on the original instance.
  }

  public static ByteArrayMediator forByteArray(final byte[] byteArray) {
    return new ByteArrayMediator(byteArray);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IByteArrayWithStartIndexMediator fromIndex(final int index) {
    return ByteArrayWithStartIndexMediator.forByteArrayAndStartIndex(byteArray, index);
  }
}
