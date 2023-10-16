package hbv501g.service;

import org.springframework.data.jpa.repository.JpaRepository;

import hbv501g.objects.Game;

interface UserService extends JpaRepository<Game,Long> {



}
