package com.bank.borrowing.usecase.approve;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.bank.borrowing.usecase.borrowing.BorrowingTdo;

@FeignClient("sis-bank-approve")
public interface ApproveService {
    @PostMapping(value = "/approve/v1/borrowing/")
    BorrowingTdo sendToApprove(BorrowingTdo data, @RequestHeader(value = "Authorization", required = true) String authorizationHeader);
}
