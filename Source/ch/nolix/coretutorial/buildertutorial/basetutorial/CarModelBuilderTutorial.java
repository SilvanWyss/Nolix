package ch.nolix.coretutorial.buildertutorial.basetutorial;

import ch.nolix.core.builder.base.ArgumentCapturer;
import ch.nolix.core.builder.base.BaseArgumentCapturer;
import ch.nolix.core.builder.base.Builder;
import ch.nolix.core.builder.base.TerminalArgumentCapturer;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.errorcontrol.validator.Validator;

public class CarModelBuilderTutorial {
	
	public static void main(String[] args) {
		
		final var carModel =
		CarModel.withName("Ferrari Enzo").withWeightInKilogram(1365).withTopSpeedInKilometerPerHour(355);
		
		System.out.println(carModel);
	}
	
	public static final class CarModel {
		
		public static WeightInKilogramCapturer<TopSpeedCapturer<CarModel>> withName(final String name) {
			return new CarModelBuilder().getRefStart().withName(name);
		}
		
		private final String name;
		private final int weightInKilogram;
		private final int topSpeedInKilometersPerHour;
		
		private CarModel(final String name, final int weightInKilogram, final int topSpeedInKilometersPerHour) {
			
			Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
			Validator.assertThat(weightInKilogram).thatIsNamed("weight in kilogram").isPositive();
			Validator.assertThat(topSpeedInKilometersPerHour).thatIsNamed("top speed in kilometers per hour").isPositive();
			
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
	
	private static final class NameCapturer<NAC extends BaseArgumentCapturer<?>>
	extends ArgumentCapturer<String, NAC> {
		
		public NameCapturer(final NAC nextArgumentCapturer) {
			super(nextArgumentCapturer);
		}
		
		public NAC withName(final String name) {
			return setArgumentAndGetRefNextArgumentCapturer(name);
		}
	}
	
	private static final class WeightInKilogramCapturer<NAC extends BaseArgumentCapturer<?>
	> extends ArgumentCapturer<Integer, NAC> {
		
		public WeightInKilogramCapturer(final NAC nextArgumentCapturer) {
			super(nextArgumentCapturer);
		}
		
		public NAC withWeightInKilogram(final Integer weightInKilogram) {
			return setArgumentAndGetRefNextArgumentCapturer(weightInKilogram);
		}
	}
	
	private static final class TopSpeedCapturer<O> extends TerminalArgumentCapturer<Integer, O> {
		public O withTopSpeedInKilometerPerHour(final int topSpeedInKilometerPerHour) {
			return setArgumentAndBuild(topSpeedInKilometerPerHour);
		}
	}
	
	private static final class CarModelBuilder extends Builder<
		NameCapturer<WeightInKilogramCapturer<TopSpeedCapturer<CarModel>>>,
		CarModel
	> {
		
		@Override
		protected CarModel build(
			final NameCapturer<WeightInKilogramCapturer<TopSpeedCapturer<CarModel>>> nameCapturer
		) {
			
			final var weightInKilogramCapturer = nameCapturer.n();
			final var topSpeedCapturer = weightInKilogramCapturer.n();
			
			return new CarModel(
				nameCapturer.getRefArgument(),
				weightInKilogramCapturer.getRefArgument(),
				topSpeedCapturer.getRefArgument()
			);
		}
		
		@Override
		protected NameCapturer<WeightInKilogramCapturer<TopSpeedCapturer<CarModel>>> createStartArgumentCapturer() {
			return new NameCapturer<>(new WeightInKilogramCapturer<>(new TopSpeedCapturer<>()));
		}
	}
}
