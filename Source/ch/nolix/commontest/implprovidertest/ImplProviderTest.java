//package declaration
package ch.nolix.commontest.implprovidertest;

//own imports
import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.implprovider.ImplProvider;
import ch.nolix.common.test.Test;
import ch.nolix.common.validator.Validator;

//class
public final class ImplProviderTest extends Test {
	
	//static interface
	private static interface ICat {
		
		//method declaration
		String getName();
	}
	
	//static class
	private static final class Cat implements ICat {
		
		//attribute
		private final String name;
		
		//constructor
		@SuppressWarnings("unused")
		public Cat() {
			name = "Garfield";
		}
		
		//constructor
		@SuppressWarnings("unused")
		public Cat(String name) {
			
			Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
			
			this.name = name;
		}
		
		//method
		public String getName() {
			return name;
		}
	}
	
	//method
	@TestCase
	public void test_forInterface_registerImplementation() {
		
		//setup
		final var testUnit = new ImplProvider();
		
		//setup verification
		expectNot(testUnit.forInterface(ICat.class).containsImplementation());
		
		//execution
		testUnit.forInterface(ICat.class).registerImplementation(Cat.class);
		
		//verification
		testUnit.forInterface(ICat.class).containsImplementation();
	}
	
	//method
	@TestCase
	public void test_ofInterface_createInstance_whenDefaultConstructorIsUsed() {
		
		//setup
		final var testUnit = new ImplProvider();
		testUnit.forInterface(ICat.class).registerImplementation(Cat.class);
		
		//execution
		final var result = testUnit.ofInterface(ICat.class).createInstance();
		
		//verification
		expect(result).isOfType(Cat.class);
	}
	
	//method
	@TestCase
	public void test_ofInterface_createInstance_whenConstructorWithParametersIsUsed() {
		
		//setup
		final var testUnit = new ImplProvider();
		testUnit.forInterface(ICat.class).registerImplementation(Cat.class);
		
		//execution
		final var result = testUnit.ofInterface(ICat.class).createInstance("Simba");
		
		//verification
		expect(result).isOfType(Cat.class);
		expect(result.getName()).isEqualTo("Simba");
	}
}
