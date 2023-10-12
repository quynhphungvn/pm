package quynh.java.webapp.pm.model;

import java.time.LocalDate;

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
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_date")
    private LocalDate createdDate;
    private String actors;
    @Column(name="trigger_context")
    private String triggerContext;
    private String description;
    private String preconditions;
    private String postconditions;
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
    @Column(name = "other_informations")
    private String otherInformations;
    private String assumptions;
    @Column(name = "activity_diagram")
    private String activityDiagram;
    @Column(name = "sequence_diagram")
    private String sequenceDiagram;
    @ManyToOne
    @JoinColumn(name = "domain_id")
    private Domain domain;
}