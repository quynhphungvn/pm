package quynh.java.webapp.pm.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "domain")
public class Domain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String overview;
    private String scope;
    private String technology;
    private String note;
    @Column(name="view_setting")
    private String viewSetting;
    @Column(name= "requirement")
    private String requirement;
    @Column(name = "erd_diagram")
    private String erdDiagram;
    @Column(name = "class_diagram")
    private String classDiagram;
    @Column(name = "created_date")
    private LocalDate createdDate;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    @OneToMany(mappedBy = "domain")
    private List<PlanDiagram> planDiagrams;
    @OneToMany(mappedBy = "domain")
    private List<Screen> screens;
    @OneToMany(mappedBy = "domain")
    private List<Usecase> usecases;
    @OneToMany(mappedBy = "domain")
    private List<ClassPackage> classPackages;
    @OneToMany(mappedBy = "domain")
    private List<SqlQuery> sqlQueries;
}
