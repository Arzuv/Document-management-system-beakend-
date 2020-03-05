package tm.arzuv.app.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tm.arzuv.app.Service.UserService;
import tm.arzuv.app.dao.DocumentViewModel;
import tm.arzuv.app.model.Document;
import tm.arzuv.app.repository.DocumentRepository;
import tm.arzuv.app.repository.UserRepository;

@Service
public final class DocumentServiceImpl {
    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

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
}