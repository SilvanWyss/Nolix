//package declaration
package ch.nolix.application.candlestickAnalyzer;

//own imports
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.element.core.Time;
import ch.nolix.element.finance.NYSEProductSymbolManager;
import ch.nolix.primitive.validator2.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 190
 */
public final class ArgumentOfficer {

	//attributes
	private String productSymbol = NYSEProductSymbolManager.BLACK_ROCK_INC;
	private Time startTime = new Time(2016, 1, 1);
	private Time endTime = new Time(2016, 12, 31);
	private int redCandlestickCountBeforeHammer = 1;
	private int greenCandlestickCountAfterHammer = 1;
	private double hammerMinLowerWickLengthRatio = 0.5;
	private int maxKeepingDayCount = 30;
	private double maxLossRatioPerDay = 0.05;
	
	//method
	public ArgumentOfficer getCopy() {
		return
			new ArgumentOfficer()
			.setProductSymbol(getProductSymbol())
			.setStartTime(getStartTime())
			.setEndTime(getEndTime())
			.setRedCandlestickCountBeforeHammer(getRedCandlestickCountBeforeHammer())
			.setGreenCandlestickCountAfterHammer(getGreenCandlestickCountAfterHammer())
			.setHammerMinLowerWickLengthRation(getHammerMinLowerWickLengthRation())
			.setMaxLossRatioPerDay(getMaxLossRatioPerDay())
			.setMaxKeepingDayCount(getMaxKeepingDayCount());
	}
	
	//method
	public Time getEndTime() {
		return endTime;
	}
	
	//method
	public int getGreenCandlestickCountAfterHammer() {
		return greenCandlestickCountAfterHammer;
	}
	
	//method
	public double getHammerMinLowerWickLengthRation() {
		return hammerMinLowerWickLengthRatio;
	}
	
	//method
	public int getMaxKeepingDayCount() {
		return maxKeepingDayCount;
	}
	
	//method
	public double getMaxLossRatioPerDay() {
		return maxLossRatioPerDay;
	}
	
	//method
	public String getProductSymbol() {
		return productSymbol;
	}
	
	//method
	public int getRedCandlestickCountBeforeHammer() {
		return redCandlestickCountBeforeHammer;
	}
	
	//method
	public Time getStartTime() {
		return startTime;
	}
	
	//method
	public ArgumentOfficer setEndTime(final Time endTime) {
		
		Validator.suppose(endTime).thatIsNamed("end time").isNotNull();
		
		this.endTime = endTime;
		
		return this;
	}
	
	//method
	public ArgumentOfficer setGreenCandlestickCountAfterHammer(final int greenCandlestickCountAfterHammer) {
		
		Validator
		.suppose(greenCandlestickCountAfterHammer)
		.thatIsNamed("green candlestick count after hammer")
		.isNotNegative();
		
		this.greenCandlestickCountAfterHammer = greenCandlestickCountAfterHammer;
		
		return this;
	}
	
	//method
	public ArgumentOfficer setHammerMinLowerWickLengthRation(final double hammerMinLowerWickLengthRatio) {
		
		Validator
		.suppose(hammerMinLowerWickLengthRatio)
		.thatIsNamed("hammer minimal lower wick Length ratio")
		.isNotNegative();
		
		this.hammerMinLowerWickLengthRatio = hammerMinLowerWickLengthRatio;
		
		return this;
	}
	
	//method
	public ArgumentOfficer setMaxKeepingDayCount(final int maxKeepingDayCount) {
		
		Validator
		.suppose(maxKeepingDayCount)
		.thatIsNamed("maximal keeping day count")
		.isPositive();
		
		this.maxKeepingDayCount = maxKeepingDayCount;
		
		return this;
	}
	
	//method
	public ArgumentOfficer setMaxLossRatioPerDay(final double maxLossRatioPerDay) {
		
		Validator
		.suppose(maxLossRatioPerDay)
		.thatIsNamed("max loss ratio per day")
		.isNotNegative();
		
		Validator
		.suppose(maxLossRatioPerDay)
		.thatIsNamed("max loss ratio per day")
		.isNotBiggerThan(1.0);
		
		this.maxLossRatioPerDay = maxLossRatioPerDay;
		
		return this;
	}
	
	//method
	public ArgumentOfficer setProductSymbol(final String productSymbol) {
		
		Validator.suppose(productSymbol).isNotEmpty();
		
		this.productSymbol = productSymbol;
		
		return this;
	}
	
	//method
	public ArgumentOfficer setRedCandlestickCountBeforeHammer(final int redCandlestickCountBeforeHammer) {
		
		Validator
		.suppose(redCandlestickCountBeforeHammer)
		.thatIsNamed("red candlestick count before hammer")
		.isNotNegative();
		
		this.redCandlestickCountBeforeHammer = redCandlestickCountBeforeHammer;
		
		return this;
	}
	
	//method
	public ArgumentOfficer setStartTime(final Time startTime) {
		
		Validator.suppose(startTime).thatIsNamed("start time").isNotNull();
		
		this.startTime = startTime;
		
		return this;
	}
	
	//method
	public List<String> toStrings() {
		return
		new List<String>(		
			"product symbol:  " + getProductSymbol(),
			"start time:      " + getStartTime(),
			"end time:        " + getEndTime(),
			StringCatalogue.EMPTY_STRING,
			"number of red candle sticks before hammer:   " + getRedCandlestickCountBeforeHammer(),
			"number of green candle sticks after hammer:  " + getGreenCandlestickCountAfterHammer(),
			"hammer minimal lower wick to length ratio:   " + getHammerMinLowerWickLengthRation(),
			"maximal number of keeping days:              " + getMaxKeepingDayCount(),
			"maximal loss ratio per day:                  " + getMaxLossRatioPerDay()
			
		);
	}
}
