package quynh.java.webapp.pm.model;

import java.util.List;

import jakarta.persistence.CascadeType;
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
@Table(name = "class_spec")
public class ClassSpec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "detail_content")
    private String detailContent;
    @ManyToOne
    @JoinColumn(name = "class_package_id")
    private ClassPackage classPackage;
    @OneToMany(mappedBy = "classSpec", cascade = CascadeType.ALL)
    private List<TestingFunction> testingFunctions;
}