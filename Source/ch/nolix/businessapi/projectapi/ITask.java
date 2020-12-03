//package declaration
package ch.nolix.businessapi.projectapi;

//own imports
import ch.nolix.common.mutableattributeapi.Titleble;
import ch.nolix.element.time.Time;

//interface
public interface ITask extends Titleble<ITask>{
	
	//method declaration
	Time getCreationDate();
	
	//method declaration
	TaskSize getSize();
	
	//method declaration
	Time getSolveDate();
		
	//method declaration
	boolean hasSize();
	
	//method declaration
	boolean isSolved();
	
	//method declaration
	ITask setSize(TaskSize size);
	
	//method declaration
	ITask setSolved();
	
	//method declaration
	ITask setSolved(final Time solveTime);
	
	//method declaration
	ITask setUnsolved();
}
