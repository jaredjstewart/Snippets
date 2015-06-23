package com.jaredjstewart.http_client

import com.jaredjstewart.parser.IntranetParser
import com.jaredjstewart.resource_loading.ResourceLoader
import com.jaredjstewart.tree.Employee
import org.apache.commons.lang.time.StopWatch
import org.apache.http.HttpEntity
import org.apache.http.HttpHost
import org.apache.http.HttpResponse
import org.apache.http.auth.AuthScope
import org.apache.http.auth.NTCredentials
import org.apache.http.auth.params.AuthPNames
import org.apache.http.client.ClientProtocolException
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.params.AuthPolicy
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.protocol.BasicHttpContext
import org.apache.http.protocol.HttpContext
import org.apache.http.util.EntityUtils

/**
 * Created by Jared on 6/21/2015.
 */
class IntranetClient {
    DefaultHttpClient httpclient
    HttpContext localContext

    IntranetClient() {
        Properties properties = ResourceLoader.loadPropertiesFile('credentials.properties')
        String username = properties.getProperty('username')
        String password = properties.getProperty('password')


        httpclient = new DefaultHttpClient();
        List<String> authpref = new ArrayList<String>();
        authpref.add(AuthPolicy.NTLM);
        httpclient.getParams().setParameter(AuthPNames.TARGET_AUTH_PREF, authpref);
        NTCredentials creds = new NTCredentials(username, password, "localhost", "CARFAX");
        httpclient.getCredentialsProvider().setCredentials(AuthScope.ANY, creds);

        // Make sure the same context is used to execute logically related requests
        localContext = new BasicHttpContext();
        httpclient.connectionManager

    }

    public List<Employee> retrieveAllEmployees() {
        List<Employee> employees = IntranetParser.parseEmployeesfromHtmlUsingJsoup(retrieveAllEmployeesPageHtml())

        employees.collect { Employee employee ->
            String employeeDetailsHtml = retrieveEmployeePage(employee.uri)
            IntranetParser.populateEmployeeDetails(employeeDetailsHtml, employee)
        }
    }

    public String retrieveEmployeePage(URI employeeUri) {
        HttpHost target = new HttpHost("internalapps", 80, "http");
        HttpGet httpget = new HttpGet("/sharepoint/employees/details.cfm?" + employeeUri.query)

        HttpResponse response = httpclient.execute(target, httpget, localContext);
        HttpEntity entity = response.getEntity();
        return EntityUtils.toString(entity)
    }

    private String retrieveAllEmployeesPageHtml() throws ClientProtocolException, IOException {
        HttpHost target = new HttpHost("internalapps", 80, "http");
        HttpGet httpget = new HttpGet("/sharepoint/employees/SearchAction.cfm?sort=title");

        HttpResponse response = httpclient.execute(target, httpget, localContext);
        HttpEntity entity = response.getEntity();
        return EntityUtils.toString(entity)
    }
}
