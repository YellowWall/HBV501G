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
        return new JsonResponse<List<Forumpost>>(false, "Not yet Implemented", null);
    };
    @GetMapping("/post")
    public JsonResponse<List<Forumpost>> getPostAndChildren(){
        return new JsonResponse<List<Forumpost>>
        (false, "Not implemented", null);
    }

    @PostMapping("/newPost")
    public JsonResponse<Forumpost> postNewThread(@RequestBody Forumpost post){
        return new JsonResponse<Forumpost>(
            false,"Not Implemented yet",null);
    };
    @PostMapping("/replyPost")
    public JsonResponse<Forumpost> postReply(@RequestParam String ppid,@RequestBody Forumpost post){
        return new JsonResponse<Forumpost>(
            false,"Not Implemented",null);
    }
    @PatchMapping("/editPost")
    public JsonResponse<Forumpost> editPost(@RequestBody Forumpost post){
        return new JsonResponse<Forumpost>(
            false,"Not Implemented yet",null);
    };
    @DeleteMapping("/deletePost")
    public JsonResponse<Forumpost> deletePost(@RequestBody Forumpost post){
        return new JsonResponse<Forumpost>(
            false, "Not Implemented", null);
    }
}
