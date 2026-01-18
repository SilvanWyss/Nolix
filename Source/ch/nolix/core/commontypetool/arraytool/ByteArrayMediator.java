/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.commontypetool.arraytool;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.commontypetool.arraytool.IByteArrayMediator;
import ch.nolix.coreapi.commontypetool.arraytool.IByteArrayMediatorWithStartIndex;

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
  public IByteArrayMediatorWithStartIndex fromIndex(final int index) {
    return ByteArrayMediatorWithStartIndex.forByteArrayAndStartIndex(byteArray, index);
  }
}
