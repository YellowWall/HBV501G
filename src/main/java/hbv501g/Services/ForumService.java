package hbv501g.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hbv501g.Persistence.Repositories.ForumRepository;

@Service
public class ForumService {
    @Autowired
    private ForumRepository forumRepository;
    
}
