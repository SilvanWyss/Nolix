/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.database.blob;

import java.nio.charset.StandardCharsets;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.PluralLowerCaseVariableNameCatalog;
import ch.nolix.systemapi.database.blob.IBlob;

/**
 * @author Silvan Wyss
 */
public final class Blob implements IBlob {
  private final byte[] bytes;

  /**
   * Creates a new {@link Blob} with the given bytes. The invoker is responsible
   * that the given byte will not be mutated.
   * 
   * @param bytes
   * @throws RuntimeException if the given bytes is null.
   */
  private Blob(final byte[] bytes) {
    Validator.assertThat(bytes).thatIsNamed(PluralLowerCaseVariableNameCatalog.BYTES).isNotNull();

    this.bytes = bytes; // NOSONAR: The current Blob operates on the given original bytes.
  }

  /**
   * @param bytes
   * @return a new {@link Blob} with the given bytes
   * @throws RuntimeException if the given bytes is null
   */
  public static Blob forBytes(final byte[] bytes) {
    final var bytesCopy = bytes.clone();

    return new Blob(bytesCopy);
  }

  /**
   * @param string
   * @return a new {@link Blob} from the given string
   * @throws RuntimeException if the given string is null
   */
  public static Blob fromString(final String string) {
    final var bytes = string.getBytes(StandardCharsets.UTF_8);

    return new Blob(bytes);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getSizeInBytes() {
    return bytes.length;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public byte[] getStoredBytes() {
    return bytes.clone();
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return new String(bytes, StandardCharsets.UTF_8);
  }
}
