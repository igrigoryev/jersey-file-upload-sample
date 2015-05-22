package com.igrigoryev.samples.jersey;

import java.util.List;

public class FileResponse {
    List<FileMeta> files;

    public FileResponse() {
    }

    public FileResponse(List<FileMeta> files) {
        this.files = files;
    }

    public List<FileMeta> getFiles() {
        return files;
    }

    public void setFiles(List<FileMeta> files) {
        this.files = files;
    }
}
