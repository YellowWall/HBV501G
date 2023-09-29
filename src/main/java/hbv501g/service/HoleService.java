package hbv501g.service;

import org.springframework.data.jpa.repository.JpaRepository;

import hbv501g.objects.Hole;

interface HoleService extends JpaRepository<Hole,Long> {
    
}
