package com.tool.service.account;

import com.tool.dto.AccountResp;
import com.tool.dto.AccountReq;
import com.tool.dto.AccountUpdate;

import java.util.List;

public interface AccountService {
    List<AccountResp> getAccountByEnvironment(String environment);

    List<AccountResp> getAllPassword();

    void insertAccount(AccountReq accountReq, String remoteAddr);

    void updateAccount(AccountUpdate account, String remoteAddr);

    void deleteAccount(String id);
}
