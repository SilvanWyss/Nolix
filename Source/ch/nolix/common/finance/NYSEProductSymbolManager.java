/*
 * file:	NYSEProductSymbolManager.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	110
 */

//package declaration
package ch.nolix.common.finance;

//own import
import ch.nolix.common.container.List;

//class
/**
 * This class manages the product symbols of NYSE (New York Stock Exchange).
 */
public final class NYSEProductSymbolManager {

	//NYSE product symbols
	public final static String AMERICAN_ELECTRIC_POWER_CO_INC = "AEP";
	public final static String APPLE = "AAPL";
	public final static String ALPHABET = "GOOG";
	public final static String AT_T_INC = "T";
	public final static String BANK_OF_AMERICA = "BAC";
	public final static String BLACK_ROCK_INC = "BLK";
	public final static String BOEING = "BA";
	public final static String BP = "BP";
	public final static String CABOT_OIL_GAS_CORPORATION = "COG";
	public final static String CHEVRON_CORPORATION  = "CVX";
	public final static String CITYGROUP_INC = "C";
	public final static String CREDIT_SUISSE_GROUP_AG = "CS";
	public final static String EXXON_MOBILE_CORPORATION = "XOM";
	public final static String FORD = "F";
	public final static String GENERAL_ELECTRIC_COMPANY = "GE";
	public final static String GENERAL_MOTORS = "GM";
	public final static String HILTON_WORLDWIDE_HOLDINGS_INC = "HLT";
	public final static String HP_INC = "HPQ";
	public final static String INTERNATIONAL_BUSINESS_MACHINES_CORPORATION = "IBM";
	public final static String JOHNSON_JOHNSON ="JNJ";
	public final static String JPMORGAN_CHASE_COMPANY = "JPM";
	public final static String MACYS_INC = "M";
	public final static String MARATHON_OIL_CORPORATION = "MRO";
	public final static String MONSANTO_COMPANY = "MON";
	public final static String NIKE_INC = "NKE";
	public final static String NOVARTIS_AG = "NVS";
	public final static String OPPENHEIMER_HOLDINGS_INC = "OPY";
	public final static String ORACLE_CORPORATION = "ORCL";
	public final static String PERFORMANCE_SPORTS_GROUP_LTD = "PSG";
	public final static String PFIZER_INC = "PFE";
	public final static String PHILIP_MORIS_INTERNATIONAL_INC = "PM";
	public final static String SUMMIT_HOTEL_PROPERTIES_INC = "INN";
	public final static String THE_GOLDMAN_SACHS_GROUP = "GS";
	public final static String THE_WALT_DISNEY_COMPANY = "DIS";
	public final static String TOYOTA_MOTOR_CORPORATION = "TM";
	public final static String TWITTER_INC = "TWTR";
	public final static String UBS_GROUP_AG = "UBS";
	public final static String UNILEVER_NV = "UN";
	public final static String UNITED_STATES_STEEL_CORPORATION = "X";
	public final static String VISA_INC = "V";
	public final static String WALMART_STORES_INC = "WMT";
	public final static String YAHOO = "YHOO";
	
	//static method
	/**
	 * @return the product symbols of the NYSE
	 */
	public static List<String> getProductSymbols() {
		return new List<String>()
		.addAtEnd(
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
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private NYSEProductSymbolManager() {}
}
