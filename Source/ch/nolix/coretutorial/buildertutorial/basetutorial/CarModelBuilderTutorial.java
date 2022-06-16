package ch.nolix.coretutorial.buildertutorial.basetutorial;

//own imports
import ch.nolix.core.builder.base.ArgumentCapturer;
import ch.nolix.core.builder.base.BaseArgumentCapturer;
import ch.nolix.core.builder.base.TerminalArgumentCapturer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.name.LowerCaseCatalogue;

public final class CarModelBuilderTutorial {
	
	public static void main(String[] args) {
		
		final var carModel =
		CarModel.withName("Ferrari Enzo").withWeightInKilogram(1365).withTopSpeedInKilometerPerHour(355);
		
		System.out.println(carModel);
	}
	
	public static final class CarModel {
		
		public static CarModelBuilder withName(final String name) {
			return new CarModelBuilder(name);
		}
		
		private final String name;
		
		private final int weightInKilogram;
		
		private final int topSpeedInKilometersPerHour;
		
		private CarModel(final String name, final int weightInKilogram, final int topSpeedInKilometersPerHour) {
			
			GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
			
			GlobalValidator.assertThat(weightInKilogram).thatIsNamed("weight in kilogram").isPositive();
			
			GlobalValidator
			.assertThat(topSpeedInKilometersPerHour)
			.thatIsNamed("top speed in kilometers per hour")
			.isPositive();
			
			this.name = name;
			this.weightInKilogram = weightInKilogram;
			this.topSpeedInKilometersPerHour = topSpeedInKilometersPerHour;
		}
		
		public String getName() {
			return name;
		}
		
		public int getTopSpeedInKilometersPerHour() {
			return topSpeedInKilometersPerHour;
		}
		
		public int getWeightInKilogram() {
			return weightInKilogram;
		}
		
		@Override
		public String toString() {
			return
			getName()
			+ " "
			+ getWeightInKilogram()
			+ " kg "
			+ getTopSpeedInKilometersPerHour()
			+ " km/h";
		}
	}
	
	private static class WeightInKilogramCapturer<NAC extends BaseArgumentCapturer<?>>
	extends ArgumentCapturer<Integer, NAC> {
		
		public WeightInKilogramCapturer(final NAC nextArgumentCapturer) {
			super(nextArgumentCapturer);
		}
		
		public int getWeightInKilogram() {
			return getRefArgument();
		}
		
		public NAC withWeightInKilogram(final Integer weightInKilogram) {
			return setArgumentAndGetRefNextArgumentCapturer(weightInKilogram);
		}
	}
	
	private static final class TopSpeedCapturer<O> extends TerminalArgumentCapturer<Integer, O> {
		
		public int getTopSpeedInKilometerPerHour() {
			return getRefArgument();
		}
		
		public O withTopSpeedInKilometerPerHour(final int topSpeedInKilometerPerHour) {
			return setArgumentAndBuild(topSpeedInKilometerPerHour);
		}
	}
	
	private static final class CarModelBuilder extends WeightInKilogramCapturer<TopSpeedCapturer<CarModel>> {
		
		public CarModelBuilder(final String name) {
			
			super(new TopSpeedCapturer<>());
			
			setBuilder(() -> build(name));
		}
		
		private CarModel build(final String name) {
			return
			new CarModel(
				name,
				getWeightInKilogram(),
				n().getTopSpeedInKilometerPerHour()
			);
		}
	}
	
	private CarModelBuilderTutorial() {}
}
