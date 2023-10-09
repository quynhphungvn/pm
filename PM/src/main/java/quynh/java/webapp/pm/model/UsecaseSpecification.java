package quynh.java.webapp.pm.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usecase_specification")
public class UsecaseSpecification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "created_by")
    private String createBy;
    @Column(name = "created_date")
    private LocalDate createdDate;
    private String actors;
    private String trigger;
    private String description;
    private String preconditions;
    private String postcondition;
    @Column(name = "normal_flow")
    private String normalFlow;
    @Column(name = "alternative_flow")
    private String alternativeFlow;
    private String exceptions;
    private String priority;
    @Column(name = "frequency_of_use")
    private String frequencyOfUse;
    @Column(name = "bussiness_rules")
    private String bussinessRules;
    @Column(name = "other_infomations")
    private String otherInformation;
    private String assumptions;
    @OneToOne
    private Usecase usecase;
}
