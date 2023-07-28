package com.bookingcar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A CarAttribute.
 */
@Entity
@Table(name = "car_attribute")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CarAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "attribute_value", nullable = false)
    private String attributeValue;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @NotNull
    @JsonIgnoreProperties(value = { "carAttributes", "groupattribute" }, allowSetters = true)
    private Attribute attribute;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "carattributes", "images", "showroom", "customer", "employee", "carmodel" }, allowSetters = true)
    private Car car;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CarAttribute id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttributeValue() {
        return this.attributeValue;
    }

    public CarAttribute attributeValue(String attributeValue) {
        this.setAttributeValue(attributeValue);
        return this;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public Attribute getAttribute() {
        return this.attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public CarAttribute attribute(Attribute attribute) {
        this.setAttribute(attribute);
        return this;
    }

    public Car getCar() {
        return this.car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public CarAttribute car(Car car) {
        this.setCar(car);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CarAttribute)) {
            return false;
        }
        return id != null && id.equals(((CarAttribute) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CarAttribute{" +
            "id=" + getId() +
            ", attributeValue='" + getAttributeValue() + "'" +
            "}";
    }
}
