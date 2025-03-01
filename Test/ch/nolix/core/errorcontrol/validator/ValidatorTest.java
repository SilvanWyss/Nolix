package ch.nolix.core.errorcontrol.validator;

import org.junit.jupiter.api.Test;

import ch.nolix.core.programatom.function.GlobalFunctionService;
import ch.nolix.core.testing.standardtest.StandardTest;

final class ValidatorTest extends StandardTest {

  @Test
  void testCase_assertThat_whenTheGivenArgumentIsAByte() {

    //setup
    final var argument = (byte) 127;

    //execution
    final var result = Validator.assertThat(argument);

    //verification
    expect(result).isOfType(ByteMediator.class);
  }

  @Test
  void testCase_assertThat_whenTheGivenArgumentIsAByteArray() {

    //setup
    final var argument = new byte[100];

    //execution
    final var result = Validator.assertThat(argument);

    //verification
    expect(result).isOfType(ExtendedIterableMediator.class);
  }

  @Test
  void testCase_assertThat_whenTheGivenArgumentIsADouble() {

    //setup
    final var argument = 1.0;

    //execution
    final var result = Validator.assertThat(argument);

    //verification
    expect(result).isOfType(DoubleMediator.class);
  }

  @Test
  void testCase_assertThat_whenTheGivenArgumentIsADoubleArray() {

    //setup
    final var argument = new double[100];

    //execution
    final var result = Validator.assertThat(argument);

    //verification
    expect(result).isOfType(ExtendedIterableMediator.class);
  }

  @Test
  void testCase_assertThat_whenTheGivenArgumentIsAnInt() {

    //setup
    final var argument = 50;

    //execution
    final var result = Validator.assertThat(argument);

    //verification
    expect(result).isOfType(LongMediator.class);
  }

  @Test
  void testCase_assertThat_whenTheGivenArgumentIsAnIntArray() {

    //setup
    final var argument = new int[100];

    //execution
    final var result = Validator.assertThat(argument);

    //verification
    expect(result).isOfType(ExtendedIterableMediator.class);
  }

  @Test
  void testCase_assertThat_whenTheGivenArgumentIsALong() {

    //setup
    final var argument = 50L;

    //execution
    final var result = Validator.assertThat(argument);

    //verification
    expect(result).isOfType(LongMediator.class);
  }

  @Test
  void testCase_assertThat_whenTheGivenArgumentIsALongArray() {

    //setup
    final var argument = new long[100];

    //execution
    final var result = Validator.assertThat(argument);

    //verification
    expect(result).isOfType(ExtendedIterableMediator.class);
  }

  @Test
  void testCase_assertThat_whenTheGivenArgumentIsAMethod() throws NoSuchMethodException {

    //setup
    final var argument = GlobalFunctionService.class.getMethod("doNothing");

    //execution
    final var result = Validator.assertThat(argument);

    //verification
    expect(result).isOfType(MethodMediator.class);
  }

  @Test
  void testCase_assertThat_whenTheGivenArgumentIsAString() {

    //setup
    final var argument = "lorem ipsum";

    //execution
    final var result = Validator.assertThat(argument);

    //verification
    expect(result).isOfType(StringMediator.class);
  }
}
