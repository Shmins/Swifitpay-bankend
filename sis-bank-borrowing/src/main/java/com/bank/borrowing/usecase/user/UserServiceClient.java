package com.bank.borrowing.usecase.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.bank.borrowing.domain.entitys.administrator.model.Administrator;
import com.bank.borrowing.domain.entitys.boss.model.Boss;
import com.bank.borrowing.domain.entitys.client.model.Client;
import com.bank.borrowing.domain.entitys.official.model.Official;

@FeignClient("sis-bank-users")
public interface UserServiceClient {
        @GetMapping(value = "/client/v1/{cpf}")
        Client getClient(@PathVariable("cpf") String username,
                        @RequestHeader(value = "Authorization", required = true) String authorizationHeader);

        @GetMapping(value = "/official/v1/{cpf}")
        Official getOfficial(@PathVariable("cpf") String username,
                        @RequestHeader(value = "Authorization", required = true) String authorizationHeader);

        @GetMapping(value = "/adm/v1/{cpf}")
        Administrator getAdmintrator(@PathVariable("cpf") String username,
                        @RequestHeader(value = "Authorization", required = true) String authorizationHeader);

        @GetMapping(value = "/boss/v1/{cpf}")
        Boss getBoss(@PathVariable("cpf") String username,
                        @RequestHeader(value = "Authorization", required = true) String authorizationHeader);

}