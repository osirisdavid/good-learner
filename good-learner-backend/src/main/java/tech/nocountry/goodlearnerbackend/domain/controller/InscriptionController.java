package tech.nocountry.goodlearnerbackend.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.nocountry.goodlearnerbackend.domain.model.request.InscriptionRequest;
import tech.nocountry.goodlearnerbackend.domain.service.IInscriptionService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/admin")
public class InscriptionController {

    @Autowired
    private IInscriptionService iInscriptionService;

    @PostMapping("/inscription")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<?> createdInscription(@Validated @RequestBody InscriptionRequest inscriptionRequest, BindingResult validations){
        if(validations.hasErrors()){
            return new ResponseEntity<>(
                    "Lo datos fecha de inscripción, ID de la comisión y ID del estudiante son Obligatorios.",
                    HttpStatus.BAD_REQUEST);
        }
        try{
            return iInscriptionService.createdInscriptionFromStudent(inscriptionRequest);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/inscription/{idInscription}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<?> findInscription(@PathVariable Long idInscription){
        try{
            return iInscriptionService.findInscriptionById(idInscription);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/inscription/{idInscription}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<?> deleteInscription(@PathVariable Long idInscription){
        try{
            return iInscriptionService.deleteInscriptionById(idInscription);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
