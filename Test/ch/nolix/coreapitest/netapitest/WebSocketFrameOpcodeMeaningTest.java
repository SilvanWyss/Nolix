package ch.nolix.coreapitest.netapitest;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.netapi.websocketapi.WebSocketFrameOpcodeMeaning;

final class WebSocketFrameOpcodeMeaningTest extends StandardTest {

  @MethodSource
  private static IContainer<Arguments> getWebSocketFrameOpcodeMeaningsAndTheirNumbers() {
    return //
    ImmutableList.withElement(
      Arguments.of(WebSocketFrameOpcodeMeaning.CONTINUATION_FRAME, 0x0),
      Arguments.of(WebSocketFrameOpcodeMeaning.TEXT_FRAME, 0x1),
      Arguments.of(WebSocketFrameOpcodeMeaning.BINARY_FRAME, 0x2),
      Arguments.of(WebSocketFrameOpcodeMeaning.CONNECTION_CLOSE, 0x8),
      Arguments.of(WebSocketFrameOpcodeMeaning.PING, 0x9),
      Arguments.of(WebSocketFrameOpcodeMeaning.PONG, 0xA));
  }

  @MethodSource
  private static IContainer<Arguments> getNumbersAndTheirWebSocketFrameOpcodeMeanings() {
    return //
    ImmutableList.withElement(
      Arguments.of(0x0, WebSocketFrameOpcodeMeaning.CONTINUATION_FRAME, 0x0),
      Arguments.of(0x1, WebSocketFrameOpcodeMeaning.TEXT_FRAME, 0x1),
      Arguments.of(0x2, WebSocketFrameOpcodeMeaning.BINARY_FRAME, 0x2),
      Arguments.of(0x8, WebSocketFrameOpcodeMeaning.CONNECTION_CLOSE, 0x8),
      Arguments.of(0x9, WebSocketFrameOpcodeMeaning.PING, 0x9),
      Arguments.of(0xA, WebSocketFrameOpcodeMeaning.PONG),
      Arguments.of(0xB, WebSocketFrameOpcodeMeaning.RESERVED),
      Arguments.of(0xC, WebSocketFrameOpcodeMeaning.RESERVED),
      Arguments.of(0xD, WebSocketFrameOpcodeMeaning.RESERVED),
      Arguments.of(0xE, WebSocketFrameOpcodeMeaning.RESERVED),
      Arguments.of(0xF, WebSocketFrameOpcodeMeaning.RESERVED));
  }

  @MethodSource
  private static IContainer<Arguments> getWebSocketFrameOpcodeMeaningsThatDoNotHaveANumber() {
    return ImmutableList.withElement(Arguments.of(WebSocketFrameOpcodeMeaning.RESERVED));
  }

  @ParameterizedTest
  @MethodSource("getNumbersAndTheirWebSocketFrameOpcodeMeanings")
  void testCase_toNumber_fromNumber(
    final int number,
    final WebSocketFrameOpcodeMeaning expectedWebSocketFrameOpcodeMeaning) {

    //execution
    final var result = WebSocketFrameOpcodeMeaning.fromNumber(number);

    //verification
    expect(result).isEqualTo(expectedWebSocketFrameOpcodeMeaning);
  }

  @ParameterizedTest
  @MethodSource("getWebSocketFrameOpcodeMeaningsAndTheirNumbers")
  void testCase_toNumber_whenHaseANumber(final WebSocketFrameOpcodeMeaning testUnit, final int expectedNumber) {

    //execution
    final var result = testUnit.toNumber();

    //verification
    expect(result).isEqualTo(expectedNumber);
  }

  @ParameterizedTest
  @MethodSource("getWebSocketFrameOpcodeMeaningsThatDoNotHaveANumber")
  void testCase_toNumber_whenDoesNotHaveASingleNumber(final WebSocketFrameOpcodeMeaning testUnit) {

    //execution & verification
    expectRunning(testUnit::toNumber).throwsException().ofType(InvalidArgumentException.class);
  }
}
