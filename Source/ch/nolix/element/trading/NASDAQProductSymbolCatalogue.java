//package declaration
package ch.nolix.element.trading;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.ReadContainer;

//class
/**
 * The {@link NASDAQProductSymbolCatalogue} defines  product symbols of the NASDAQ
 * (National Association of Securities Dealers Automated Quotations).
 * 
 * @author Silvan Wyss
 * @date 2017-01-01
 * @lines 50
 */
public final class NASDAQProductSymbolCatalogue {
	
	//constants
	public static final String ADVANCED_MICRO_DEVICES_INC = "AMD";
	public static final String CENTURY_ALUMINIUM_CORPORATION = "CENX";
	public static final String FACEBOOK = "FB";
	public static final String FORD_INDUSTRIES_INC = "FORD";
	public static final String GENERAL_FINANCE_CORPORATION = "GFN";
	public static final String INTEL_CORPORATION = "INTC";
	public static final String MICROSOFT_CORPORATION = "MSFT";
	public static final String NETFLIX_INC = "NFLX";
	public static final String NVIDIA_CORPORATION = "NVDA";
	public static final String STARBUCKS_CORPORATION = "SBUX";
	public static final String TESLA_MOTORS_INC = "TSLA";
	public static final String THE_KRAFT_HEINZ_COMPANY = "KHC";
	public static final String UNIVERSAL_FOREST_PRODUCTS_INC = "UFPI";
	
	//constant
	public static final IContainer<String> PRODUCT_SYMBOLS =
	ReadContainer.withElements(
		ADVANCED_MICRO_DEVICES_INC,
		CENTURY_ALUMINIUM_CORPORATION,
		FACEBOOK,
		FORD_INDUSTRIES_INC,
		GENERAL_FINANCE_CORPORATION,
		INTEL_CORPORATION ,
		MICROSOFT_CORPORATION,
		NETFLIX_INC,
		NVIDIA_CORPORATION,
		STARBUCKS_CORPORATION,
		TESLA_MOTORS_INC,
		THE_KRAFT_HEINZ_COMPANY,
		UNIVERSAL_FOREST_PRODUCTS_INC
	);
	
	//constructor
	/**
	 * Prevents that an instance of the {@link NASDAQProductSymbolCatalogue} can be created.
	 */
	private NASDAQProductSymbolCatalogue() {}
}
