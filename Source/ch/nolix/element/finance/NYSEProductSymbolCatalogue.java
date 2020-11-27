//package declaration
package ch.nolix.element.finance;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.ReadContainer;

//class
/**
 * The {@link NYSEProductSymbolCatalogue} defines product symbols of the NYSE (New York Stock Exchange).
 * 
 * @author Silvan Wyss
 * @date 2017-01-01
 * @lines 110
 */
public final class NYSEProductSymbolCatalogue {
	
	//constants
	public static final String AMERICAN_ELECTRIC_POWER_CO_INC = "AEP";
	public static final String APPLE = "AAPL";
	public static final String ALPHABET = "GOOG";
	public static final String AT_T_INC = "T";
	public static final String BANK_OF_AMERICA = "BAC";
	public static final String BLACK_ROCK_INC = "BLK";
	public static final String BOEING = "BA";
	public static final String BP = "BP";
	public static final String CABOT_OIL_GAS_CORPORATION = "COG";
	public static final String CHEVRON_CORPORATION = "CVX";
	public static final String CITYGROUP_INC = "C";
	public static final String CREDIT_SUISSE_GROUP_AG = "CS";
	public static final String EXXON_MOBILE_CORPORATION = "XOM";
	public static final String FORD = "F";
	public static final String GENERAL_ELECTRIC_COMPANY = "GE";
	public static final String GENERAL_MOTORS = "GM";
	public static final String HILTON_WORLDWIDE_HOLDINGS_INC = "HLT";
	public static final String HP_INC = "HPQ";
	public static final String INTERNATIONAL_BUSINESS_MACHINES_CORPORATION = "IBM";
	public static final String JOHNSON_JOHNSON ="JNJ";
	public static final String JPMORGAN_CHASE_COMPANY = "JPM";
	public static final String MACYS_INC = "M";
	public static final String MARATHON_OIL_CORPORATION = "MRO";
	public static final String MONSANTO_COMPANY = "MON";
	public static final String NIKE_INC = "NKE";
	public static final String NOVARTIS_AG = "NVS";
	public static final String OPPENHEIMER_HOLDINGS_INC = "OPY";
	public static final String ORACLE_CORPORATION = "ORCL";
	public static final String PERFORMANCE_SPORTS_GROUP_LTD = "PSG";
	public static final String PFIZER_INC = "PFE";
	public static final String PHILIP_MORIS_INTERNATIONAL_INC = "PM";
	public static final String SUMMIT_HOTEL_PROPERTIES_INC = "INN";
	public static final String THE_GOLDMAN_SACHS_GROUP = "GS";
	public static final String THE_WALT_DISNEY_COMPANY = "DIS";
	public static final String TOYOTA_MOTOR_CORPORATION = "TM";
	public static final String TWITTER_INC = "TWTR";
	public static final String UBS_GROUP_AG = "UBS";
	public static final String UNILEVER_NV = "UN";
	public static final String UNITED_STATES_STEEL_CORPORATION = "X";
	public static final String VISA_INC = "V";
	public static final String WALMART_STORES_INC = "WMT";
	public static final String YAHOO = "YHOO";
	
	//constant
	public static final IContainer<String> PRODUCT_SYMBOLS =
	ReadContainer.withElements(
		AMERICAN_ELECTRIC_POWER_CO_INC,
		APPLE,
		ALPHABET,
		AT_T_INC,
		BANK_OF_AMERICA,
		BLACK_ROCK_INC,
		BOEING,
		BP,
		CABOT_OIL_GAS_CORPORATION,
		CHEVRON_CORPORATION,
		CREDIT_SUISSE_GROUP_AG,
		EXXON_MOBILE_CORPORATION,
		FORD,
		GENERAL_ELECTRIC_COMPANY,
		GENERAL_MOTORS,
		HILTON_WORLDWIDE_HOLDINGS_INC,
		HP_INC,
		INTERNATIONAL_BUSINESS_MACHINES_CORPORATION,
		JOHNSON_JOHNSON,
		JPMORGAN_CHASE_COMPANY,
		MACYS_INC,
		MARATHON_OIL_CORPORATION,
		MONSANTO_COMPANY,
		NIKE_INC,
		NOVARTIS_AG,
		OPPENHEIMER_HOLDINGS_INC,
		ORACLE_CORPORATION,
		PERFORMANCE_SPORTS_GROUP_LTD,
		PFIZER_INC,
		PHILIP_MORIS_INTERNATIONAL_INC,
		THE_GOLDMAN_SACHS_GROUP,
		THE_WALT_DISNEY_COMPANY,
		TOYOTA_MOTOR_CORPORATION,
		TWITTER_INC,
		UBS_GROUP_AG,
		UNILEVER_NV,
		UNITED_STATES_STEEL_CORPORATION,
		VISA_INC,
		WALMART_STORES_INC,
		YAHOO
	);
	
	//visibility-reduced constructor
	/**
	 * Avoids that an instance of the {@link NYSEProductSymbolCatalogue} can be created.
	 */
	private NYSEProductSymbolCatalogue() {}
}
