//package declaration
package ch.nolix.coretest.errorcontroltest.validatortest;

//own imports
import ch.nolix.core.errorcontrol.validator.ByteMediator;
import ch.nolix.core.errorcontrol.validator.DoubleMediator;
import ch.nolix.core.errorcontrol.validator.ExtendedContainerMediator;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.errorcontrol.validator.LongMediator;
import ch.nolix.core.errorcontrol.validator.MethodMediator;
import ch.nolix.core.errorcontrol.validator.StringMediator;
import ch.nolix.core.programatom.function.FunctionCatalogue;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.testingapi.testapi.TestCase;

//class
public final class GlobalValidatorTest extends Test {

  //method
  @TestCase
  public void testCase_assertThat_whenTheGivenArgumentIsAByte() {

    //setup
    final var argument = (byte) 127;

    //execution
    final var result = GlobalValidator.assertThat(argument);

    //verification
    expect(result).isOfType(ByteMediator.class);
  }

  //method
  @TestCase
  public void testCase_assertThat_whenTheGivenArgumentIsAByteArray() {

    //setup
    final var argument = new byte[100];

    //execution
    final var result = GlobalValidator.assertThat(argument);

    //verification
    expect(result).isOfType(ExtendedContainerMediator.class);
  }

  //method
  @TestCase
  public void testCase_assertThat_whenTheGivenArgumentIsADouble() {

    //setup
    final var argument = 1.0;

    //execution
    final var result = GlobalValidator.assertThat(argument);

    //verification
    expect(result).isOfType(DoubleMediator.class);
  }

  //method
  @TestCase
  public void testCase_assertThat_whenTheGivenArgumentIsADoubleArray() {

    //setup
    final var argument = new double[100];

    //execution
    final var result = GlobalValidator.assertThat(argument);

    //verification
    expect(result).isOfType(ExtendedContainerMediator.class);
  }

  //method
  @TestCase
  public void testCase_assertThat_whenTheGivenArgumentIsAnInt() {

    //setup
    final var argument = 50;

    //execution
    final var result = GlobalValidator.assertThat(argument);

    //verification
    expect(result).isOfType(LongMediator.class);
  }

  //method
  @TestCase
  public void testCase_assertThat_whenTheGivenArgumentIsAnIntArray() {

    //setup
    final var argument = new int[100];

    //execution
    final var result = GlobalValidator.assertThat(argument);

    //verification
    expect(result).isOfType(ExtendedContainerMediator.class);
  }

  //method
  @TestCase
  public void testCase_assertThat_whenTheGivenArgumentIsALong() {

    //setup
    final var argument = 50L;

    //execution
    final var result = GlobalValidator.assertThat(argument);

    //verification
    expect(result).isOfType(LongMediator.class);
  }

  //method
  @TestCase
  public void testCase_assertThat_whenTheGivenArgumentIsALongArray() {

    //setup
    final var argument = new long[100];

    //execution
    final var result = GlobalValidator.assertThat(argument);

    //verification
    expect(result).isOfType(ExtendedContainerMediator.class);
  }

  //method
  @TestCase
  public void testCase_assertThat_whenTheGivenArgumentIsAMethod() throws NoSuchMethodException {

    //setup
    final var argument = FunctionCatalogue.class.getMethod("doNothing");

    //execution
    final var result = GlobalValidator.assertThat(argument);

    //verification
    expect(result).isOfType(MethodMediator.class);
  }

  //method
  @TestCase
  public void testCase_assertThat_whenTheGivenArgumentIsAString() {

    //setup
    final var argument = "lorem ipsum";

    //execution
    final var result = GlobalValidator.assertThat(argument);

    //verification
    expect(result).isOfType(StringMediator.class);
  }
}
