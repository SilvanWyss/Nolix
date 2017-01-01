/*
 * file:	NASDAQProductSymbolManager.java
 * author:	Silvan Wyss
 * month:	2016-09
 * lines:	60
 */

//package declaration
package ch.nolix.common.finance;

//own import
import ch.nolix.common.container.List;

//class
/**
 * This class manages the product symbols of NASDAQ (National Association of Securities Dealers Automated Quotations).
 */
public class NASDAQProductSymbolManager {
	
	//NASDAQ product symbols
	public final static String ADVANCED_MICRO_DEVICES_INC = "AMD";
	public final static String CENTURY_ALUMINIUM_CORPORATION = "CENX";
	public final static String FACEBOOK = "FB";
	public final static String FORD_INDUSTRIES_INC = "FORD";
	public final static String GENERAL_FINANCE_CORPORATION = "GFN";
	public final static String INTEL_CORPORATION = "INTC";
	public final static String MICROSOFT_CORPORATION = "MSFT";
	public final static String NETFLIX_INC = "NFLX";
	public final static String NVIDIA_CORPORATION = "NVDA";
	public final static String STARBUCKS_CORPORATION = "SBUX";
	public final static String TESLA_MOTORS_INC = "TSLA";
	public final static String THE_KRAFT_HEINZ_COMPANY = "KHC";
	public final static String UNIVERSAL_FOREST_PRODUCTS_INC = "UFPI";
	
	//static method
	/**
	 * @return the product symbols of the NASDAQ.
	 */
	public static List<String> getProductSymbols() {
		return new List<String>()
		.addAtEnd(
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
	}

	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private NASDAQProductSymbolManager() {}
}
