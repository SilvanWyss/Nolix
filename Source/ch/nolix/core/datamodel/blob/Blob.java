package ch.nolix.core.datamodel.blob;

import java.nio.charset.StandardCharsets;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.datamodelapi.blobapi.IBlob;

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

  @Override
  public byte[] getStoredBytes() {
    return bytes; //NOSONAR: A BinaryObject returns the original instance.
  }

  @Override
  public int getSize() {
    return bytes.length;
  }

  @Override
  public String toString() {
    return new String(bytes, StandardCharsets.UTF_8);
  }
}
