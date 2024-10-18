package ch.nolix.core.programstructure.data;

import java.nio.charset.StandardCharsets;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programstructureapi.dataapi.IBinaryObject;

public final class BinaryObject implements IBinaryObject {

  private final byte[] bytes;

  private BinaryObject(final byte[] bytes) {

    GlobalValidator.assertThat(bytes).thatIsNamed("bytes").isNotNull();

    this.bytes = bytes; //NOSONAR: A BinaryObject operates on the original instance.
  }

  public static BinaryObject forBytes(final byte[] bytes) {
    return new BinaryObject(bytes);
  }

  public static BinaryObject fromString(final String string) {
    return new BinaryObject(string.getBytes(StandardCharsets.UTF_8));
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
