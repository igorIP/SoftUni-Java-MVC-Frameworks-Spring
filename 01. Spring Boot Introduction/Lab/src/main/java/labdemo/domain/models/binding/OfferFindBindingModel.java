package labdemo.domain.models.binding;

import labdemo.domain.enities.Offer;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class OfferFindBindingModel implements Bindable<Offer> {

    @NotNull
    @Positive
    @DecimalMax("9999999.99")
    @Digits(integer = 9, fraction = 2)
    private BigDecimal familyBudget;


    @NotBlank
    @Size(min = 1, max = 255)
    private String apartmentType;

    @NotBlank
    @Size(min = 1, max = 255)
    private String familyName;

    public OfferFindBindingModel() {
    }

    public BigDecimal getFamilyBudget() {
        return familyBudget;
    }

    public void setFamilyBudget(BigDecimal familyBudget) {
        this.familyBudget = familyBudget;
    }

    public String getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}
