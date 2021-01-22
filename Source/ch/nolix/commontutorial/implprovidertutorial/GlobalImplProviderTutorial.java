package ch.nolix.commontutorial.implprovidertutorial;

import ch.nolix.common.implprovider.GlobalImplProvider;

public final class GlobalImplProviderTutorial {
	
	private static interface ICity {
		String getName();
		int getPopulation();
	}
	
	private static class City implements ICity {
		
		private final String name;
		private final int popoulation ;
		
		@SuppressWarnings("unused")
		public City(final String name, final int population) {
			this.name = name;
			this.popoulation = population;
		}
		
		@Override
		public String getName() {
			return name;
		}

		@Override
		public int getPopulation() {
			return popoulation;
		}
	}
	
	public static void main(String[] args) {
		
		//Registers the City class as the implementation of the ICity interface.
		GlobalImplProvider.forInterface(ICity.class).registerImplementation(City.class);
		
		//Creates instances of the City class, that is found by the ICity interface.
		final var paris = GlobalImplProvider.ofInterface(ICity.class).createInstance("Paris", 12_100_000);
		final var london = GlobalImplProvider.ofInterface(ICity.class).createInstance("London", 8_100_000);
		
		//Prints out to the console the data of the instances.
		System.out.println(paris.getName() + ", population: " + paris.getPopulation());
		System.out.println(london.getName() + ", population: " + london.getPopulation());
	}
	
	private GlobalImplProviderTutorial() {}
}
