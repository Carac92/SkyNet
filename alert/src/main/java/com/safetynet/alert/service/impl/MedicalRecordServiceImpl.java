package com.safetynet.alert.service.impl;

import com.safetynet.alert.model.Data;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Iterator;
import java.util.List;

/**
 * @author Quentin_Caracatzanis
 * Implementation of Medical Record Service.
 * Contains 3 methods one to add a MR in the list of MR in Data, one to update and the last one to remove.
 */
@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {


    @Autowired
    private Data data;


    @Override
    public boolean updateMedicalRecord(String firstName, String lastName, MedicalRecord medicalRecordToUpdate) {
        List<MedicalRecord> medicalRecords = data.getMedicalrecords();
        int i= 0;
        for (MedicalRecord medicalRecord : medicalRecords) {
           if((medicalRecord.getFirstName().equals(firstName))
                   &&(medicalRecord.getLastName().equals(lastName))) {
               medicalRecords.set(i, medicalRecordToUpdate);
               data.setMedicalrecords(medicalRecords);
               return true;
           }
            i++;
        }
        return false;
    }

    @Override
    public boolean addMedicalRecord(MedicalRecord medicalRecord) {
        List<MedicalRecord> medicalRecords = data.getMedicalrecords();
        Iterator<MedicalRecord> it = medicalRecords.iterator();
        while(it.hasNext()) {
            MedicalRecord mr = it.next();
            if (!mr.getFirstName().equals(medicalRecord.getFirstName()) ||
                    !mr.getLastName().equals(medicalRecord.getLastName())) {
                medicalRecords.add(medicalRecord);
                data.setMedicalrecords(medicalRecords);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeMedicalRecord(String firstName, String lastName) {
        List<MedicalRecord> medicalRecords = data.getMedicalrecords();
        Iterator<MedicalRecord> it = medicalRecords.iterator();
        while(it.hasNext()) {
            MedicalRecord mr = it.next();
            if(mr.getFirstName().equals(firstName)&&
                    mr.getLastName().equals(lastName)) {
                it.remove();
                return true;
            }
        }
        return false;
    }
}
