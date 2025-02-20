package com.example.package404.instructor.model;


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


    @OneToOne
    @JoinColumn(name="user_idx")
    private Instructor instructor;
}
