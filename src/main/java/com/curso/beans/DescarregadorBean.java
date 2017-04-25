package com.curso.beans;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.faces.bean.ManagedBean;
import java.io.*;
import java.nio.file.Files;

@ManagedBean(name = "descarregadorBean")
public class DescarregadorBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private StreamedContent streamedContent;
    
    public void descarregar(File file) throws IOException {
        InputStream inputStream = new FileInputStream(file);
        
        streamedContent = new DefaultStreamedContent(inputStream, 
                Files.probeContentType(file.toPath()), file.getName());
    }
    
    public StreamedContent getStreamedContent() {
        return streamedContent;
    }
}