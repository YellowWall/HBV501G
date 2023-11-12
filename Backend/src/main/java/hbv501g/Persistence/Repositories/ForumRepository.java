package hbv501g.Persistence.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hbv501g.Persistence.Entities.Forumpost;

public interface ForumRepository extends JpaRepository<Forumpost,Long> {

    public List<Forumpost> findByParentPostId(long postid);
}
