package com.lendico.loan.model;



import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RepaymentRequest {

    @NotNull(message = "Please enter duration ")
    @Min(value = 1 ,message = "Minimum range for duration must be 1")
    private Integer duration;
    @NotNull(message = "Please enter nominal rate ")
    private Double nominalRate;
    @NotNull(message = "Please enter loan amount ")
    @Min(value = 1 ,message = "Minimum range for loan amount must be 1")
    private double loanAmount;
    @NotEmpty(message = "Please enter startDate ")
    private String startDate;


    public RepaymentRequest() {

    }

    public RepaymentRequest(int duration, double nominalRate, double loanAmount, String startDate) {
        super();
        this.duration = duration;
        this.nominalRate = nominalRate;
        this.loanAmount = loanAmount;
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getNominalRate() {
        return nominalRate;
    }

    public void setNominalRate(double nominalRate) {
        this.nominalRate = nominalRate;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

}
