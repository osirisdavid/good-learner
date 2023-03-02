package tech.nocountry.goodlearnerbackend.feat_load_grades.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.nocountry.goodlearnerbackend.feat_load_grades.model.request.QualifyStudentRequest;
import tech.nocountry.goodlearnerbackend.feat_load_grades.model.response.LoadQualificationDTO;
import tech.nocountry.goodlearnerbackend.feat_load_grades.service.ILoadQualificationService;
import tech.nocountry.goodlearnerbackend.feat_load_grades.service.LoadQualificationServiceImpl;

import java.util.Optional;

@RestController
@RequestMapping("api/teacher")
public class QualifyStudentController {

    @Autowired
    private ILoadQualificationService loadQualificationService;

    @GetMapping("/qualification/{idPerson}")
    public ResponseEntity<?> getQualificationsById(@PathVariable Long idPerson) {
        Optional<LoadQualificationDTO> loadQualificationDTO = loadQualificationService.getQualificationsById(idPerson);
        return loadQualificationDTO.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().header("error", "Could not found id " + idPerson).build());
    }

    @PostMapping("/qualification")
    public ResponseEntity<?> createQualifyStudent(@Validated @RequestBody QualifyStudentRequest qualifyStudent, BindingResult validations){
        if(validations.hasErrors()){
            return new ResponseEntity<>("Los datos ID del estudiante, periodo, ID de la asignatura-comisión y nota numerica son obligatorios", HttpStatus.BAD_REQUEST);
        }
        try{
            return loadQualificationService.createQualifyStudent(qualifyStudent);

        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
