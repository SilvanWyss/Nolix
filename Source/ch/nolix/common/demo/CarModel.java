//package declaration
package ch.nolix.common.demo;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;

//class
public final class CarModel {
	
	//attribute
	private final String name;
	
	//attribute
	private final int topSpeedInKilometerPerHour;
	
	//constructor
	public CarModel(final String name, final int topSpeedInKilometerPerHour) {
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		Validator.assertThat(topSpeedInKilometerPerHour).thatIsNamed("top speed in kilometer per hour").isPositive();
		
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
