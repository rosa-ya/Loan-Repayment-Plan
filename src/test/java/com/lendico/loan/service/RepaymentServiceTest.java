package com.lendico.loan.service;

import com.lendico.loan.model.RepaymentRequest;
import com.lendico.loan.model.RepaymentResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.util.List;

public class RepaymentServiceTest {

    private RepaymentService repaymentServiceTest;

    @Before
    public void setup() {
        repaymentServiceTest = new RepaymentService();
    }

    @Test
    public void testCalculate() {
        RepaymentRequest input = new RepaymentRequest();
        input.setDuration(24);
        input.setStartDate("2018-01-01T00:00:00Z");
        input.setNominalRate(5.0);
        input.setLoanAmount(5000);

        List<RepaymentResponse> outputList = repaymentServiceTest.calculate(input);

        Assert.assertEquals(24, outputList.size());

        Assert.assertEquals("2018-01-01T00:00:00Z", outputList.get(0).getDate());
        Assert.assertEquals("2018-02-01T00:00:00Z", outputList.get(1).getDate());

        Assert.assertEquals(198.53, outputList.get(0).getPrincipal(),0.02);
        Assert.assertEquals(199.36, outputList.get(1).getPrincipal(),0.02);

        Assert.assertEquals(20.83, outputList.get(0).getInterest(),0.02);
        Assert.assertEquals(20, outputList.get(1).getInterest(),0.02);

        Assert.assertEquals(219.36, outputList.get(0).getBorrowerPaymentAmount(),0.02);
        Assert.assertEquals(219.36, outputList.get(1).getBorrowerPaymentAmount(),0.02);

        Assert.assertEquals(5000, outputList.get(0).getInitialOutstandingPrincipal(),0.02);
        Assert.assertEquals(4801.47, outputList.get(1).getInitialOutstandingPrincipal(),0.02);
    }
  }
