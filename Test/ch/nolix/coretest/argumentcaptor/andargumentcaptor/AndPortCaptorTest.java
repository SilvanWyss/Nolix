package ch.nolix.coretest.argumentcaptor.andargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.core.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.core.argumentcaptor.andargumentcaptor.AndPortCaptor;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.testing.standardtest.StandardTest;

final class AndPortCaptorTest extends StandardTest {
  @Test
  void testCase_andPort_whenHasNext() {
    //parameter definition
    final var port = 8000;

    //setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndPortCaptor<>(andNameCaptor);

    //execution
    final var result = testUnit.andPort(port);

    //verification
    expect(testUnit.getPort()).isEqualTo(port);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_andPort_whenDoesNotHaveNext() {
    //setup
    final var testUnit = new AndPortCaptor<>();

    //execution & verification
    expectRunning(() -> testUnit.andPort(8000))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_andHttpPort_whenHasNext() {
    //setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndPortCaptor<>(andNameCaptor);

    //execution
    final var result = testUnit.andHttpPort();

    //verification
    expect(testUnit.getPort()).isEqualTo(80);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_andHttpsPort_whenHasNext() {
    //setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndPortCaptor<>(andNameCaptor);

    //execution
    final var result = testUnit.andHttpsPort();

    //verification
    expect(testUnit.getPort()).isEqualTo(443);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_andMsSqlPort_whenHasNext() {
    //setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndPortCaptor<>(andNameCaptor);

    //execution
    final var result = testUnit.andMsSqlPort();

    //verification
    expect(testUnit.getPort()).isEqualTo(1433);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_getPort_whenDoesNotHavePort() {
    //setup
    final var testUnit = new AndPortCaptor<>();

    //execution & verification
    expectRunning(testUnit::getPort).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
