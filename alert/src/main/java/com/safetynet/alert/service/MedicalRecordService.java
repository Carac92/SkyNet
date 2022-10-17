package com.safetynet.alert.service;

import com.safetynet.alert.model.MedicalRecord;

import java.util.List;

public interface MedicalRecordService {

    boolean updateMedicalRecord(MedicalRecord medicalRecordToUpdate);
    boolean addMedicalRecord(MedicalRecord medicalRecord);
    boolean removeMedicalRecord(MedicalRecord medicalRecord);
}
