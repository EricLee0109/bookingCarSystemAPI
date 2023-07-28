package com.bookingcar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A GroupAttribute.
 */
@Entity
@Table(name = "group_attribute")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class GroupAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "group_attribute_name", nullable = false)
    private String groupAttributeName;

    @NotNull
    @Column(name = "display_index", nullable = false)
    private Integer displayIndex;

    @OneToMany(mappedBy = "groupattribute")
    @JsonIgnoreProperties(value = { "carAttributes", "groupattribute" }, allowSetters = true)
    private Set<Attribute> groupattributes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public GroupAttribute id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupAttributeName() {
        return this.groupAttributeName;
    }

    public GroupAttribute groupAttributeName(String groupAttributeName) {
        this.setGroupAttributeName(groupAttributeName);
        return this;
    }

    public void setGroupAttributeName(String groupAttributeName) {
        this.groupAttributeName = groupAttributeName;
    }

    public Integer getDisplayIndex() {
        return this.displayIndex;
    }

    public GroupAttribute displayIndex(Integer displayIndex) {
        this.setDisplayIndex(displayIndex);
        return this;
    }

    public void setDisplayIndex(Integer displayIndex) {
        this.displayIndex = displayIndex;
    }

    public Set<Attribute> getGroupattributes() {
        return this.groupattributes;
    }

    public void setGroupattributes(Set<Attribute> attributes) {
        if (this.groupattributes != null) {
            this.groupattributes.forEach(i -> i.setGroupattribute(null));
        }
        if (attributes != null) {
            attributes.forEach(i -> i.setGroupattribute(this));
        }
        this.groupattributes = attributes;
    }

    public GroupAttribute groupattributes(Set<Attribute> attributes) {
        this.setGroupattributes(attributes);
        return this;
    }

    public GroupAttribute addGroupattribute(Attribute attribute) {
        this.groupattributes.add(attribute);
        attribute.setGroupattribute(this);
        return this;
    }

    public GroupAttribute removeGroupattribute(Attribute attribute) {
        this.groupattributes.remove(attribute);
        attribute.setGroupattribute(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GroupAttribute)) {
            return false;
        }
        return id != null && id.equals(((GroupAttribute) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GroupAttribute{" +
            "id=" + getId() +
            ", groupAttributeName='" + getGroupAttributeName() + "'" +
            ", displayIndex=" + getDisplayIndex() +
            "}";
    }
}
