package hbv501g.Controllers;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import hbv501g.Classes.JsonResponse;
import hbv501g.Services.ForumService;
import hbv501g.Services.UserService;
import hbv501g.Persistence.Entities.Forumpost;
import hbv501g.Persistence.Entities.User;
import hbv501g.objects.ReturnPost;
import hbv501g.objects.ForumInput;

@RestController
@CrossOrigin
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
                            post,
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
                            post,
                            user.getUsername()));
                }
            }
            return new JsonResponse<List<ReturnPost>>(
                true, "thread returned", returnPosts);
        }
        return new JsonResponse<List<ReturnPost>>(
            false,"post not found", null);
    };
    @GetMapping("/post/focus")
    public JsonResponse<ReturnPost> getPostOnly(@RequestParam String postid){
        System.out.println(postid);
        Long id = Long.parseLong(postid);
        List<Forumpost> thread = forumService.getThread(id);
        if(thread != null){
            Forumpost post = thread.get(0);
            User user = userService.getUserById(post.getPlayerId());
            ReturnPost returnPosts = new ReturnPost(post,
                            user.getUsername());
            return new JsonResponse<ReturnPost>(
                true, "post returned", returnPosts);
        }
        return new JsonResponse<ReturnPost>(
            false,"post not found", null);
    };

    @PostMapping("/newPost")
    public JsonResponse<ReturnPost> postNewThread(@RequestBody ForumInput post){
        User user = userService.getUser(post.getUsername());
        post.setParentPostId(0L);
        Forumpost sendpost = new Forumpost(post,user.getId());
        Forumpost nThread = forumService.savePost(sendpost);
        if(nThread!=null){
            ReturnPost returnPost = new ReturnPost(sendpost,
                    user.getUsername()
                    );
            return new JsonResponse<ReturnPost>(
                true,"posted",returnPost
            );
        }
        return new JsonResponse<ReturnPost>(
            false,"post not posted",null);
    };
    @PostMapping("/replyPost")
    public JsonResponse<ReturnPost> postReply(@RequestParam String ppid,@RequestBody ForumInput req){
        Long parId = Long.parseLong(ppid);
        if(parId!=null){
            User user = userService.getUser(req.getUsername());
            req.setParentPostId(Long.parseLong(ppid));
            Forumpost post = new Forumpost(req,user.getId());
            Forumpost reply = forumService.savePost(post);
            if(reply != null){
                ReturnPost returnPost = new ReturnPost(reply,
                    user.getUsername()
                    );
                return new JsonResponse<ReturnPost>(
                    true,"reply posted",returnPost
                );
            }
            return new JsonResponse<ReturnPost>(
                false,"bad post thingy",null
            );
        }
        
        return new JsonResponse<ReturnPost>(
            false,"Bad ppid",null);
    };
    @PatchMapping("/editPost")
    public JsonResponse<ReturnPost> editPost(@RequestBody ForumInput req){
        User user = userService.getUser(req.getUsername());
        Forumpost post = forumService.getPostOnly(req.getId());
        post.setText(req.getText());
        post.update();
        Forumpost reply = forumService.savePost(post);
        if(reply != null){
            return new JsonResponse<ReturnPost>(true,"post edited",new ReturnPost(reply,user.getUsername()));
        }
        return new JsonResponse<ReturnPost>(
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
    };
}