/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.misc.dataobject;

import java.nio.charset.StandardCharsets;

import ch.nolix.base.errorcontrol.validator.Validator;
import ch.nolix.baseapi.misc.dataobject.IBlob;

/**
 * @author Silvan Wyss
 */
public final class Blob implements IBlob {
  private final byte[] bytes;

  private Blob(final byte[] bytes) {
    Validator.assertThat(bytes).thatIsNamed("bytes").isNotNull();

    this.bytes = bytes; //NOSONAR: A BinaryObject operates on the original instance.
  }

  public static Blob forBytes(final byte[] bytes) {
    return new Blob(bytes);
  }

  public static Blob fromString(final String string) {
    return new Blob(string.getBytes(StandardCharsets.UTF_8));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public byte[] getStoredBytes() {
    return bytes; //NOSONAR: A BinaryObject returns the original instance.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getSize() {
    return bytes.length;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return new String(bytes, StandardCharsets.UTF_8);
  }
}
