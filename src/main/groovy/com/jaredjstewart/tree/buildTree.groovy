package com.jaredjstewart.tree
import org.ccil.cowan.tagsoup.Parser

def employees = [new MyObject(id: 'a'),
                 new MyObject(id: 'b', parentId: 'a'),
                 new MyObject(id: 'c', parentId: 'a'),
                 new MyObject(id: 'd', parentId: 'f'),
                 new MyObject(id: 'f', parentId: 'c')
]

def tree = TreeBuilder.buildTree(employees)



String ENCODING = "UTF-8"

//def PARSER = new XmlSlurper(new Parser() )
def url = "http://internalapps/sharepoint/employees/SearchAction.cfm?sort=title"
//
//println url.toString()
//
//
//GPathResult document
//
//new URL(url).withReader (ENCODING) { reader ->
//     document = PARSER.parse(reader)
//}
//
//println document.'**'.findAll ({ it.@class == 'results-table' }).size()