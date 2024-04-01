package ru.rellai.ecrf.auth.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
