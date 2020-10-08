package com.self.learn.google.api.config;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.self.learn.google.api.service.SheetService;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.*;


public final class GoogleSheetConfiguration {

    private volatile static GoogleSheetConfiguration INSTANCE = null;

    private static Properties properties;

    private GoogleSheetConfiguration(Properties properties) {
        this.properties = properties;
    }

    public static GoogleSheetConfiguration getInstance(String fileName) {
        if (Objects.isNull(INSTANCE)) {
            try (InputStream is = GoogleSheetConfiguration.class.getClassLoader().getResourceAsStream(fileName)) {
                properties = new Properties();
                properties.load(is);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new GoogleSheetConfiguration(properties);
        }
        return INSTANCE;
    }


    public static Sheets getSheetService() {

        try {
            Credential credential = authorize();
            return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(),
                    JacksonFactory.getDefaultInstance(), credential).
                    setApplicationName(properties.getProperty("application.name")).
                    build();
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
        return (Sheets) Optional.empty().get();
    }


    private static Credential authorize() throws IOException, GeneralSecurityException {
        InputStream in = GoogleSheetConfiguration.class.getClassLoader().getResourceAsStream(properties.getProperty("file.path"));
        GoogleClientSecrets secrets =
                GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), new InputStreamReader(in));
        List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(),
                secrets,
                scopes
        ).setDataStoreFactory(new FileDataStoreFactory(new File(properties.getProperty("file.data.storage"))))
                .setAccessType(properties.getProperty("access.type")).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize(properties.getProperty("authorize.name"));
        return credential;
    }
}
