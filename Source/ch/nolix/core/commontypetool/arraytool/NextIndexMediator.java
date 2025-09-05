package ch.nolix.core.commontypetool.arraytool;

import ch.nolix.coreapi.commontypetool.arraytool.INextIndexMediator;

public final class NextIndexMediator implements INextIndexMediator {
  private final int nextIndex;

  private NextIndexMediator(final int nextIndex) {
    this.nextIndex = nextIndex;
  }

  public static NextIndexMediator forNextIndex(final int nextIndex) {
    return new NextIndexMediator(nextIndex);
  }

  @Override
  public int andGetNextIndex() {
    return nextIndex;
  }
}
