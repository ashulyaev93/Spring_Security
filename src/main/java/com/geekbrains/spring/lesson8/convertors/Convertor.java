package com.geekbrains.spring.lesson8.convertors;

public interface Convertor<TARGET, SOURCE>  {
    TARGET convert(SOURCE source);
}
