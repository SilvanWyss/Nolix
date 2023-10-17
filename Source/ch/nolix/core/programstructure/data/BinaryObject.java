//package declaration
package ch.nolix.core.programstructure.data;

//Java imports
import java.nio.charset.StandardCharsets;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programstructureapi.dataapi.IBinaryObject;

//class
public final class BinaryObject implements IBinaryObject {

  //multi-attribute
  private final byte[] bytes;

  //constructor
  private BinaryObject(final byte[] bytes) {

    GlobalValidator.assertThat(bytes).thatIsNamed("bytes").isNotNull();

    this.bytes = bytes; //NOSONAR: A BinaryObject operates on the original instance.
  }

  //static method
  public static BinaryObject forBytes(final byte[] bytes) {
    return new BinaryObject(bytes);
  }

  //static method
  public static BinaryObject fromString(final String string) {
    return new BinaryObject(string.getBytes(StandardCharsets.UTF_8));
  }

  //method
  @Override
  public byte[] getStoredBytes() {
    return bytes; //NOSONAR: A BinaryObject returns the original instance.
  }

  //method
  @Override
  public int getSize() {
    return bytes.length;
  }

  //method
  @Override
  public String toString() {
    return new String(bytes, StandardCharsets.UTF_8);
  }
}
