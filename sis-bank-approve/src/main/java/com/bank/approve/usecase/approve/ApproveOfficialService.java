package com.bank.approve.usecase.approve;

import java.util.List;


import com.bank.approve.domain.approves.ApproveOfficial;

public interface ApproveOfficialService {
    ApproveOfficial createApprove(ApproveOfficial approve);
    void insertMany(Iterable<ApproveOfficial> approve);
    void deleteById(Long cpf);
    ApproveOfficial getApproveById(Long cpf);
    List<ApproveOfficial> getAll();
    ApproveOfficial updateApprove(ApproveOfficial approve);
}