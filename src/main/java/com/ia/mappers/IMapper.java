package com.ia.mappers;

public interface IMapper<T,TDTO> {
    TDTO toDTO(T model);
    T toModel(TDTO dto);
}
