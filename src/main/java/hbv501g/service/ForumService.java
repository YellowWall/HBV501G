package hbv501g.service;

import org.springframework.data.jpa.repository.JpaRepository;

import hbv501g.objects.Forumpost;

interface ForumService extends JpaRepository<Forumpost,Long> {
    
}
