package com.example.package404.instructor.model;

import com.example.package404.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String record;

    private String portfolio;





    @OneToOne
    @JoinColumn(name = "user_idx")
    private User user;


    @OneToOne(mappedBy = "instructor")
    private Course course;

}
