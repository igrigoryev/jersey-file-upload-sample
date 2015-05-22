package com.igrigoryev.samples.jersey;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Path("/files")
public class JerseyFileUpload {
    public static final String SERVER_UPLOAD_LOCATION_FOLDER = "C://Users/Grigoryev/Work/Tests/upload-folder/";

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public FileResponse uploadFile(
            @FormDataParam("files[]") InputStream fileInputStream,
            @FormDataParam("files[]") FormDataContentDisposition contentDispositionHeader) {

        String filePath = SERVER_UPLOAD_LOCATION_FOLDER + contentDispositionHeader.getFileName();
        FileMeta fileMeta = saveFile(fileInputStream, filePath);
        List<FileMeta> files = Arrays.asList(fileMeta);
        FileResponse response = new FileResponse(files);
        return response;
    }

    private FileMeta saveFile(InputStream uploadedInputStream, String serverLocation) {
        File file = new File(serverLocation);
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            int read;
            byte[] bytes = new byte[1024];

            while ((read = uploadedInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new FileMeta(file.getName(), (int) file.length(), "", "", "", "");
    }
}

