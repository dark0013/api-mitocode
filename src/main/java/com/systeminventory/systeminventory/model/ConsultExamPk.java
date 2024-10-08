package com.systeminventory.systeminventory.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class ConsultExamPk implements Serializable {
    //private static final long serialVersionUID = 1L;//-> es opcional en el serializable

    @ManyToOne
    @JoinColumn(name = "id_Consult")
    private Consult consult;

    @ManyToOne
    @JoinColumn(name = "id_exam")
    private Exam exam;
}
