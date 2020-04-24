//package declaration
package ch.nolix.template.resourceCatalogue;

//own imports
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;
import ch.nolix.common.validator.Validator;
import ch.nolix.techAPI.resourceAPI.IResource;
import ch.nolix.techAPI.resourceAPI.IResourceFactory;

//class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 50
 */
public final class ResourceCatalogue {
	
	//attributes
	public final IResource CARBON;
	public final IResource COLE;
	public final IResource CARBON_HYDRIDE;
	public final IResource METHAN;
	public final IResource SILICIUM;
	public final IResource METAL;
	public final IResource ALUMINIUM;
	public final IResource IRON;
	public final IResource TITAN;
	public final IResource SILVER;
	public final IResource GOLD;
	
	//constructor
	/**
	 * Creates a new {@link ResourceCatalogue} using the given resourceFactory.
	 * 
	 * @param resourceFactory
	 * @throws ArgumentIsNullException if the ginve resourceFactory is null.
	 */
	public ResourceCatalogue(final IResourceFactory resourceFactory) {
		
		Validator.assertThat(resourceFactory).thatIsNamed(IResourceFactory.class).isNotNull();
		
		CARBON = resourceFactory.create("Carbon");
		COLE = resourceFactory.create("Cole", CARBON);
		CARBON_HYDRIDE = resourceFactory.create("Carbon Hydride");
		METHAN = resourceFactory.create("Methan", CARBON_HYDRIDE);
		SILICIUM = resourceFactory.create("Silicium");
		METAL = resourceFactory.create("Metal");
		ALUMINIUM = resourceFactory.create("Aluminium", METAL);
		IRON = resourceFactory.create("Iron", METAL);
		TITAN = resourceFactory.create("Titan", METAL);
		SILVER = resourceFactory.create("SILVER", METAL);
		GOLD = resourceFactory.create("Gold", METAL);
	}
}
