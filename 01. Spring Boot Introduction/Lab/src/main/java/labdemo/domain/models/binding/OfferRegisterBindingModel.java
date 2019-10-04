package labdemo.domain.models.binding;

import labdemo.domain.enities.Offer;

import javax.validation.constraints.*;
import java.math.BigDecimal;


public class OfferRegisterBindingModel implements Bindable<Offer> {

    @NotNull
    @Positive
    @DecimalMax("999999.99")
    private BigDecimal apartmentRent;


    @NotBlank
    @Size(min = 1, max = 255)
    private String apartmentType;

    @NotNull
    @PositiveOrZero
    @DecimalMax("100.00")
    private BigDecimal agencyCommission;

    public OfferRegisterBindingModel() {
    }

    public BigDecimal getApartmentRent() {
        return apartmentRent;
    }

    public void setApartmentRent(BigDecimal apartmentRent) {
        this.apartmentRent = apartmentRent;
    }

    public String getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }

    public BigDecimal getAgencyCommission() {
        return agencyCommission;
    }

    public void setAgencyCommission(BigDecimal agencyCommission) {
        this.agencyCommission = agencyCommission;
    }
}
