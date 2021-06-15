//package declaration
package ch.nolix.businessapi.projectapi;

import ch.nolix.common.attributeapi.mutablemandatoryattributeapi.Titleble;
import ch.nolix.element.time.base.Time;

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
