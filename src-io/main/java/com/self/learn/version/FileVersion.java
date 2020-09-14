package com.self.learn.version;


import java.time.LocalDate;

public class FileVersion {

    private String versionNumber;
    private LocalDate lastModified;
    private String fileId;
    private String sheetId;
    private VersionContent versionContent;

    public FileVersion(String versionNumber, LocalDate lastModified, VersionContent versionContent) {
        this.versionNumber = versionNumber;
        this.lastModified = lastModified;
        this.versionContent = versionContent;
    }


    public String getVersionNumber() {
        return versionNumber;
    }

    public LocalDate getLastModified() {
        return lastModified;
    }

    public VersionContent getVersionContent() {
        return versionContent;
    }
}
