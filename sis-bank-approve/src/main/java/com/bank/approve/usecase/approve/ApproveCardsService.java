package com.bank.approve.usecase.approve;

import java.util.List;


import com.bank.approve.domain.approves.ApproveCards;

public interface ApproveCardsService {
    ApproveCards createApprove(ApproveCards approve);
    void insertMany(Iterable<ApproveCards> approve);
    void deleteById(Long cpf);
    ApproveCards getApproveById(Long cpf);
    List<ApproveCards> getAll();
    ApproveCards updateApprove(ApproveCards approve);
}