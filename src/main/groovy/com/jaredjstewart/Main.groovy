package com.jaredjstewart

import groovy.util.logging.Slf4j

@Slf4j
class Main {

    public static void main(String[] args) {
        log.info("start")
        log.debug(sleep(10000))
        log.info ('we done')
    }
}
