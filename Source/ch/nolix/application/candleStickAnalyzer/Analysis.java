//package declaration
package ch.nolix.application.candleStickAnalyzer;

//own imports
import ch.nolix.core.constants.CharacterManager;
import ch.nolix.core.constants.StringManager;
import ch.nolix.core.container.List;
import ch.nolix.core.container.SequencePattern;
import ch.nolix.core.fileSystem.FileSystemAccessor;
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
			
			int sellIndex = -1;
						
			//Iterates the volume candle sticks of the current potential sequence.
			int i = 1;
			for (final VolumeCandleStick vcs : ps) {
				
				if (i > buyIndex) {
					if (1 - vcs.getChangeRatio() > this.argumentOfficer.getMaxLossRatioPerDay()) {
						sellIndex = i + 1;
					}
				}
				
				i++;
			}
			
			sellIndex = Calculator.getMin(sellIndex, sequencePattern.getSize());
			final double sellPrice = ps.getRefAt(sellIndex).getOpeningPrice();
			
			final double outputToInputRatio = sellPrice / buyPrice;
			outputToInputRatioSum += outputToInputRatio;
			
			if (outputToInputRatio > 1.0) {
				winSequenceCount++;
			}
		}
		
		averageOutputToInputRatio = outputToInputRatioSum / potentialSequences.getElementCount();
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
	public void save() {
				
		int i = 1;
		while (new FileSystemAccessor().fileSystemItemExists("analysis_" + i + ".txt")) {
			i++;
		}
		
		String data = StringManager.EMPTY_STRING;
		for (final String s : toStrings()) {
			data += s + CharacterManager.NEW_LINE;
		}
		data += CharacterManager.NEW_LINE;
		
		data += "potential sequences:";
		data += CharacterManager.NEW_LINE;
		data += CharacterManager.NEW_LINE;		
		for (final List<VolumeCandleStick> ps : potentialSequences) {
			
			for (final VolumeCandleStick vcs : ps) {
				data += vcs.getSpecification().toString() + CharacterManager.NEW_LINE;
			}
			
			data += CharacterManager.NEW_LINE;
		}
		
		new FileSystemAccessor().createFile("analysis_" + i + ".txt", data);
	}
	
	//method
	public String toString() {
		return toStrings().toString();
	}
	
	//method
	public List<String> toStrings() {
		return
		argumentOfficer.toStrings().addAtEnd(
			" ",
			"potential patterns: " + getPotentialSequenceCount(),
			"win patterns: " + getWinSequenceCount(),
			"average output to input ratio: " + getAverageOutputToInputRatio()
		);
	}
}
