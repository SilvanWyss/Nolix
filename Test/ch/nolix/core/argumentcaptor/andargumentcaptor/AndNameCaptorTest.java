package ch.nolix.core.argumentcaptor.andargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.testing.standardtest.StandardTest;

final class AndNameCaptorTest extends StandardTest {

  @Test
  void testCase_andName_whenHasNext() {

    //parameter definition
    final var name = "my_name";

    //setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndNameCaptor<>(andNameCaptor);

    //execution
    final var result = testUnit.andName(name);

    //verification
    expect(testUnit.getName()).isEqualTo(name);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_andName_whenDoesNotHaveNext() {

    //setup
    final var testUnit = new AndNameCaptor<>();

    //execution & verification
    expectRunning(() -> testUnit.andName("my_name"))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_getName_whenDoesNotHaveName() {

    //setup
    final var testUnit = new AndNameCaptor<>();

    //execution & verification
    expectRunning(testUnit::getName).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
