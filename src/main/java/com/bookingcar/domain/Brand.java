package com.bookingcar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * The Employee entity.
 */
@Schema(description = "The Employee entity.")
@Entity
@Table(name = "brand")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Brand implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    /**
     * The firstname attribute.
     */
    @Schema(description = "The firstname attribute.", required = true)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "logo_url", nullable = false)
    private String logoUrl;

    @OneToMany(mappedBy = "brand",fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"brand"}, allowSetters = true)
    private Set<CarModel> carmodels = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Brand id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Brand name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoUrl() {
        return this.logoUrl;
    }

    public Brand logoUrl(String logoUrl) {
        this.setLogoUrl(logoUrl);
        return this;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Set<CarModel> getCarmodels() {
        return this.carmodels;
    }

    public void setCarmodels(Set<CarModel> carModels) {
        if (this.carmodels != null) {
            this.carmodels.forEach(i -> i.setBrand(null));
        }
        if (carModels != null) {
            carModels.forEach(i -> i.setBrand(this));
        }
        this.carmodels = carModels;
    }

    public Brand carmodels(Set<CarModel> carModels) {
        this.setCarmodels(carModels);
        return this;
    }

    public Brand addCarmodel(CarModel carModel) {
        this.carmodels.add(carModel);
        carModel.setBrand(this);
        return this;
    }

    public Brand removeCarmodel(CarModel carModel) {
        this.carmodels.remove(carModel);
        carModel.setBrand(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Brand)) {
            return false;
        }
        return id != null && id.equals(((Brand) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Brand{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", logoUrl='" + getLogoUrl() + "'" +
            "}";
    }
}
