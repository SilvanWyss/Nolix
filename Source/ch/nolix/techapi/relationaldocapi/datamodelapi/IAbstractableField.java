//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//own imports
import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableNameHolder;
import ch.nolix.coreapi.datamodelapi.fieldrequestapi.MandatorynessRequestable;
import ch.nolix.coreapi.functionapi.requestapi.EmptinessRequestable;
import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableCardinalityHolder;
import ch.nolix.techapi.relationaldocapi.baseapi.Abstractable;
import ch.nolix.techapi.relationaldocapi.baseapi.ContentTypeAssignable;

//interface
public interface IAbstractableField
extends
Abstractable<IAbstractableField>,
ContentTypeAssignable<IAbstractableField>,
EmptinessRequestable,
IFluentMutableNameHolder<IAbstractableField>,
IFluentMutableCardinalityHolder<IAbstractableField>,
MandatorynessRequestable {

  //method declaration
  IAbstractableField getStoredBaseField();

  //method declaration
  IAbstractableObject getStoredParentObject();

  //method declaration
  IContent getStoredContent();

  //method declaration
  boolean inheritsFromBaseField();
}
