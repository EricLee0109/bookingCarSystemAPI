package com.bookingcar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Attribute.
 */
@Entity
@Table(name = "attribute")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Attribute implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "attribute_name", nullable = false)
    private String attributeName;

    @NotNull
    @Column(name = "display_index", nullable = false)
    private Long displayIndex;

    @OneToMany(mappedBy = "attribute")
    @JsonIgnoreProperties(value = { "attribute", "car" }, allowSetters = true)
    private Set<CarAttribute> carAttributes = new HashSet<>();

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @NotNull
//    @JsonIgnoreProperties(value = { "groupattributes" }, allowSetters = true)
    private GroupAttribute groupattribute;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Attribute id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttributeName() {
        return this.attributeName;
    }

    public Attribute attributeName(String attributeName) {
        this.setAttributeName(attributeName);
        return this;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public Long getDisplayIndex() {
        return this.displayIndex;
    }

    public Attribute displayIndex(Long displayIndex) {
        this.setDisplayIndex(displayIndex);
        return this;
    }

    public void setDisplayIndex(Long displayIndex) {
        this.displayIndex = displayIndex;
    }

    public Set<CarAttribute> getCarAttributes() {
        return this.carAttributes;
    }

    public void setCarAttributes(Set<CarAttribute> carAttributes) {
        if (this.carAttributes != null) {
            this.carAttributes.forEach(i -> i.setAttribute(null));
        }
        if (carAttributes != null) {
            carAttributes.forEach(i -> i.setAttribute(this));
        }
        this.carAttributes = carAttributes;
    }

    public Attribute carAttributes(Set<CarAttribute> carAttributes) {
        this.setCarAttributes(carAttributes);
        return this;
    }

    public Attribute addCarAttribute(CarAttribute carAttribute) {
        this.carAttributes.add(carAttribute);
        carAttribute.setAttribute(this);
        return this;
    }

    public Attribute removeCarAttribute(CarAttribute carAttribute) {
        this.carAttributes.remove(carAttribute);
        carAttribute.setAttribute(null);
        return this;
    }

    public GroupAttribute getGroupattribute() {
        return this.groupattribute;
    }

    public void setGroupattribute(GroupAttribute groupAttribute) {
        this.groupattribute = groupAttribute;
    }

    public Attribute groupattribute(GroupAttribute groupAttribute) {
        this.setGroupattribute(groupAttribute);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Attribute)) {
            return false;
        }
        return id != null && id.equals(((Attribute) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Attribute{" +
            "id=" + getId() +
            ", attributeName='" + getAttributeName() + "'" +
            ", displayIndex=" + getDisplayIndex() +
            "}";
    }
}
