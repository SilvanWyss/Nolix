package ch.nolix.commonTutorial.instanceProviderTutorial;

//own imports
import ch.nolix.common.instanceProvider.CentralInstanceProvider;

public final class CentralInstanceProviderTutorial {
	
	public static void main(String[] args) {
		
		//Registers the City class as the implementation of the ICity interface.
		CentralInstanceProvider.register(ICity.class, City.class);
		
		//Creates instances of the City class, that is found by the ICity interface.
		final var paris = CentralInstanceProvider.create(ICity.class, "Paris", 12100000);
		final var london = CentralInstanceProvider.create(ICity.class, "London", 8100000);
		
		//Prints out to the console the data of the instances.
		System.out.println(paris.getName() + ", population: " + paris.getPopulation());
		System.out.println(london.getName() + ", population: " + london.getPopulation());
	}
	
	private static interface ICity {
		public abstract String getName();
		public abstract int getPopulation();
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
	
	private CentralInstanceProviderTutorial() {}
}
