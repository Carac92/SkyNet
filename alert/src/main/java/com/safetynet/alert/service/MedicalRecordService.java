package com.safetynet.alert.service;

import com.safetynet.alert.model.MedicalRecord;

/**
 * @author Quentin_Caracatzanis
 * Interface of the Medical Record Service.
 * Generate a Post / Put / Delete Methods.
 */
public interface MedicalRecordService {

    boolean updateMedicalRecord(String firstName,String lastName,MedicalRecord medicalRecordToUpdate);
    boolean addMedicalRecord(MedicalRecord medicalRecord);
    boolean removeMedicalRecord(String firstName, String lastName);
}
