package com.example.task2_1_1.service;

import com.example.task2_1_1.entity.Address;
import com.example.task2_1_1.entity.Department;
import com.example.task2_1_1.entity.Worker;
import com.example.task2_1_1.payload.ResponseApi;
import com.example.task2_1_1.payload.WorkerDto;
import com.example.task2_1_1.repository.AddressRepository;
import com.example.task2_1_1.repository.DepartmentRepository;
import com.example.task2_1_1.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    WorkerRepository workerRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    public ResponseApi create(WorkerDto workerDto) {
        if (workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber()))
            return new ResponseApi("The phone number is already in use", false);
        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        if (optionalAddress.isEmpty())
            return new ResponseApi("Address is not selected", false);
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (optionalDepartment.isEmpty())
            return new ResponseApi("Department is not selected", false);
        Worker worker = new Worker(null, workerDto.getName(), workerDto.getPhoneNumber(), optionalAddress.get(), optionalDepartment.get());
        workerRepository.save(worker);
        return new ResponseApi("Worker is created", true);
    }

    public List<Worker> getAll() {
        return workerRepository.findAll();
    }

    public Worker getById(Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (optionalWorker.isEmpty())
            return null;
        return optionalWorker.get();
    }

    public ResponseApi update(Integer id, WorkerDto workerDto) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (optionalWorker.isEmpty())
            return new ResponseApi("Worker not found", false);
        if (workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber()))
            return new ResponseApi("The phone number is already in use", false);
        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        if (optionalAddress.isEmpty())
            return new ResponseApi("Address is not selected", false);
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (optionalDepartment.isEmpty())
            return new ResponseApi("Department is not selected", false);
        Worker worker = optionalWorker.get();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(optionalAddress.get());
        worker.setDepartment(optionalDepartment.get());
        workerRepository.save(worker);
        return new ResponseApi("Worker data is updated", true);
    }

    public ResponseApi deleteById(Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (optionalWorker.isEmpty())
            return new ResponseApi("Worker not found", false);
        workerRepository.deleteById(id);
        return new ResponseApi("Worker is deleted", true);
    }
}
