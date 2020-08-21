package com.self.learn.reader.io.base.impl;

import com.self.learn.reader.io.base.ImportHandler;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;

public class HttpResponseHandlerImpl extends ImportHandler {

    @Override
    public InputStream openStream() {
        HttpGet request = new HttpGet("http://httpstat.us/200");
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
            this.reader.read(response.getEntity().getContent());
            return response.getEntity().getContent();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void validateContent() {

    }
}
