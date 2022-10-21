package com.safetynet.alert.service;

import com.safetynet.alert.model.MedicalRecord;

import java.util.List;

/**
 * @author Quentin_Caracatzanis
 * Interface of the Medical Record Service.
 * Generate a Post / Put / Delete Methods.
 */
public interface MedicalRecordService {

    boolean updateMedicalRecord(MedicalRecord medicalRecordToUpdate);
    boolean addMedicalRecord(MedicalRecord medicalRecord);
    boolean removeMedicalRecord(MedicalRecord medicalRecord);
}
