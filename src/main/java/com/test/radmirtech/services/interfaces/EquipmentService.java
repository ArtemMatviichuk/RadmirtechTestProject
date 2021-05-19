package com.test.radmirtech.services.interfaces;

import com.test.radmirtech.dto.equipment.EquipmentDto;

import java.util.List;

public interface EquipmentService {
    EquipmentDto getEquipment(long id);
    EquipmentDto getEquipment(String serialNumber);
    List<EquipmentDto> getEquipment();

    List<EquipmentDto> getCrashed();

    List<EquipmentDto> getCrashedByTime(int hour);

    List<EquipmentDto> getCrashedByVolume(int volume);

    List<EquipmentDto> getEquipmentByType(String type);
}
