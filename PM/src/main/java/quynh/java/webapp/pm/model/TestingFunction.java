package quynh.java.webapp.pm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "testing_function")
public class TestingFunction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "function_name")
    private String functionName;
    @Column(name = "test_requirement")
    private String testRequirement;
    @Column(name = "function_params")
    private String functionParams;
    @Column(name = "function_return")
    private String functionReturn;
    @Column(name = "function_exceptions")
    private String functionExceptions;
    @Column(name = "function_logs")
    private String functionLogs;
    @Column(name = "test_plan_input")
    private String testPlanInput;
    @Column(name = "test_plan_result")
    private String testPlanResult;
    @ManyToOne
    @JoinColumn(name = "class_spec_id")
    private ClassSpec classSpec;
}