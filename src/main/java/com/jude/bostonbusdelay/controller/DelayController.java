package com.jude.bostonbusdelay.controller;
import com.jude.bostonbusdelay.service.*;

import org.springframework.ui.Model; 

import com.jude.bostonbusdelay.model.*;
import com.jude.bostonbusdelay.repository.SearchHistoryRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
@RequestMapping("/")
public class DelayController {
    private final MBTAService mbtaService;
    private final SearchHistoryRepository historyRepository;

    public DelayController(MBTAService mbtaService, SearchHistoryRepository historyRepository) {
        this.mbtaService = mbtaService;
        this.historyRepository = historyRepository;
    }

    @GetMapping
    public String showHome(Model model) {
        model.addAttribute("recentSearches", historyRepository.findTop10ByOrderBySearchTimeDesc());
        return "index";
    }

    @GetMapping("/delays")
    public String getDelays(
        @RequestParam(required = false) String route,
        Model model
    ) {
        try {
            // Fetch delays for all routes if no route is specified
            List<DelayDTO> delays = mbtaService.getBusDelays(route);
            model.addAttribute("delays", delays);
            model.addAttribute("selectedRoute", route != null ? route : "all");
            return "delays";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to retrieve delay information");
            return "error";
        }
    }
}