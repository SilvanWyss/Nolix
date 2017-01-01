package ch.nolix.common.sequencer;

import ch.nolix.common.functional.IBooleanGetter;
import ch.nolix.common.functional.IRunner;
import ch.nolix.common.util.Validator;

class Runner extends Thread {

	//attributes
	private int timeIntervalInMilliseconds;
	private IRunner runner;
	private IBooleanGetter booleanGetter;
	
	public Runner(int timeIntervalInMilliseconds, IBooleanGetter booleanGetter, IRunner runner) {
		
		Validator.throwExceptionIfValueIsNegative("time interval in milliseconds", timeIntervalInMilliseconds);
		Validator.throwExceptionIfValueIsNull("boolean getter", booleanGetter);
		Validator.throwExceptionIfValueIsNull("runner", runner);
		
		this.timeIntervalInMilliseconds = timeIntervalInMilliseconds;
		this.runner = runner;
		this.booleanGetter = booleanGetter;
		
		start();
	}
	
	public final void run() {
		while (true) {
			try {
				sleep(timeIntervalInMilliseconds);
			}
			catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			
			if (!booleanGetter.getOutput()) {
				break;
			}
			
			runner.run();
		}
	}
}
