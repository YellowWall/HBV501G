package hbv501g.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hbv501g.Persistence.Repositories.ForumRepository;
import hbv501g.Persistence.Entities.Forumpost;

@Service
public class ForumService {
    @Autowired
    private ForumRepository forumRepository;

    /**
    * top
    * getthread
    * newthread
    * newReply
    * editPost
    * deletePost
    */

    public List<Forumpost> getAllThreads(){
        List<Forumpost> threadlist = forumRepository.findByParentPostId(0);
        return threadlist;
    }   
    public List<Forumpost> getThread(Long postid){
        var parentpost = forumRepository.findById(postid);
        if(parentpost.get()!=null){
            List<Forumpost> retlist = forumRepository.findByParentPostId(postid);
            retlist.add(0,parentpost.get());
            return retlist;
        }
        return null;
    }  
    public Forumpost newThread(Forumpost post){
        post.setParentPostId(0);
        return savePost(post);
    }
    public Forumpost savePost(Forumpost post){
        post.update();
        Forumpost posted = forumRepository.save(post);
        return posted;
    }
    public boolean deletePost(Forumpost post){
        forumRepository.delete(post);
        var test = forumRepository.findById(post.getId());
        if(test != null){
            return false;
        }
        return true;

    }   
}
