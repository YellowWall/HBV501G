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
    public Forumpost getPostOnly(Long postid){
        Forumpost post = forumRepository.getById(postid);
        if(post!=null){
            return post;
        }
        return null;
    }
    public List<Forumpost> getAllThreads(){
        List<Forumpost> threadlist = forumRepository.findByParentPostId(0);
        return threadlist;
    }   
    public List<Forumpost> getThread(Long postid){
        Forumpost parentpost = forumRepository.getById(postid);
        if(parentpost!=null){
            List<Forumpost> retlist = forumRepository.findByParentPostId(postid);
            retlist.add(0,parentpost);
            return retlist;
        }
        return null;
    }  
    public Forumpost savePost(Forumpost post){
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
