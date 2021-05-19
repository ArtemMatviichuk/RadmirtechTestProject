package com.test.radmirtech.controllers;

import com.test.radmirtech.dto.equipment.EquipmentDto;
import com.test.radmirtech.services.interfaces.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("crash")
public class CrashEquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EquipmentDto> getCrashed(){
        return equipmentService.getCrashed();
    }

    @GetMapping("last-contact/{hour}")
    @ResponseStatus(HttpStatus.OK)
    public List<EquipmentDto> getByTime(@PathVariable int hour){
        return equipmentService.getCrashedByTime(hour);
    }

    @GetMapping("volume/{volume}")
    @ResponseStatus(HttpStatus.OK)
    public List<EquipmentDto> getByVolume(@PathVariable int volume){
        return equipmentService.getCrashedByVolume(volume);
    }
}
