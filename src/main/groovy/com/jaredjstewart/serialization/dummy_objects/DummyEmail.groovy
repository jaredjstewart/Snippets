package com.jaredjstewart.serialization.dummy_objects

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class DummyEmail implements Serializable {

    String from, to

    DummyMessage body
}
