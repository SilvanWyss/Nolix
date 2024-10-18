package ch.nolix.techapi.relationaldocapi.datamodelapi;

import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableCardinalityHolder;
import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableNameHolder;
import ch.nolix.coreapi.datamodelapi.fieldrequestapi.MandatorynessRequestable;
import ch.nolix.coreapi.stateapi.staterequestapi.EmptinessRequestable;
import ch.nolix.techapi.relationaldocapi.baseapi.Abstractable;
import ch.nolix.techapi.relationaldocapi.baseapi.ContentTypeAssignable;

public interface IAbstractableField
extends
Abstractable<IAbstractableField>,
ContentTypeAssignable<IAbstractableField>,
EmptinessRequestable,
IFluentMutableNameHolder<IAbstractableField>,
IFluentMutableCardinalityHolder<IAbstractableField>,
MandatorynessRequestable {

  IAbstractableField getStoredBaseField();

  IAbstractableObject getStoredParentObject();

  IContent getStoredContent();

  boolean inheritsFromBaseField();
}
