//package declaration
package ch.nolix.coretest.errorcontroltest.validatortest;

//Java imports
import java.lang.reflect.Method;

//own imports
import ch.nolix.core.errorcontrol.validator.ByteMediator;
import ch.nolix.core.errorcontrol.validator.DoubleMediator;
import ch.nolix.core.errorcontrol.validator.ExtendedContainerMediator;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.errorcontrol.validator.LongMediator;
import ch.nolix.core.errorcontrol.validator.MethodMediator;
import ch.nolix.core.errorcontrol.validator.StringMediator;
import ch.nolix.core.programatom.function.FunctionCatalogue;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class GlobalValidatorTest extends Test {
	
	//method
	@TestCase
	public void testCase_assertThat_whenTheGivenArgumentIsAByte() {
		
		//setup
		final byte argument = 127;
		
		//execution
		final var result = GlobalValidator.assertThat(argument);
		
		//verification
		expect(result).isOfType(ByteMediator.class);
	}
	
	//method
	@TestCase
	public void testCase_assertThat_whenTheGivenArgumentIsAByteArray() {
		
		//setup
		final byte[] argument = new byte[100];
		
		//execution
		final var result = GlobalValidator.assertThat(argument);
		
		//verification
		expect(result).isOfType(ExtendedContainerMediator.class);
	}
	
	//method
	@TestCase
	public void testCase_assertThat_whenTheGivenArgumentIsADouble() {
		
		//setup
		final double argument = 1.0;
		
		//execution
		final var result = GlobalValidator.assertThat(argument);
		
		//verification
		expect(result).isOfType(DoubleMediator.class);
	}
	
	//method
	@TestCase
	public void testCase_assertThat_whenTheGivenArgumentIsADoubleArray() {
		
		//setup
		final double[] argument = new double[100];
		
		//execution
		final var result = GlobalValidator.assertThat(argument);
		
		//verification
		expect(result).isOfType(ExtendedContainerMediator.class);
	}
	
	//method
	@TestCase
	public void testCase_assertThat_whenTheGivenArgumentIsAnInt() {
		
		//setup
		final int argument = 50;
		
		//execution
		final var result = GlobalValidator.assertThat(argument);
		
		//verification
		expect(result).isOfType(LongMediator.class);
	}
	
	//method
	@TestCase
	public void testCase_assertThat_whenTheGivenArgumentIsAnIntArray() {
		
		//setup
		final int[] argument = new int[100];
		
		//execution
		final var result = GlobalValidator.assertThat(argument);
		
		//verification
		expect(result).isOfType(ExtendedContainerMediator.class);
	}
	
	//method
	@TestCase
	public void testCase_assertThat_whenTheGivenArgumentIsALong() {
		
		//setup
		final long argument = 50L;
		
		//execution
		final var result = GlobalValidator.assertThat(argument);
		
		//verification
		expect(result).isOfType(LongMediator.class);
	}
	
	//method
	@TestCase
	public void testCase_assertThat_whenTheGivenArgumentIsALongArray() {
		
		//setup
		final long[] argument = new long[100];
		
		//execution
		final var result = GlobalValidator.assertThat(argument);
		
		//verification
		expect(result).isOfType(ExtendedContainerMediator.class);
	}
	
	//method
	@TestCase
	public void testCase_assertThat_whenTheGivenArgumentIsAMethod() throws NoSuchMethodException {
		
		//setup
		final Method argument = FunctionCatalogue.class.getMethod("doNothing");
		
		//execution
		final var result = GlobalValidator.assertThat(argument);
		
		//verification
		expect(result).isOfType(MethodMediator.class);
	}
	
	//method
	@TestCase
	public void testCase_assertThat_whenTheGivenArgumentIsAString() {
		
		//setup
		final String argument = "lorem ipsum";
		
		//execution
		final var result = GlobalValidator.assertThat(argument);
		
		//verification
		expect(result).isOfType(StringMediator.class);
	}
}
