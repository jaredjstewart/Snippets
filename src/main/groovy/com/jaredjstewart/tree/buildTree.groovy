package com.jaredjstewart.tree

import com.jaredjstewart.http_client.IntranetClient
import com.jaredjstewart.parser.IntranetParser
import org.ccil.cowan.tagsoup.Parser

def employees = [new MyObject(id: 'a'),
                 new MyObject(id: 'b', parentId: 'a'),
                 new MyObject(id: 'c', parentId: 'a'),
                 new MyObject(id: 'd', parentId: 'f'),
                 new MyObject(id: 'f', parentId: 'c')
]



def PARSER = new XmlSlurper(new Parser())
def url = "http://internalapps/sharepoint/employees/SearchAction.cfm?sort=title"

//List<Employee> employees1 = IntranetParser.getAllEmployeesFromFile('AllEmployees.html')


//def tree = TreeBuilder.buildTree(employees)

//new IntranetClient().retrieveAllEmployees()

//def emps = IntranetParser.getAllEmployeesFromFile('AllEmployees.html')


IntranetClient client = new IntranetClient()

def emps = client.retrieveAllEmployees()

emps.findAll({it.title}).first().each {

    Employee employee ->
       println IntranetParser.populateEmployeeDetails(client.retrieveEmployeePage(employee.uri))
}