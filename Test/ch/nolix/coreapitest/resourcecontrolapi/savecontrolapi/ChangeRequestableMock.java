package ch.nolix.coreapitest.resourcecontrolapi.savecontrolapi;

import ch.nolix.coreapi.resourcecontrol.savecontrol.ChangeRequestable;

public final class ChangeRequestableMock implements ChangeRequestable {
  private final boolean hasChanges;

  private ChangeRequestableMock(final boolean hasChanges) {
    this.hasChanges = hasChanges;
  }

  public static ChangeRequestableMock withHasChangesFlag(final boolean hasChanges) {
    return new ChangeRequestableMock(hasChanges);
  }

  @Override
  public boolean hasChanges() {
    return hasChanges;
  }
}
