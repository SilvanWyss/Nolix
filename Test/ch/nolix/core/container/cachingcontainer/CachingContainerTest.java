package ch.nolix.core.container.cachingcontainer;

import org.junit.jupiter.api.Test;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.testing.standardtest.StandardTest;

final class CachingContainerTest extends StandardTest {

  @Test
  void testCase_constructor() {

    //execution
    final var result = new CachingContainer<String>();

    //verification
    expect(result.isEmpty()).isTrue();
  }

  @Test
  void testCase_getStoredById() {

    //setup
    final var testUnit = new CachingContainer<String>();
    final var garfield = "Garfield";
    final var garfieldId = testUnit.registerAndGetId(garfield);

    //execution
    final var result = testUnit.getStoredById(garfieldId);

    //verification
    expect(result).is(garfield);
  }

  @Test
  void testCase_getStoredById_whenForTheGivenIdAnElementIsNotRegistered() {

    //setup
    final var testUnit = new CachingContainer<String>();

    //execution
    expectRunning(() -> testUnit.getStoredById("G"))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_registerAndGetId() {

    //setup
    final var testUnit = new CachingContainer<String>();
    final var garfield = "Garfield";

    //execution
    final var result = testUnit.registerAndGetId(garfield);

    //verification
    expect(testUnit.getCount()).isEqualTo(1);
    expect(testUnit.containsWithId(result)).isTrue();
    expect(testUnit.contains(garfield));
  }

  @Test
  void testCase_registerAndGetId_whenTheGivenElementIsAlreadyRegistered() {

    //setup
    final var testUnit = new CachingContainer<String>();
    final var garfield = "Garfield";
    testUnit.registerAndGetId(garfield);

    //execution & verification
    expectRunning(() -> testUnit.registerAndGetId(garfield)).throwsException();
  }

  @Test
  void testCase_registerAt() {

    //setup
    final var testUnit = new CachingContainer<String>();
    final var garfieldId = "G";
    final var garfield = "Garfield";

    //execution
    testUnit.registerAtId(garfieldId, garfield);

    //verification
    expect(testUnit.getCount()).isEqualTo(1);
    expect(testUnit.containsWithId(garfieldId)).isTrue();
    expect(testUnit.contains(garfield));
  }

  @Test
  void testCase_registerAt_whenTheGivenElementIsAlreadyRegistered() {

    //setup
    final var testUnit = new CachingContainer<String>();
    final var garfieldId = "G";
    final var garfield = "Garfield";
    testUnit.registerAtId(garfieldId, garfield);

    //execution & verification
    expectRunning(() -> testUnit.registerAtId(garfieldId, garfield)).throwsException();
  }

  @Test
  void testCase_registerIfNotRegisterAndGetId() {

    //setup
    final var testUnit = new CachingContainer<String>();
    final var garfield = "Garfield";

    //execution
    final var result = testUnit.registerIfNotRegisteredAndGetId(garfield);

    //verification
    expect(testUnit.getCount()).isEqualTo(1);
    expect(testUnit.containsWithId(result)).isTrue();
    expect(testUnit.contains(garfield));
  }

  @Test
  void testCase_registerIfNotRegisterAndGetId_whenTheGivenElementIsAlreadyRegistered() {

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
