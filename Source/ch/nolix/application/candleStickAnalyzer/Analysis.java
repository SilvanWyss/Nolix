//package declaration
package ch.nolix.application.candleStickAnalyzer;

//own imports
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.container.SequencePattern;
import ch.nolix.core.mathematics.Calculator;
import ch.nolix.element.finance.QuandlDataProvider;
import ch.nolix.element.finance.VolumeCandleStick;

//class
/**
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 150
 */
public final class Analysis {

	//attributes
	private final ArgumentOfficer argumentOfficer;
	private final List<List<VolumeCandleStick>> potentialSequences;
	private final int winSequenceCount;
	private final double averageOutputToInputRatio;
	
	//constructor
	public Analysis(final ArgumentOfficer argumentOfficer) {
		
		//Sets the argument officer of this analysis.
		this.argumentOfficer = argumentOfficer.getCopy();
		
		//Creates sequence pattern.
		final SequencePattern<VolumeCandleStick> sequencePattern
		= new SequencePattern<VolumeCandleStick>()
		.forNext(this.argumentOfficer.getRedCandleStickCountBeforeHammer()).addCondition(vcs -> vcs.isBearish())
		.addConditionForNext(vcs -> vcs.isHammer(this.argumentOfficer.getHammerMinLowerWickLengthRation()))
		.forNext(this.argumentOfficer.getGreenCandleStickCountAfterHammer()).addCondition(vcs -> vcs.isBullish())
		.forNext(this.argumentOfficer.getMaxKeepingDayCount()).addBlank();
		
		//Fetches the candle sticks.
		final List<VolumeCandleStick> candleSticks
		= new QuandlDataProvider().getCandleSticksPerDay(
			this.argumentOfficer.getProductSymbol(),
			this.argumentOfficer.getStartTime(),
			this.argumentOfficer.getEndTime()
		);
		
		//Extracts the potential sequences.
		potentialSequences
		= candleSticks.getSequences(sequencePattern);
		
		final int buyIndex
		= this.argumentOfficer.getRedCandleStickCountBeforeHammer()
		+ 1
		+ this.argumentOfficer.getGreenCandleStickCountAfterHammer()
		+ 1;
				
		//Iterates the potential sequences.
		int winSequenceCount = 0;
		double outputToInputRatioSum = 0.0;
		for (final List<VolumeCandleStick> ps : potentialSequences) {
			
			final double buyPrice = ps.getRefAt(buyIndex).getOpeningPrice();
			
			int sellIndex = sequencePattern.getSize();
						
			//Iterates the volume candle sticks of the current potential sequence.
			int i = 1;
			for (final VolumeCandleStick vcs : ps) {
				
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
		for (final List<VolumeCandleStick> ps : potentialSequences) {
			
			for (final VolumeCandleStick vcs : ps) {
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
