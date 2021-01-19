package com.lendico.loan.controller;

import com.lendico.loan.model.RepaymentRequest;
import com.lendico.loan.model.RepaymentResponse;

import com.lendico.loan.service.RepaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
public class RepaymentController {

    @Autowired
    RepaymentService repaymentService;

    @RequestMapping(value="/generatePlan", method = RequestMethod.POST)
    public ResponseEntity<Object>  calculate(@RequestBody @Valid RepaymentRequest repaymentRequest) {
        List<RepaymentResponse> repaymentResponse=new ArrayList<>();
        repaymentResponse=repaymentService.calculate(repaymentRequest);
        return ResponseEntity.accepted().body(repaymentResponse);
    }
}
