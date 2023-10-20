package com.bank.approve.infrastructure.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.approve.domain.approves.ApproveOfficial;
import com.bank.approve.domain.approves.repository.ApproveOfficialRepository;
import com.bank.approve.usecase.approve.ApproveOfficialService;

@Service
public class ApproveOfficialServiceImpl implements ApproveOfficialService {
    @Autowired
    private ApproveOfficialRepository approveRepository;
    
    
    @Override
    public ApproveOfficial createApprove(ApproveOfficial approve) {
        return this.approveRepository.save(approve);
    }

    @Override
    public void insertMany(Iterable<ApproveOfficial> approve) {
        this.approveRepository.saveAll(approve);
    }

    @Override
    public void deleteById(Long id) {
        this.approveRepository.deleteById(id);
    }

    @Override
    public ApproveOfficial getApproveById(Long id) {
        Optional<ApproveOfficial> borrowing = this.approveRepository.findById(id);
        return borrowing.isPresent() ? borrowing.get() : null;
    }

    @Override
    public List<ApproveOfficial> getAll() {
        return this.approveRepository.findAll();
    }

    @Override
    public ApproveOfficial updateApprove(ApproveOfficial approve) {
        return approveRepository.save(approve);
    }
}
