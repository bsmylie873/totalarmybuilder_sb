package com.coe.totalarmybuilder.mapper;

import org.modelmapper.PropertyMap;

public interface SourceToTargetMapping<T, U> {

    PropertyMap<T, U> mapFromSourceToTarget();
}
