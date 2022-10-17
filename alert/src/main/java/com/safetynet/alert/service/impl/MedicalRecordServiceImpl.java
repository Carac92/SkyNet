package com.safetynet.alert.service.impl;

import com.safetynet.alert.model.Data;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {


    @Autowired
    private Data data;


    @Override
    public boolean updateMedicalRecord(MedicalRecord medicalRecordToUpdate) {
        List<MedicalRecord> medicalRecords = data.getMedicalrecords();
        int i= 0;
        for (MedicalRecord medicalRecord : medicalRecords) {
           i++;
           if((medicalRecord.getFirstName().equals(medicalRecordToUpdate.getFirstName()))
                   &&(medicalRecord.getLastName().equals(medicalRecordToUpdate.getLastName()))) {
               medicalRecords.set(i, medicalRecordToUpdate);
               data.setMedicalrecords(medicalRecords);
               return true;
           }
        }
        return false;
    }

    @Override
    public boolean addMedicalRecord(MedicalRecord medicalRecord) {
        List<MedicalRecord> medicalRecords = data.getMedicalrecords();
        if(!medicalRecords.contains(medicalRecord)){
            medicalRecords.add(medicalRecord);
            data.setMedicalrecords(medicalRecords);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeMedicalRecord(MedicalRecord medicalRecordToDelete) {
        List<MedicalRecord> medicalRecords = data.getMedicalrecords();
        int i = 0;
        for(MedicalRecord medicalRecord : medicalRecords){
            i++;
            if((medicalRecord.getFirstName().equals(medicalRecordToDelete.getFirstName()))
                    && (medicalRecord.getLastName().equals(medicalRecordToDelete.getLastName()))){
                medicalRecords.remove(i);
                data.setMedicalrecords(medicalRecords);
                return true;
            }
        }
        return false;
    }
}
