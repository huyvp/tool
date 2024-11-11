package com.tool.service.tab;

import com.tool.dto.TabReq;
import com.tool.entity.Tab;
import com.tool.repo.TabRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TabServiceImpl implements TabService {
    TabRepo tabRepo;
    TabMapper tabMapper;

    @Override
    public Tab createTab(TabReq tabReq) {
        if (tabRepo.findByName(tabReq.getName()).isPresent())
            throw new RuntimeException("Tab existed");
        Tab tab = tabMapper.toTabFromTabReq(tabReq);
        return tabRepo.save(tab);
    }

    @Override
    public List<Tab> getTabs() {
        return tabRepo.findAll(
                Sort.by(Sort.Order.asc("index"))
        );
    }

    @Override
    public Tab updateTab(TabReq tabReq, String id) {
        Tab tab = tabRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Tab not found"));
        tabMapper.updateTab(tab, tabReq);
        return tabRepo.save(tab);
    }

    @Override
    public void deleteTab(String id) {
        tabRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Tab not found"));
        tabRepo.deleteById(id);
    }
}
