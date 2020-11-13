/*
 * file:	NASDAQProductSymbolManager.java
 * author:	Silvan Wyss
 * month:	2016-09
 * lines:	60
 */

//package declaration
package ch.nolix.element.finance;

import ch.nolix.common.container.LinkedList;

//class
/**
 * This class manages the product symbols of NASDAQ (National Association of Securities Dealers Automated Quotations).
 */
public class NASDAQProductSymbolCatalogue {
	
	//NASDAQ product symbols
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
	
	//static method
	/**
	 * @return the product symbols of the NASDAQ.
	 */
	public static LinkedList<String> getProductSymbols() {
		return new LinkedList<String>()
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

	//visibility-reducing constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private NASDAQProductSymbolCatalogue() {}
}
