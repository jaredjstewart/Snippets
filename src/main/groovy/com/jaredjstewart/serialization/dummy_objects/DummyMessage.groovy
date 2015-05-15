package com.jaredjstewart.serialization.dummy_objects

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class DummyMessage implements Serializable {

    String body

    boolean html
}
