package com.jaredjstewart.resource_loading

import org.apache.commons.io.IOUtils


class ResourceLoader {

    public static URI loadResourceAsURI(String fileName) {
        Thread.currentThread().getContextClassLoader().getResource(fileName).toURI()
    }

    public static InputStream loadResourceAsStream(String fileName) {
        Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName)
    }

    public static Properties loadPropertiesFile(String fileName) {
        InputStream inputStream = loadResourceAsStream(fileName)
        try {
            Properties props = new Properties()
            props.load(inputStream)
            return props
        } finally {
            IOUtils.closeQuietly(inputStream)
        }
    }
}
