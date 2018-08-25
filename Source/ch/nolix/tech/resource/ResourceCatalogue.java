//package declaration
package ch.nolix.tech.resource;

//class
/**
 * Of a {@link ResourceCatalogue} no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 30
 */
public final class ResourceCatalogue {

	//constants
	public static final Resource CARBON = new Resource("Carbon");
	public static final Resource COLE = new Resource("Cole", CARBON);
	public static final Resource CARBON_HYDRIDE = new Resource("Carbon Hydride");
	public static final Resource METHAN = new Resource("Methan", CARBON_HYDRIDE);
	public static final Resource SILICIUM = new Resource("Silicium");
	public static final Resource METAL = new Resource("Metal");
	public static final Resource ALUMINIUM = new Resource("Aluminium", METAL);
	public static final Resource IRON = new Resource("Iron", METAL);
	public static final Resource TITAN = new Resource("Titan", METAL);
	public static final Resource SILVER = new Resource("Silver", METAL);
	public static final Resource GOLD = new Resource("Gold", METAL);
	
	//private constructor
	/**
	 * Avoids that an instance of a {@link ResourceCatalogue} can be created.
	 */
	private ResourceCatalogue() {}
}
