package hbv501g.Controllers;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import hbv501g.Classes.JsonResponse;
import hbv501g.Services.ForumService;
import hbv501g.Services.UserService;
import hbv501g.Persistence.Entities.Forumpost;
import hbv501g.Persistence.Entities.User;
import hbv501g.objects.ReturnPost;

@RestController
@CrossOrigin("*")
@RequestMapping("/forum")
public class ForumController {
    @Autowired
    private ForumService forumService;
    @Autowired
    private UserService userService;

    @GetMapping("/top")
    public JsonResponse<List<ReturnPost>> getAllParentPosts(){
        List<Forumpost> threads = forumService.getAllThreads();
        if(threads != null){
            List<ReturnPost> returnPosts = new ArrayList<ReturnPost>();
            for(Forumpost post: threads){
                User user = userService.getUserById(post.getPlayerId());
                if(user != null){
                    returnPosts.add(
                        new ReturnPost(
                            post.getTitle(),
                            post.getText(),
                            post.getId(),
                            post.getParentPostId(),
                            user.getUsername()));
                }
            }
            return new JsonResponse<List<ReturnPost>>(true, "All threads returned", returnPosts);
        }
        return new JsonResponse<List<ReturnPost>>(false, "No threads found", null);
    };
    @GetMapping("/post")
    public JsonResponse<List<ReturnPost>> getPostAndChildren(@RequestParam String postid){
        Long id = Long.parseLong(postid);
        List<Forumpost> thread = forumService.getThread(id);
        if(thread != null){
            List<ReturnPost> returnPosts = new ArrayList<ReturnPost>();
            for(Forumpost post: thread){
                User user = userService.getUserById(post.getPlayerId());
                if(user != null){
                    returnPosts.add(
                        new ReturnPost(
                            post.getTitle(),
                            post.getText(),
                            post.getId(),
                            post.getParentPostId(),
                            user.getUsername()));
                }
            }
            return new JsonResponse<List<ReturnPost>>(
                true, "thread returned", returnPosts);
        }
        return new JsonResponse<List<ReturnPost>>(
            false,"post not found", null);
    }
    @GetMapping("/post/focus")
    public JsonResponse<ReturnPost> getPostOnly(@RequestParam String postid){
        System.out.println(postid);
        Long id = Long.parseLong(postid);
        List<Forumpost> thread = forumService.getThread(id);
        if(thread != null){
            Forumpost post = thread.get(0);
            User user = userService.getUserById(post.getPlayerId());
            ReturnPost returnPosts = new ReturnPost(post.getTitle(),
                            post.getText(),
                            post.getId(),
                            post.getParentPostId(),
                            user.getUsername());
            return new JsonResponse<ReturnPost>(
                true, "post returned", returnPosts);
        }
        return new JsonResponse<ReturnPost>(
            false,"post not found", null);
    }

    @PostMapping("/newPost")
    public JsonResponse<Forumpost> postNewThread(@RequestBody ReturnPost post){
        User user = userService.getUser(post.getUsername());
        Forumpost sendpost = new Forumpost(post.getTitle(),post.getText(),user.getId(),Long.parseLong("0"));
        Forumpost nThread = forumService.newThread(sendpost);
        if(nThread!=null){
            return new JsonResponse<Forumpost>(
                true,"posted",nThread
            );
        }
        return new JsonResponse<Forumpost>(
            false,"post not posted",null);
    };
    @PostMapping("/replyPost")
    public JsonResponse<Forumpost> postReply(@RequestParam String ppid,@RequestBody ReturnPost res){
        Long parId = Long.parseLong(ppid);
        if(parId!=null){
            User user = userService.getUser(res.getUsername());
            Forumpost post = new Forumpost(res.getTitle(),res.getText(),user.getId(),parId);
            Forumpost reply = forumService.savePost(post);
            if(reply != null){
                return new JsonResponse<Forumpost>(
                    true,"reply posted",reply
                );
            }
            return new JsonResponse<Forumpost>(
                false,"bad post thingy",null
            );
        }
        
        return new JsonResponse<Forumpost>(
            false,"Bad ppid",null);
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
        boolean deleted = forumService.deletePost(post);
        if(deleted){
            return new JsonResponse<Forumpost>(true,"Post Deleted",null);
        }
        return new JsonResponse<Forumpost>(
            false, "Post not deleted", null);
    }
}