package com.bank.approve.infrastructure.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.approve.domain.approves.ApproveCards;
import com.bank.approve.domain.approves.repository.ApproveCardsRepository;
import com.bank.approve.usecase.approve.ApproveCardsService;

@Service
public class ApproveCardsServiceImpl implements ApproveCardsService {
    @Autowired
    private ApproveCardsRepository approveRepository;

    @Override
    public ApproveCards createApprove(ApproveCards approve) {
        return this.approveRepository.save(approve);
    }

    @Override
    public void insertMany(Iterable<ApproveCards> approve) {
        this.approveRepository.saveAll(approve);
    }

    @Override
    public void deleteById(Long id) {
        this.approveRepository.deleteById(id);
    }

    @Override
    public ApproveCards getApproveById(Long id) {
        Optional<ApproveCards> borrowing = this.approveRepository.findById(id);
        return borrowing.isPresent() ? borrowing.get() : null;
    }

    @Override
    public List<ApproveCards> getAll() {
        return this.approveRepository.findAll();
    }

    @Override
    public ApproveCards updateApprove(ApproveCards approve) {
        return approveRepository.save(approve);
    }
}
