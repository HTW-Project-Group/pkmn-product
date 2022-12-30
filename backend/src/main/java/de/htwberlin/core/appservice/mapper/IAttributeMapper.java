package de.htwberlin.core.appservice.mapper;

public interface IAttributeMapper<T> {

    T copyAttributes(T source, T target);
}
