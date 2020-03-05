package tm.arzuv.app.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import tm.arzuv.app.Service.DocumentService;
import tm.arzuv.app.dao.DocumentViewModel;
import tm.arzuv.app.model.Document;
import tm.arzuv.app.repository.DocumentRepository;

@Service
public final class DocumentServiceImpl implements DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

	@Override
	public List<Document> findAll() {
		return documentRepository.findAll();
    }
    
    @Override
    public List<Document> findAllAndSort(String condition) {
        return documentRepository.findAll(Sort.by(condition));
    }

	@Override
	public Document findById(int id) {
		return documentRepository.findById(id);
	}

	@Override
	public Document findByName(String name) {
		return documentRepository.findByDname(name);
	}

	@Override
	public List<Document> findByWhomContract(String whomContract) {
		return documentRepository.findByWhomContract(whomContract);
	}

	@Override
	public List<Document> findByWhoContracted(String whoContracted) {
		return documentRepository.findByWhoContracted(whoContracted);
	}

	@Override
	public Document save(Document d) {
		return documentRepository.save(d);
	}

	@Override
	public void delete(int id) {
		documentRepository.deleteById(id);
    }
    @Override
    public DocumentViewModel convertToDocumentViewModel(Document d) {
        return new DocumentViewModel();
    }
    
    @Override
    public Document convertToDocument(DocumentViewModel d) {
        
        return new Document();
    }
}