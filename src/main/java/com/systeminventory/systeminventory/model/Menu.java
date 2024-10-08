package com.systeminventory.systeminventory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Menu {
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idMenu;

    @Column(nullable = false,length = 20)
    private String icon;

    @Column(nullable = false,length = 20)
    private String name;

    @Column(nullable = false,length = 30)
    private String url;

    //para consultar una tabla recursiva
    /*@ManyToOne
    @JoinColumn(name = "id_sub_menu")
    private Menu menu;*/

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "menu_roles",
        joinColumns = @JoinColumn(name = "id_menu", referencedColumnName = "idMenu"),
        inverseJoinColumns = @JoinColumn(name = "id_role",referencedColumnName = "idRole")
    )
    private List<Role> roles;


}
