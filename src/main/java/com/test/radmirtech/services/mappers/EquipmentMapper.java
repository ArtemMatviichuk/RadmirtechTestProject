package com.test.radmirtech.services.mappers;

import com.test.radmirtech.dao.domain.Equipment;
import com.test.radmirtech.dto.equipment.EquipmentDto;

public class EquipmentMapper {
    public static EquipmentDto toDto(Equipment equipment){
        EquipmentDto dto = new EquipmentDto();

        dto.setId(equipment.getId());
        dto.setSerialNumber(equipment.getSerialNumber());
        dto.setEquipmentType(equipment.getEquipmentType());
        dto.setManufacturerId(equipment.getManufacturer().getId());
        dto.setManufacturerName(equipment.getManufacturer().getName());

        dto.setLastContactDate(equipment.getDetails().getLastContactDate());
        dto.setCurrentVolume(equipment.getDetails().getCurrentVolume());
        dto.setHasError(equipment.getDetails().hasError());

        return dto;
    }
}
