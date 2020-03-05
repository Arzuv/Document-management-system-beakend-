package tm.arzuv.app.Service.impl;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import tm.arzuv.app.Service.UserService;
import tm.arzuv.app.dao.DocumentViewModel;
import tm.arzuv.app.model.Document;
import tm.arzuv.app.model.User;
import tm.arzuv.app.repository.DocumentRepository;
import tm.arzuv.app.repository.UserRepository;

@Service
public class DocumentServiceImpl {
    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    public boolean setDocument(User user, String name, MultipartFile file) {
        Document d = new Document();
        d.setUser(user);
        d.setName_document(name);
        d.setStatus_document(true);
        d.setData_creat(new Date());
        if (!file.isEmpty() && file != null) {
            try {
                d.setFile_document(file.getBytes());
                System.out.println(new String(file.getBytes()));
                documentRepository.save(d);
            } catch(IOException e) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    public ModelAndView readDocuments() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("documents", documentRepository.findAll());
        return mv;
    }

    public DocumentViewModel convertToDocumentViewModel(Document d) {
        DocumentViewModel dvm = new DocumentViewModel();
        dvm.setId(d.getId());
        dvm.setName_document(d.getName_document());
        dvm.setStatus_document(d.isStatus_document());
        dvm.setData_creat(d.getData_creat());
        dvm.setFile_document(new String(d.getFile_document()));
        dvm.setUser(userService.convertToUserViewModel(d.getUser()));
        return dvm;
    }

    public Document convertToDocument(DocumentViewModel d) {
        Document document = new Document();
        document.setName_document(d.getName_document());
        document.setData_creat(d.getData_creat());
        document.setStatus_document(d.isStatus_document());
        document.setFile_document(d.getFile_document().getBytes());
        document.setUser(userRepository.findById(d.getUser().getId()));
        return document;
    }

    public Document saveDocument(Document d) {
        return documentRepository.save(d);
    }
}