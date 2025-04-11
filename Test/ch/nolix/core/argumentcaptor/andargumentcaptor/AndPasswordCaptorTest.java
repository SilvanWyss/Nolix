package ch.nolix.core.argumentcaptor.andargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.testing.standardtest.StandardTest;

final class AndPasswordCaptorTest extends StandardTest {

  @Test
  void testCase_andPassword_whenHasNext() {

    //parameter definition
    final var password = "my_password";

    //setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndPasswordCaptor<>(andNameCaptor);

    //execution
    final var result = testUnit.andPassword(password);

    //verification
    expect(testUnit.getPassword()).isEqualTo(password);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_andPassword_whenDoesNotHaveNext() {

    //setup
    final var testUnit = new AndPasswordCaptor<>();

    //execution & verification
    expectRunning(() -> testUnit.andPassword("my_password"))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_getPassword_whenDoesNotHavePassword() {

    //setup
    final var testUnit = new AndPasswordCaptor<>();

    //execution & verification
    expectRunning(testUnit::getPassword).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
