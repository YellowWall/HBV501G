package hbv501g.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hbv501g.Classes.JsonResponse;
import hbv501g.Services.ForumService;
import hbv501g.Persistence.Entities.Forumpost;

@RestController
@RequestMapping("/forum")
public class ForumController {
    @Autowired
    private ForumService forumService;

    @GetMapping("/top")
    public JsonResponse<List<Forumpost>> getAllParentPosts(){
        List<Forumpost> threads = forumService.getAllThreads();
        if(threads != null){
            return new JsonResponse<List<Forumpost>>(true, "All threads returned", threads);
        }
        return new JsonResponse<List<Forumpost>>(false, "No threads found", null);
    };
    @GetMapping("/post")
    public JsonResponse<List<Forumpost>> getPostAndChildren(@RequestParam string postid){
        Long id = Long.parseLong(postid);
        List<Forumpost> thread = forumService.getThread(id);
        if(thread != null){
            return new JsonResponse<List<Forumpost>>(
                true, "thread returned", thread);
        }
        return new JsonResponse<List<Forumpost>>(
            false,"post not found", null);
    }

    @PostMapping("/newPost")
    public JsonResponse<Forumpost> postNewThread(@RequestBody Forumpost post){
        Forumpost nThread = forumService.newThread(post);
        if(nThread!=null){
            return new JsonResponse<Forumpost>(
                true,"posted",nThread
            );
        }
        return new JsonResponse<Forumpost>(
            false,"post not posted",null);
    };
    @PostMapping("/replyPost")
    public JsonResponse<Forumpost> postReply(@RequestParam String ppid,@RequestBody Forumpost post){
        Forumpost reply = forumService.savePost(post);
        return new JsonResponse<Forumpost>(
            false,"Not Implemented",null);
    }
    @PatchMapping("/editPost")
    public JsonResponse<Forumpost> editPost(@RequestBody Forumpost post){
        Forumpost reply = forumService.savePost(post);
        if(reply != null){
            return new JsonResponse<Forumpost>(true,"post edited",reply);
        }
        return new JsonResponse<Forumpost>(
            false,"Post not edited",null);
    };
    @DeleteMapping("/deletePost")
    public JsonResponse<Forumpost> deletePost(@RequestBody Forumpost post){
        boolean deleleted = forumService.deletePost(post);
        if(deleted){
            return new JsonResponse<Forumpost>(true,"Post Deleted",null);
        }
        return new JsonResponse<Forumpost>(
            false, "Post not deleted", null);
    }
}