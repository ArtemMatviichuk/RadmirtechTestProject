package com.test.radmirtech.controllers;

import com.test.radmirtech.dto.equipment.EquipmentDto;
import com.test.radmirtech.services.interfaces.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EquipmentDto> getEquipment(){
        return equipmentService.getEquipment();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public EquipmentDto getEquipment(@PathVariable long id){
        return equipmentService.getEquipment(id);
    }

    @GetMapping("serial/{serialNumber}")
    @ResponseStatus(HttpStatus.OK)
    public EquipmentDto getEquipment(@PathVariable String serialNumber){
        return equipmentService.getEquipment(serialNumber);
    }

    @GetMapping("type/{type}")
    @ResponseStatus(HttpStatus.OK)
    public List<EquipmentDto> getEquipmentByType(@PathVariable String type){
        return equipmentService.getEquipmentByType(type);
    }
}
