package ch.nolix.commontutorial.buildertutorial;

import ch.nolix.common.builder.ArgumentCapturer;
import ch.nolix.common.builder.BaseArgumentCapturer;
import ch.nolix.common.builder.Builder;
import ch.nolix.common.builder.TerminalArgumentCapturer;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;

public class CarModelBuilderTutorial {
	
	public static void main(String[] args) {
		
		final var carModel =
		CarModel.withName("Ferrari Enzo").withWeightInKilogram(1365).withTopSpeedInKilometerPerHour(355);
		
		System.out.println(carModel);
	}
	
	public static final class CarModel {
		
		public static WeightInKilogramCapturer<TopSpeedCapturer<CarModel>, CarModel> withName(final String name) {
			return
			new CarModelBuilder(
				new NameCapturer<>(
					new WeightInKilogramCapturer<>(
						new TopSpeedCapturer<>()
					)
				)
			)
			.getRefStart()
			.withName(name);
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
	
	private static final class NameCapturer<
		NAC extends BaseArgumentCapturer<?, O>,
		O
	> extends ArgumentCapturer<String, NAC, O> {
		
		public NameCapturer(final NAC nextArgumentCapturer) {
			super(nextArgumentCapturer);
		}
		
		public NAC withName(final String name) {
			return setArgumentAndGetRefNextArgumentCapturer(name);
		}
	}
	
	private static final class WeightInKilogramCapturer<
		NAC extends BaseArgumentCapturer<?, O>,
		O
	> extends ArgumentCapturer<Integer, NAC, O> {
		
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
		NameCapturer<WeightInKilogramCapturer<TopSpeedCapturer<CarModel>, CarModel>, CarModel>,
		CarModel
	> {
		
		public CarModelBuilder(
			final NameCapturer<WeightInKilogramCapturer<TopSpeedCapturer<CarModel>, CarModel>, CarModel> nameCapturer
		) {
			super(nameCapturer);
		}
		
		@Override
		protected CarModel build(
			final NameCapturer<WeightInKilogramCapturer<TopSpeedCapturer<CarModel>, CarModel>, CarModel> nameCapturer
		) {
						
			final var weightInKilogramCapturer = nameCapturer.getRefNextArgumentCapturer();
			final var topSpeedCapturer = weightInKilogramCapturer.getRefNextArgumentCapturer();
			
			return new CarModel(
				nameCapturer.getRefArgument(),
				weightInKilogramCapturer.getRefArgument(),
				topSpeedCapturer.getRefArgument()
			);
		}
	}
}
