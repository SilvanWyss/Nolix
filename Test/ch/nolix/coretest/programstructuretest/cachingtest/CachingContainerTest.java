//package declaration
package ch.nolix.coretest.programstructuretest.cachingtest;

import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programstructure.caching.CachingContainer;
import ch.nolix.core.testing.test.StandardTest;

//class
public final class CachingContainerTest extends StandardTest {

  //method
  @Test
  public void testCase_constructor() {

    //execution
    final var result = new CachingContainer<String>();

    //verification
    expect(result.isEmpty());
  }

  //method
  @Test
  public void testCase_getStoredById() {

    //setup
    final var testUnit = new CachingContainer<String>();
    final var garfield = "Garfield";
    final var garfieldId = testUnit.registerAndGetId(garfield);

    //execution
    final var result = testUnit.getStoredById(garfieldId);

    //verification
    expect(result).is(garfield);
  }

  //method
  @Test
  public void testCase_getStoredById_whenForTheGivenIdAnElementIsNotRegistered() {

    //setup
    final var testUnit = new CachingContainer<String>();

    //execution
    expectRunning(() -> testUnit.getStoredById("G")).throwsException().ofType(InvalidArgumentException.class);
  }

  //method
  @Test
  public void testCase_registerAndGetId() {

    //setup
    final var testUnit = new CachingContainer<String>();
    final var garfield = "Garfield";

    //execution
    final var result = testUnit.registerAndGetId(garfield);

    //verification
    expect(testUnit.getElementCount()).isEqualTo(1);
    expect(testUnit.containsWithId(result));
    expect(testUnit.contains(garfield));
  }

  //method
  @Test
  public void testCase_registerAndGetId_whenTheGivenElementIsAlreadyRegistered() {

    //setup
    final var testUnit = new CachingContainer<String>();
    final var garfield = "Garfield";
    testUnit.registerAndGetId(garfield);

    //execution & verification
    expectRunning(() -> testUnit.registerAndGetId(garfield)).throwsException();
  }

  //method
  @Test
  public void testCase_registerAt() {

    //setup
    final var testUnit = new CachingContainer<String>();
    final var garfieldId = "G";
    final var garfield = "Garfield";

    //execution
    testUnit.registerAtId(garfieldId, garfield);

    //verification
    expect(testUnit.getElementCount()).isEqualTo(1);
    expect(testUnit.containsWithId(garfieldId));
    expect(testUnit.contains(garfield));
  }

  //method
  @Test
  public void testCase_registerAt_whenTheGivenElementIsAlreadyRegistered() {

    //setup
    final var testUnit = new CachingContainer<String>();
    final var garfieldId = "G";
    final var garfield = "Garfield";
    testUnit.registerAtId(garfieldId, garfield);

    //execution & verification
    expectRunning(() -> testUnit.registerAtId(garfieldId, garfield)).throwsException();
  }

  //method
  @Test
  public void testCase_registerIfNotRegisterAndGetId() {

    //setup
    final var testUnit = new CachingContainer<String>();
    final var garfield = "Garfield";

    //execution
    final var result = testUnit.registerIfNotRegisteredAndGetId(garfield);

    //verification
    expect(testUnit.getElementCount()).isEqualTo(1);
    expect(testUnit.containsWithId(result));
    expect(testUnit.contains(garfield));
  }

  //method
  @Test
  public void testCase_registerIfNotRegisterAndGetId_whenTheGivenElementIsAlreadyRegistered() {

    //setup
    final var testUnit = new CachingContainer<String>();
    final var garfieldId = "G";
    final var garfield = "Garfield";
    testUnit.registerAtId(garfieldId, garfield);

    //execution
    final var result = testUnit.registerIfNotRegisteredAndGetId(garfield);

    //verification
    expect(result).isEqualTo(garfieldId);
  }
}
