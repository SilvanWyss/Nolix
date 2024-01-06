//package declaration
package ch.nolix.coreapitest.attributeapitest.mandatoryattributeapitest;

import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.testingapi.testapi.TestCase;

//class
public final class NameHolderTest extends Test {

  //method
  @TestCase
  public void testCase_getNameInQuotes() {

    //setup
    final var testUnit = MockNameHolder.withName("my_name");

    //execution
    final var result = testUnit.getNameInQuotes();

    //verification
    expect(result).isEqualTo("'my_name'");
  }

  //method
  @TestCase
  public void testCase_hasName_whenDoesNotHaveTheGivenName() {

    //setup
    final var testUnit = MockNameHolder.withName("my_name");

    //execution
    final var result = testUnit.hasName("My_name");

    //verification
    expectNot(result);
  }

  //method
  @TestCase
  public void testCase_hasName_whenHasTheGivenName() {

    //setup
    final var testUnit = MockNameHolder.withName("my_name");

    //execution
    final var result = testUnit.hasName("my_name");

    //verification
    expect(result);
  }

  //method
  @TestCase
  public void testCase_hasSameNameAs_whenDoesNotHaveTheSameName() {

    //setup
    final var nameHolder = MockNameHolder.withName("My_name");
    final var testUnit = MockNameHolder.withName("my_name");

    //execution
    final var result = testUnit.hasSameNameAs(nameHolder);

    //verification
    expectNot(result);
  }

  //method
  @TestCase
  public void testCase_hasSameNameAs_whenHasTheSameName() {

    //setup
    final var nameHolder = MockNameHolder.withName("my_name");
    final var testUnit = MockNameHolder.withName("my_name");

    //execution
    final var result = testUnit.hasSameNameAs(nameHolder);

    //verification
    expect(result);
  }
}
