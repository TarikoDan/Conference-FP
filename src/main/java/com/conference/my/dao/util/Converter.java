package com.conference.my.dao.util;

public interface Converter<E,V> {
  E convert(V value);
}
