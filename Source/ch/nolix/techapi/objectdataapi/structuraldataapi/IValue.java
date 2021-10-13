//package declaration
package ch.nolix.techapi.objectdataapi.structuraldataapi;

//interface
public interface IValue<VA extends IValue<VA, V>, V> extends ISingleValue<VA, V> {}
