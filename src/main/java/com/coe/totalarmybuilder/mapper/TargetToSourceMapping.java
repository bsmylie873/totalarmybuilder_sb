package com.coe.totalarmybuilder.mapper;

import org.modelmapper.PropertyMap;

public interface TargetToSourceMapping<T, U> {

    PropertyMap<T, U> mapFromTargetToSource();
}