package com.jaredjstewart.tree

import groovy.transform.ToString

/**
 * Created by Jared on 6/21/2015.
 */

@ToString
class Node {
    MyObject employee

    Node parent
    List<Node> children = []
}
