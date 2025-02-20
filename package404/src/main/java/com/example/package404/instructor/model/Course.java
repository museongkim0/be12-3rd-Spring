package com.example.package404.instructor.model;


import com.example.package404.manager.model.Test;
import com.example.package404.student.model.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String name;
    private int generation;

    @OneToMany(mappedBy = "course")
    private List<Curriculum> sectionList = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<Test> testList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name="user_idx")
    private Instructor instructor;
}
