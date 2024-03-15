//package declaration
package ch.nolix.coretest.errorcontroltest.validatortest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class ValidatorTestPool extends TestPool {

  //constructor
  public ValidatorTestPool() {
    super(
      ContainerMediatorTest.class,
      ExtendedStringMediatorTest.class,
      GlobalValidatorTest.class,
      LongMediatorTest.class,
      MultiDoubleMediatorTest.class,
      StringMediatorTest.class,
      TypeMediatorTest.class);
  }
}
