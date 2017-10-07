//package declaration
package ch.nolix.application.candlestickAnalyzer;

//own imports
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.container.SequencePattern;
import ch.nolix.core.mathematics.Calculator;
import ch.nolix.element.finance.QuandlDataProvider;
import ch.nolix.element.finance.VolumeCandlestick;

//class
/**
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 150
 */
public final class Analysis {

	//attributes
	private final ArgumentOfficer argumentOfficer;
	private final List<List<VolumeCandlestick>> potentialSequences;
	private final int winSequenceCount;
	private final double averageOutputToInputRatio;
	
	//constructor
	public Analysis(final ArgumentOfficer argumentOfficer) {
		
		//Sets the argument officer of this analysis.
		this.argumentOfficer = argumentOfficer.getCopy();
		
		//Creates sequence pattern.
		final SequencePattern<VolumeCandlestick> sequencePattern
		= new SequencePattern<VolumeCandlestick>()
		.forNext(this.argumentOfficer.getRedCandlestickCountBeforeHammer()).addCondition(vcs -> vcs.isBearish())
		.addConditionForNext(vcs -> vcs.isHammer(this.argumentOfficer.getHammerMinLowerWickLengthRation()))
		.forNext(this.argumentOfficer.getGreenCandlestickCountAfterHammer()).addCondition(vcs -> vcs.isBullish())
		.forNext(this.argumentOfficer.getMaxKeepingDayCount()).addBlank();
		
		//Fetches the candle sticks.
		final List<VolumeCandlestick> candleSticks
		= new QuandlDataProvider().getCandleSticksPerDay(
			this.argumentOfficer.getProductSymbol(),
			this.argumentOfficer.getStartTime(),
			this.argumentOfficer.getEndTime()
		);
		
		//Extracts the potential sequences.
		potentialSequences
		= candleSticks.getSequences(sequencePattern);
		
		//Calculates the buy index.
		final int buyIndex
		= this.argumentOfficer.getRedCandlestickCountBeforeHammer()
		+ 1
		+ this.argumentOfficer.getGreenCandlestickCountAfterHammer()
		+ 1;
				
		//Iterates the potential sequences.
		int winSequenceCount = 0;
		double outputToInputRatioSum = 0.0;
		for (final List<VolumeCandlestick> ps : potentialSequences) {
			
			final double buyPrice = ps.getRefAt(buyIndex).getOpeningPrice();
			
			int sellIndex = sequencePattern.getSize();
						
			//Iterates the volume candle sticks of the current potential sequence.
			int i = 1;
			for (final VolumeCandlestick vcs : ps) {
				
				if (i > buyIndex) {
					if (1.0 - (vcs.getClosingPrice() / vcs.getOpeningPrice()) > this.argumentOfficer.getMaxLossRatioPerDay()) {
						sellIndex = Calculator.getMin(i + 1, sequencePattern.getSize());
						break;
					}
				}
				
				i++;
			}
			
			final double sellPrice = ps.getRefAt(sellIndex).getOpeningPrice();
			
			final double outputToInputRatio = sellPrice / buyPrice;
			
			if (outputToInputRatio > 1.0) {
				winSequenceCount++;
			}
			
			outputToInputRatioSum += outputToInputRatio;
		}
		
		if (potentialSequences.isEmpty()) {
			averageOutputToInputRatio = 0.0;
		}
		else {
			averageOutputToInputRatio = outputToInputRatioSum / potentialSequences.getElementCount();
		}
		
		this.winSequenceCount = winSequenceCount;
	}
	
	//method
	public double getAverageOutputToInputRatio() {
		return averageOutputToInputRatio;
	}
	
	//method
	public int getPotentialSequenceCount() {
		return potentialSequences.getElementCount();
	}
	
	//method
	public int getWinSequenceCount() {
		return winSequenceCount;
	}
	
	//method
	public String getData() {
			
		String data = StringCatalogue.EMPTY_STRING;
		for (final String s : toStrings()) {
			data += s + CharacterCatalogue.NEW_LINE;
		}
		data += CharacterCatalogue.NEW_LINE;
		
		data += "potential sequences:";
		data += CharacterCatalogue.NEW_LINE;
		data += CharacterCatalogue.NEW_LINE;		
		for (final List<VolumeCandlestick> ps : potentialSequences) {
			
			for (final VolumeCandlestick vcs : ps) {
				data += vcs.getSpecification().toString();
			    data += CharacterCatalogue.NEW_LINE;
			}
			
			data += CharacterCatalogue.NEW_LINE;
		}
		
		return data;
	}
	
	//method
	public String toString() {
		return toStrings().toString();
	}
	
	//method
	public List<String> toStrings() {
		return
		argumentOfficer.toStrings().addAtEnd(
			StringCatalogue.EMPTY_STRING,
			"potential sequences:            " + getPotentialSequenceCount(),
			"win sequences:                  " + getWinSequenceCount(),
			"average output to input ratio:  " + getAverageOutputToInputRatio()
		);
	}
}
