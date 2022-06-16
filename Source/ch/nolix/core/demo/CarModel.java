//package declaration
package ch.nolix.core.demo;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.name.LowerCaseCatalogue;

//class
public final class CarModel {
	
	//attribute
	private final String name;
	
	//attribute
	private final int topSpeedInKilometerPerHour;
	
	//constructor
	public CarModel(final String name, final int topSpeedInKilometerPerHour) {
		
		GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		GlobalValidator.assertThat(topSpeedInKilometerPerHour).thatIsNamed("top speed in kilometer per hour").isPositive();
		
		this.name = name;
		this.topSpeedInKilometerPerHour = topSpeedInKilometerPerHour;
	}
	
	//method
	public String getName() {
		return name;
	}
	
	//method
	public int getTopSpeedInKilometerPerHour() {
		return topSpeedInKilometerPerHour;
	}
}
