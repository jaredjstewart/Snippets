package com.jaredstewart.resource_loading

import com.jaredjstewart.resource_loading.ResourceLoader
import spock.lang.Specification


class ResourceLoaderTest extends Specification {

    def 'loads example property'() {
        given:
        Properties props = ResourceLoader.loadPropertiesFile('example.properties')

        expect:
        props.getProperty('username') == 'jared'
    }
}