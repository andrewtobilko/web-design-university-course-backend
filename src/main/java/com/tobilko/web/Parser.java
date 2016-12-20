package com.tobilko.web;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;

public interface Parser<T extends Serializable> {

    T parse(Reader reader) throws IOException;

}
