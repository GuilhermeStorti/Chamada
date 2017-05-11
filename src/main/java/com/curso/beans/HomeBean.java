package com.curso.beans;

import com.curso.entity.Arquivo;
import com.curso.entity.Turma;
import com.curso.entity.Usuario;
import com.curso.utils.ArquivoUtil;
import com.curso.utils.JpaUtil;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@ManagedBean(name = "homeBean")
public class HomeBean implements Serializable{

    private static final long serialVersionUID = 1L;

    private List<File> arquivos = new ArrayList<>();

    private UploadedFile uploadedFile;

    @PostConstruct
    public void postConstruct() {
        arquivos = new ArrayList<>(ArquivoUtil.listar());
    }

    public void upload() {
        try {
            File arquivo = ArquivoUtil.escrever(uploadedFile.getFileName(), uploadedFile.getContents());

            persistir();
            //arquivos.add(arquivo);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Upload completo", "O arquivo " + arquivo.getName() + " foi salvo!"));
            limpar();
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
        }
    }

    private void persistir() {
        EntityManager manager = JpaUtil.getManager();
        manager.getTransaction().begin();
        Arquivo arquivo = new Arquivo();
        arquivo.setDados(uploadedFile.getContents());
        arquivo.setDescricao(uploadedFile.getFileName());

        List<Turma> turmas = manager.createQuery("from Turma", Turma.class).getResultList();

        List<Usuario> usuarios = manager.createQuery("from Usuario", Usuario.class).getResultList();

        arquivo.setIdTurma(turmas.get(0));
        arquivo.setIdUsuarioUpload(usuarios.get(0));
        manager.merge(arquivo);
        manager.getTransaction().commit();
        manager.close();
    }

    private void limpar() {
        this.arquivos.clear();
        this.uploadedFile = null;
    }

    public List<File> getArquivos() {
        return arquivos;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
}
