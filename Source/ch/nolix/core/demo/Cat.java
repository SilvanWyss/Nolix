//package declaration
package ch.nolix.core.demo;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.name.LowerCaseCatalogue;

//class
public final class Cat {
	
	//attribute
	private final String name;
	
	//constructor
	public Cat(final String name) {
		
		GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		
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
