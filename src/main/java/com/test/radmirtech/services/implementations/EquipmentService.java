package com.test.radmirtech.services.implementations;

import com.test.radmirtech.dao.domain.Equipment;
import com.test.radmirtech.dao.repos.EquipmentRepository;
import com.test.radmirtech.dto.equipment.EquipmentDto;
import com.test.radmirtech.exceptions.ItemNotFoundException;
import com.test.radmirtech.services.mappers.EquipmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipmentService implements com.test.radmirtech.services.interfaces.EquipmentService {
    @Autowired
    private EquipmentRepository equipmentRepository;

    @Override
    public EquipmentDto getEquipment(long id) {
        return EquipmentMapper.toDto(
                equipmentRepository.findById(id)
                        .orElseThrow(() -> new ItemNotFoundException("Equipment not found!"))
        );
    }

    @Override
    public EquipmentDto getEquipment(String serialNumber) {
        return EquipmentMapper.toDto(
                equipmentRepository.findBySerialNumber(serialNumber)
                        .orElseThrow(() -> new ItemNotFoundException("Equipment not found!"))
        );
    }

    @Override
    public List<EquipmentDto> getEquipment() {
        return equipmentRepository.findAll()
                .stream()
                .map(EquipmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EquipmentDto> getCrashed() {
        return equipmentRepository.findCrashed()
                .stream()
                .map(EquipmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EquipmentDto> getCrashedByTime(int hour) {
        return equipmentRepository.findByContactDate(hour)
                .stream()
                .map(EquipmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EquipmentDto> getCrashedByVolume(int volume) {
        return equipmentRepository.findByVolume(volume)
                .stream()
                .map(EquipmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EquipmentDto> getEquipmentByType(String type) {
        return equipmentRepository.findByEquipmentType(type)
                .stream()
                .map(EquipmentMapper::toDto)
                .collect(Collectors.toList());
    }
}
