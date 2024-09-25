package com.tool.password;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PasswordServiceImpl implements PasswordService {
    private final PasswordRepo passwordRepo;

    protected enum Users {
        HUY("nv.huy1", "107.120.121.97"),
        HIEUS("tq.hieu", "107.120.121.24"),
        THUAN("dth.thuan1", "107.120.121.23"),
        TRANG("pth.trang1", "107.120.121.34"),
        NGOC("ptb.ngoc", "107.120.121.87"),
        DAT("nv.dat2", "107.120.121.30"),
        THAN("cv.than", "107.120.121.32"),
        KHANH("htm.khanh", "107.120.121.35"),
        PHU("nh.phu", "107.120.121.29"),
        PHUC("vv.phuc", "107.120.121.88"),
        DUC("dm.duc", "107.120.121.31"),
        NHUNG("lt.nhung1", "107.120.121.25");
        @Getter
        private String knox;
        @Getter
        private String ip;

        Users(String knox, String ip) {
            this.knox = knox;
            this.ip = ip;
        }
    }

    @Autowired
    public PasswordServiceImpl(PasswordRepo passwordRepo) {
        this.passwordRepo = passwordRepo;
    }

    @Override
    public List<PasswordDto> getPassByEnvironment(String environment) {
        List<PasswordDto> result = new ArrayList<>();
        List<Password> passwords = passwordRepo.getPassByEnvironment(environment);
        for (Password item : passwords) {
            String user = new SimpleDateFormat("HH:mm dd-MM-yyyy").format(item.getUpdatedAt());
            PasswordDto passwordDto = PasswordDto.builder()
                    .id(item.getId())
                    .environment(item.getEnvironment())
                    .type(item.getType())
                    .username(item.getUsername())
                    .password(item.getPassword())
                    .updatedBy(item.getUpdatedBy())
                    .UpdatedAt(user)
                    .build();
            result.add(passwordDto);
        }
        return result;
    }

    @Override
    public List<Password> getAllPassword() {
        return passwordRepo.getALlPassword();
    }

    @Override
    public void insertPassword(Password password, String remoteAddr) {
        password.setUpdatedBy(getIdFromIp(remoteAddr));
        password.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        passwordRepo.insertPassword(password);
    }

    @Override
    public void updatePassword(Password password, String remoteAddr) {
        password.setUpdatedBy(getIdFromIp(remoteAddr));
        password.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        passwordRepo.updatePassword(password);
    }

    @Override
    public void deletePassword(Password password) {
        passwordRepo.deletePassword(password);
    }
    public static String getIdFromIp(String ip){
        for (Users user: Users.values()){
            if (user.getIp().equals(ip)){
                return user.getKnox();
            }
        }
        return "";
    }
}
