package ch.nolix.coreapi.datamodelapi.entityrequestapi;

public final class AbstractnessRequestableMock implements AbstractnessRequestable {

  private final boolean isAbstract;

  private AbstractnessRequestableMock(final boolean isAbstract) {
    this.isAbstract = isAbstract;
  }

  public static AbstractnessRequestableMock withIsAbstractFlag(final boolean isAbstractFlag) {
    return new AbstractnessRequestableMock(isAbstractFlag);
  }

  @Override
  public boolean isAbstract() {
    return isAbstract;
  }
}
