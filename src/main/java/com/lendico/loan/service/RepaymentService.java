package com.lendico.loan.service;

import com.lendico.loan.model.RepaymentRequest;
import com.lendico.loan.model.RepaymentResponse;
import com.lendico.loan.utils.DateUtil;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;


@Service
public class RepaymentService {

    private static DecimalFormat df = new DecimalFormat("#.##");

    public List<RepaymentResponse> calculate(RepaymentRequest repaymentRequest) {
        List<RepaymentResponse> response = new ArrayList<>();
        double lastOutstandingPrincipal = Double.parseDouble(df.format(repaymentRequest.getLoanAmount()));
        for (int months = 0; months < repaymentRequest.getDuration(); months++) {
            RepaymentResponse repaymentResponse = new RepaymentResponse();
            repaymentResponse = calculateInstalment(repaymentRequest, lastOutstandingPrincipal, months);
            lastOutstandingPrincipal = repaymentResponse.getRemainingOutstandingPrincipal();
            response.add(repaymentResponse);
        }
        return response;
    }

    private RepaymentResponse calculateInstalment(RepaymentRequest repaymentRequest, double lastOutstandingPrincipal, int months) {
        double annuity = calculateAnnuity(repaymentRequest.getNominalRate(), repaymentRequest.getLoanAmount(), repaymentRequest.getDuration());
        double interest = calculateInterest(repaymentRequest.getNominalRate(), lastOutstandingPrincipal);
        double principal = calculatePrincipal(annuity, interest, lastOutstandingPrincipal);
        double initialOutstandingPrincipal = Double.parseDouble(df.format(lastOutstandingPrincipal));
        String date = DateUtil.addMonthsToDate(repaymentRequest.getStartDate(), months);
        double remainingOutstandingPrincipal = Double.parseDouble(df.format(lastOutstandingPrincipal - principal));
        RepaymentResponse repaymentResponse=new RepaymentResponse(annuity,date,initialOutstandingPrincipal,interest,principal,remainingOutstandingPrincipal);
        return repaymentResponse;
    }

    private double calculatePrincipal(double annuity, double interest, double initialOutstandingPrincipal) {
        // Principal = Annuity - Interest
        final double principal = calculatePrincipal(annuity, interest);
        return interest > initialOutstandingPrincipal ? initialOutstandingPrincipal : principal;
    }

    private double calculatePrincipal(double annuity, double interest) {
       return Double.parseDouble(df.format(annuity-interest));
    }

    private double calculateInterest(double nominalRate,double initialOutstandingPrincipal) {
        //  Interest = (Nominal-Rate * Days in Month * Initial Outstanding Principal) / days in year
        return Double.parseDouble(df.format((nominalRate * 30 * initialOutstandingPrincipal / 360)/100));
    }

    private double calculateAnnuity(double nominalRate, double presentValue, int numberOfPeriods) {
        final double ratePerPeriod = nominalRate / (100 * 12);
        //Borrower Payment Amount (Annuity) = Principal + Interest
        return Double.parseDouble(df.format((presentValue * ratePerPeriod ) / (1 - Math.pow((1+ratePerPeriod), -numberOfPeriods))));
    }
}
