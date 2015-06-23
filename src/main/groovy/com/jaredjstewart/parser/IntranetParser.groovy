package com.jaredjstewart.parser

import com.jaredjstewart.resource_loading.ResourceLoader
import com.jaredjstewart.tree.Employee
import groovy.util.slurpersupport.GPathResult
import org.apache.commons.lang.WordUtils
import org.ccil.cowan.tagsoup.Parser
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

/**
 * Created by Jared on 6/21/2015.
 */
class IntranetParser {

    static Employee populateEmployeeDetails(String detailsCfmHtml, Employee employee) {
        Document doc = Jsoup.parse(detailsCfmHtml)

        employee.managerName = doc.select("span:contains(My Manager)")?.first()?.parent()?.select('a')?.text()
        employee.department = doc.select("span:contains(Department)")?.first()?.parent()?.select('a')?.text()
        employee.workGroup = doc.select("span:contains(Work Group)")?.first()?.parent()?.select('a')?.text()

        return employee
    }

    static List<Employee> parseEmployeesFromHtmlUsingTagsoup(String allEmployeesHTML) {
        def PARSER = new XmlSlurper(new Parser())
        GPathResult gPathResult = PARSER.parseText(allEmployeesHTML)

        List<Employee> employees = gPathResult.depthFirst().find({ it.@class == 'resultstable' }).tbody.tr.collect { tr ->
            new Employee(title: tr.td[2].text().trim(),
                    uri: new URI(tr.td[2].a.@href.text().trim()),
                    name: formatName(tr.td[3].text()),
                    phone: tr.td[4].text().trim(),
                    email: tr.td[5].text().trim()
            )
        }
        employees.remove(0) //remove header row

        return employees
    }

    static List<Employee> parseEmployeesfromHtmlUsingJsoup(String allEmployeesHTML) {
        Document doc = Jsoup.parse(allEmployeesHTML)

        Elements employeeRows = doc.select('table.resultstable tbody tr:gt(0)')

        return employeeRows.collect { Element employeeRow ->
            Elements tds = employeeRow.select('td')

            new Employee(title: tds.get(2).select('a').text(),
                    uri: new URI(tds.get(2).select('a').attr('href')),
                    name: formatName(tds.get(3).text()),
                    phone: tds.get(4).text(),
                    email: tds.get(5).text())
        }

    }


    private static String formatName(String rawName) {
        WordUtils.capitalize(rawName.trim().toLowerCase())
    }
}
