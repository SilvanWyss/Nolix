//package declaration
package ch.nolix.common.simpleDemoObject;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.validator.Validator;

//class
public final class Cat {
	
	//attribute
	private final String name;
	
	//constructor
	public Cat(final String name) {
		
		Validator.assertThat(name).thatIsNamed(VariableNameCatalogue.NAME).isNotBlank();
		
		this.name = name;
	}
	
	//method
	public String getName() {
		return name;
	}
	
	//method
	public void meow() {
		System.out.println("Meow!");
	}
}
