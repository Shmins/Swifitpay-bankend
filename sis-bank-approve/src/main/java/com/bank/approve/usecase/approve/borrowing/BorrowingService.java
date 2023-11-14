package com.bank.approve.usecase.approve.borrowing;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("sis-bank-borrowing")
public interface BorrowingService {
    @PutMapping(value = "/borrowing/v1/approve/{id}")
    BorrowingTdo sendApprovedBorrowing(@PathVariable("id") Long id,
            @RequestHeader(value = "Authorization", required = true) String authorizationHeader);

    @PutMapping(value = "/borrowing/v1/refuse/{id}")
    BorrowingTdo sendRefusedBorrowing(@PathVariable("id") Long id,
            @RequestHeader(value = "Authorization", required = true) String authorizationHeader);
}
