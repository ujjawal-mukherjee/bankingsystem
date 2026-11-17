package com.banking.bank.service;
import com.banking.bank.entity.Benificiary;
import com.banking.bank.entity.User;
import com.banking.bank.repository.BenificiaryRepository;
import com.banking.bank.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BenificiaryService {
    private final BenificiaryRepository beneficiaryRepository;
    private final UserRepository userRepository;
    public BenificiaryService(BenificiaryRepository benificiaryRepository,UserRepository userRepository){
        this.beneficiaryRepository=benificiaryRepository;
        this.userRepository=userRepository;
    }
    public Benificiary add(Benificiary benificiary,Long userId){
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        benificiary.setUser(user);
        return beneficiaryRepository.save(
                benificiary
        );
    }
    public List<Benificiary>getAll(Long userId){
        return beneficiaryRepository.findByUser_Id(userId);
    }
    public void delete(Long id){
        if(!beneficiaryRepository.existsById(id)){
            throw new RuntimeException("Benificiary not found");
        }
        beneficiaryRepository.deleteById(id);
    }
}
