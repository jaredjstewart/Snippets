package com.jaredjstewart.http_client

import groovyx.net.http.HTTPBuilder

def url = "http://internalapps/sharepoint/employees/SearchAction.cfm?sort=title"
def html = buildBasicAuthRequest(url, 'username', 'password').get([:])

println html


static HTTPBuilder buildBasicAuthRequest(String url, String username, String password){
    HTTPBuilder http = new HTTPBuilder(url)
    http.headers['Authorization'] = "Basic $username:$password".getBytes('iso-8859-1').encodeBase64()
    return http
}