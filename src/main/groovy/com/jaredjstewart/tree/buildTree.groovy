package com.jaredjstewart.tree

import com.jaredjstewart.resource_loading.ResourceLoader
import groovy.util.slurpersupport.GPathResult
import org.apache.commons.io.FileUtils
import org.apache.commons.lang.WordUtils
import org.ccil.cowan.tagsoup.Parser

def employees = [new MyObject(id: 'a'),
                 new MyObject(id: 'b', parentId: 'a'),
                 new MyObject(id: 'c', parentId: 'a'),
                 new MyObject(id: 'd', parentId: 'f'),
                 new MyObject(id: 'f', parentId: 'c')
]



def PARSER = new XmlSlurper(new Parser())
def url = "http://internalapps/sharepoint/employees/SearchAction.cfm?sort=title"

List<Employee> employees1 = IntranetParser.getAllEmployeesFromFile('AllEmployees.html')

//def tree = TreeBuilder.buildTree(employees)

employees1.each {
    println it
}



