package ch.nolix.core.commontypetool.arraytool;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.commontypetool.arraytool.IByteArrayMediatorWithStartIndex;
import ch.nolix.coreapi.commontypetool.arraytool.INextIndexMediator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

public final class ByteArrayMediatorWithStartIndex implements IByteArrayMediatorWithStartIndex {
  private final byte[] byteArray;

  private int index;

  private ByteArrayMediatorWithStartIndex(final byte[] byteArray, final int startIndex) {
    Validator.assertThat(byteArray).thatIsNamed("byte array").isNotNull();

    Validator
      .assertThat(startIndex)
      .thatIsNamed(LowerCaseVariableCatalog.START_INDEX)
      .isBetween(0, byteArray.length);

    this.byteArray = byteArray; //NOSONAR: A ByteArrayMediatorWithStartIndex operates on the original instance.
    index = startIndex;
  }

  public static ByteArrayMediatorWithStartIndex forByteArrayAndStartIndex(final byte[] byteArray,
    final int startIndex) {
    return new ByteArrayMediatorWithStartIndex(byteArray, startIndex);
  }

  @Override
  public INextIndexMediator write(final byte[] bytes) {
    for (var i = 0; i < bytes.length; i++) {
      byteArray[index + i] = bytes[i];
    }

    index += bytes.length;

    return NextIndexMediator.forNextIndex(index);
  }
}
