package ru.rellai.ecrf.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "menu")
@Getter
@Setter
public class Menu {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private Long menuId;
        private String name;
        private String url;
        private String status; //  State 0 available 1 disabled

        @ManyToOne
        @JoinColumn(name = "parent_id")
        private Menu menu;

        @OneToMany(mappedBy = "menu", cascade = CascadeType.REMOVE, orphanRemoval = true)
        private List<Menu> childs = new ArrayList<>();

}
