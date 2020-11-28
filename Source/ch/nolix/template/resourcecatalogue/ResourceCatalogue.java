//package declaration
package ch.nolix.template.resourcecatalogue;

//own import
import ch.nolix.tech.resource.Resource;

//class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 30
 */
public final class ResourceCatalogue {
	
	//constants
	public static final Resource CARBON = new Resource("carbon");
	public static final Resource COLE = new Resource("cole", CARBON);
	public static final Resource CARBON_HYDRIDE = new Resource("carbone hydride");
	public static final Resource METHAN = new Resource("methan", CARBON_HYDRIDE);
	public static final Resource SILICIUM = new Resource("silicium");
	public static final Resource METAL = new Resource("metal");
	public static final Resource ALUMINIUM = new Resource("aluminium", METAL);
	public static final Resource IRON = new Resource("iron", METAL);
	public static final Resource TITAN = new Resource("titan", METAL);
	public static final Resource SILVER = new Resource("silver", METAL);
	public static final Resource GOLD = new Resource("gold", METAL);
	
	//visibility-reduced constructor
	/**
	 * Avoids that an instance of the {@link ResourceCatalogue} can be created.
	 */
	private ResourceCatalogue() {}
}
