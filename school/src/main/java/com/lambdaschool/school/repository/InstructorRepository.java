package com.lambdaschool.school.repository;

import com.lambdaschool.school.model.Instructor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface InstructorRepository extends PagingAndSortingRepository<Instructor, Long>
{
    ArrayList<Instructor> findInstructorsByInstructnameEquals (String name);
}
