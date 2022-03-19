//package declaration
package ch.nolix.core.demo;

//own imports
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.errorcontrol.validator.Validator;

//class
public final class Cat {
	
	//attribute
	private final String name;
	
	//constructor
	public Cat(final String name) {
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		
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
