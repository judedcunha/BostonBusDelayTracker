package com.jude.bostonbusdelay.controller;
import com.jude.bostonbusdelay.service.*;
import com.jude.bostonbusdelay.model.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/delays")
public class DelayController {

    private final MBTAService mbtaService;

    public DelayController(MBTAService mbtaService) {
        this.mbtaService = mbtaService;
    }

    @GetMapping
    public List<DelayDTO> getCurrentDelays() {
        return mbtaService.getBusDelays();
    }
}