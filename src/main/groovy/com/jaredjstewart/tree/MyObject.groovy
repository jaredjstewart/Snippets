package com.jaredjstewart.tree

import groovy.transform.ToString

/**
 * Created by Jared on 6/21/2015.
 */

@ToString(includes=['id'])
class MyObject {
    String id
    String parentId
}
