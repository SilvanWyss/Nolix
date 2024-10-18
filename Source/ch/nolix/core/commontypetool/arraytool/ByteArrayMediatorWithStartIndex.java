package ch.nolix.core.commontypetool.arraytool;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.commontypetoolapi.arraytoolapi.IByteArrayMediatorWithStartIndex;
import ch.nolix.coreapi.commontypetoolapi.arraytoolapi.INextIndexMediator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

public final class ByteArrayMediatorWithStartIndex implements IByteArrayMediatorWithStartIndex {

  private final byte[] byteArray;

  private int index;

  private ByteArrayMediatorWithStartIndex(final byte[] byteArray, final int startIndex) {

    GlobalValidator.assertThat(byteArray).thatIsNamed("byte array").isNotNull();

    GlobalValidator
      .assertThat(startIndex)
      .thatIsNamed(LowerCaseVariableCatalogue.START_INDEX)
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
