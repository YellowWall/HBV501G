package hbv501g.service;

import org.springframework.data.jpa.repository.JpaRepository;

import hbv501g.objects.Field;

interface FieldService extends JpaRepository<Field,Long> {
    
}
