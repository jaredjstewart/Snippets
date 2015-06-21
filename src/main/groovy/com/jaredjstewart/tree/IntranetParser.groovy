package com.jaredjstewart.tree

import com.jaredjstewart.resource_loading.ResourceLoader
import groovy.util.slurpersupport.GPathResult
import org.ccil.cowan.tagsoup.Parser

/**
 * Created by Jared on 6/21/2015.
 */
class IntranetParser {

    static List<Employee> getAllEmployeesFromFile(String filename){
        String allEmployeesHTML = new File(ResourceLoader.loadResourceAsURI(filename)).text
        return parseEmployeesFromHtml(allEmployeesHTML)
    }

    static List<Employee> parseEmployeesFromHtml(String allEmployeesHTML) {
        def PARSER = new XmlSlurper(new Parser())
        GPathResult gPathResult = PARSER.parseText(allEmployeesHTML)

        List<Employee> employees = gPathResult.depthFirst().find({ it.@class == 'resultstable' }).tbody.tr.collect { it ->
            new Employee(title: it.td[2].text().trim(),
                    uri : new URI(it.td[2].a.@href.text().trim()),
                    name : it.td[3].text().trim().toLowerCase(),
                    phone: it.td[4].text().trim(),
                    email: it.td[5].text().trim()
            )
        }
        employees.remove(0) //remove header row

        return employees
    }

}
