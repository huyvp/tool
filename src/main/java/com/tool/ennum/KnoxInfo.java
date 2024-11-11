package com.tool.ennum;

import lombok.Getter;

public enum KnoxInfo {
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
    private String knoxId;
    @Getter
    private String ip;

    KnoxInfo(String knoxId, String ip) {
        this.knoxId = knoxId;
        this.ip = ip;
    }
}