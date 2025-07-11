package ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelbasepi.CATEGORIZABLE;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelbasepi.ContentTypeAssignable;
import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableNameHolder;
import ch.nolix.coreapi.datamodelapi.cardinalityapi.IFluentMutableCardinalityHolder;
import ch.nolix.coreapi.datamodelapi.fieldrequestapi.MandatorynessRequestable;
import ch.nolix.coreapi.stateapi.staterequestapi.EmptinessRequestable;

public interface ISmartField
extends
CATEGORIZABLE<ISmartField>,
ContentTypeAssignable<ISmartField>,
EmptinessRequestable,
IFluentMutableNameHolder<ISmartField>,
IFluentMutableCardinalityHolder<ISmartField>,
MandatorynessRequestable {

  ISmartField getStoredBaseField();

  ISmartObject getStoredParentObject();

  IContent getStoredContent();

  boolean inheritsFromBaseField();
}
