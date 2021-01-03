package com.winery.model;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class Model {

    private String lotCode;

    private String volume;

    private String description;

    private String tankCode;

    private String productState;

    private String ownerName;

    private List<Details> components;

    public String getLotCode() {
        return lotCode;
    }

    public void setLotCode(String lotCode) {
        this.lotCode = lotCode;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTankCode() {
        return tankCode;
    }

    public void setTankCode(String tankCode) {
        this.tankCode = tankCode;
    }

    public String getProductState() {
        return productState;
    }

    public void setProductState(String productState) {
        this.productState = productState;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public List<Details> getComponents() {
        return components;
    }

    public void setComponents(List<Details> components) {
        this.components = components;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model = (Model) o;
        return Objects.equals(lotCode, model.lotCode) &&
                Objects.equals(volume, model.volume) &&
                Objects.equals(description, model.description) &&
                Objects.equals(tankCode, model.tankCode) &&
                Objects.equals(productState, model.productState) &&
                Objects.equals(ownerName, model.ownerName) &&
                Objects.equals(components, model.components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotCode, volume, description, tankCode, productState, ownerName, components);
    }

    @Override
    public String toString() {
        return "Model{" +
                "lotCode='" + lotCode + '\'' +
                ", volume='" + volume + '\'' +
                ", description='" + description + '\'' +
                ", tankCode='" + tankCode + '\'' +
                ", productState='" + productState + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", components=" + components +
                '}';
    }
}
