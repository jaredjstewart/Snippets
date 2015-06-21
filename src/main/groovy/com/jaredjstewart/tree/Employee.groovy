package com.jaredjstewart.tree

import groovy.transform.ToString

/**
 * Created by Jared on 6/21/2015.
 */

@ToString
class Employee {
    String name
    String title
    String email
    String phone

    URI uri

    String managerName
    String department
}
