package com.jaredjstewart.http_client

import org.apache.http.HttpEntity
import org.apache.http.HttpHost
import org.apache.http.HttpResponse
import org.apache.http.auth.AuthScope
import org.apache.http.auth.NTCredentials
import org.apache.http.auth.params.AuthPNames
import org.apache.http.client.ClientProtocolException
import org.apache.http.client.CredentialsProvider
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.params.AuthPolicy
import org.apache.http.impl.client.BasicCredentialsProvider
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.impl.client.ProxyAuthenticationStrategy
import org.apache.http.protocol.BasicHttpContext
import org.apache.http.protocol.HttpContext
import org.apache.http.util.EntityUtils

/**
 * Created by Jared on 6/21/2015.
 */
class HttpClient {
    DefaultHttpClient httpclient
    HttpContext localContext

    HttpClient(String username, String password) {
        httpclient = new DefaultHttpClient();
        List<String> authpref = new ArrayList<String>();
        authpref.add(AuthPolicy.NTLM);
        httpclient.getParams().setParameter(AuthPNames.TARGET_AUTH_PREF, authpref);
        NTCredentials creds = new NTCredentials(username, password, "localhost", "CARFAX");
        httpclient.getCredentialsProvider().setCredentials(AuthScope.ANY, creds);

        // Make sure the same context is used to execute logically related requests
        localContext = new BasicHttpContext();

    }

     public void testConnection() throws ClientProtocolException, IOException {

        HttpHost target = new HttpHost("internalapps", 80, "http");

        // Execute a cheap method first. This will trigger NTLM authentication
        HttpGet httpget = new HttpGet("/sharepoint/employees/SearchAction.cfm?sort=title");
        HttpResponse response = httpclient.execute(target, httpget, localContext);
        HttpEntity entity = response.getEntity();
        System.out.println(EntityUtils.toString(entity));

    }
//
//    public void testConnection2() {
//        NTCredentials ntCreds = new NTCredentials(ntUsername, ntPassword,localMachineName, domainName );
//
//        CredentialsProvider credsProvider = new BasicCredentialsProvider();
//        credsProvider.setCredentials( new AuthScope(proxyHost,proxyPort), ntCreds );
//        HttpClientBuilder clientBuilder = HttpClientBuilder.create();
//
//        clientBuilder.useSystemProperties();
//        clientBuilder.setProxy(new HttpHost(pxInfo.getProxyURL(), pxInfo.getProxyPort()));
//        clientBuilder.setDefaultCredentialsProvider(credsProvider);
//        clientBuilder.setProxyAuthenticationStrategy(new ProxyAuthenticationStrategy());
//
//        CloseableHttpClient client = clientBuilder.build();
//    }
}
