package quynh.java.webapp.pm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usecase")
public class Usecase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "activity_diagram")
    private String activityDiagram;
    @Column(name = "sequence_diagram")
    private String sequenceDiagram;
    @OneToOne(mappedBy = "usecase")
    private UsecaseSpecification usecaseSpecification;
    @ManyToOne
    @JoinColumn(name = "domain_id")
    private Domain domain;
}