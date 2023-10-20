package com.bank.borrowing.usecase.approve;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.bank.borrowing.usecase.borrowing.BorrowingTdo;

@FeignClient("sis-bank-approve")
public interface ApproveService {
    @PostMapping(value = "/approve/v1/borrowing/")
    void sendToApprove(BorrowingTdo data);
}
