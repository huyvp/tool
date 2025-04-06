package com.tool.service.account;

import com.tool.dto.AccountReq;
import com.tool.dto.AccountResp;
import com.tool.dto.AccountUpdate;
import com.tool.entity.Account;
import com.tool.entity.User;
import com.tool.repo.AccountRepo;
import com.tool.repo.UserRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountServiceImpl implements AccountService {
    AccountRepo accountRepo;
    UserRepo userRepo;

    @Override
    public List<AccountResp> getAccountByEnvironment(String environment) {

        List<Account> accounts = accountRepo.findByEnvironmentAndActiveIsTrue(environment)
                .orElseGet(() -> new ArrayList<>(0));

        List<AccountResp> result = new ArrayList<>();

        for (Account item : accounts) {
            AccountResp passwordResp = convertToAccountResponse(item);
            result.add(passwordResp);
        }
        return result;
    }

    @Override
    public List<AccountResp> getAllPassword() {
        return accountRepo.findAll().stream()
                .map(AccountServiceImpl::convertToAccountResponse).toList();
    }

    @Override
    public void insertAccount(AccountReq accountReq, String remoteAddr) {
        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not exist"));

        Account account = convertToAccount(accountReq, user.getEmail());
        accountRepo.save(account);
    }

    @Override
    public void updateAccount(AccountUpdate accountUpdate, String remoteAddr) {
        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not exist"));

        Account account = accountRepo.findById(accountUpdate.getId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setPassword(accountUpdate.getPassword());
        account.setUpdatedBy(user.getEmail());

        accountRepo.save(account);
    }

    @Override
    public void deleteAccount(String id) {
        Account account = accountRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setActive(false);
        accountRepo.save(account);
    }

    private static AccountResp convertToAccountResponse(Account item) {
        String dateUpdate = new SimpleDateFormat("HH:mm dd-MM-yyyy")
                .format(item.getUpdatedAt());
        return AccountResp.builder()
                .id(item.getId())
                .environment(item.getEnvironment())
                .type(item.getType())
                .username(item.getUsername())
                .password(item.getPassword())
                .updatedBy(item.getUpdatedBy())
                .updatedAt(dateUpdate)
                .build();
    }

    private static Account convertToAccount(AccountReq accountReq, String userId) {
        return Account.builder()
                .environment(accountReq.getEnvironment())
                .type(accountReq.getType())
                .username(accountReq.getUsername())
                .password(accountReq.getPassword())
                .updatedBy(userId)
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .active(true)
                .build();
    }
}
