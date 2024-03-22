//package declaration
package ch.nolix.coreapitest.datamodelapitest.entityrequestapitest;

//own imports
import ch.nolix.coreapi.datamodelapi.entityrequestapi.AbstractnessRequestable;

//class
public final class AbstractnessRequestableMock implements AbstractnessRequestable {

  //method
  private final boolean isAbstract;

  //constructor
  private AbstractnessRequestableMock(final boolean isAbstract) {
    this.isAbstract = isAbstract;
  }

  //static method
  public static AbstractnessRequestableMock withIsAbstractFlag(final boolean isAbstractFlag) {
    return new AbstractnessRequestableMock(isAbstractFlag);
  }

  //method
  @Override
  public boolean isAbstract() {
    return isAbstract;
  }
}
