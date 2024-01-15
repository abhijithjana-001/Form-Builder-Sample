package com.example.Form.Builder.controller;

import com.example.Form.Builder.config.FormMapStruct;
import com.example.Form.Builder.dto.request.FormDto;
import com.example.Form.Builder.dto.response.ResponseDto;
import com.example.Form.Builder.entities.entity.Form;
import com.example.Form.Builder.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/form")
public class FormController {
    @Autowired
    private FormMapStruct formMapStruct;

    @Autowired
    private FormService formService;

    @PostMapping("/create")
  public ResponseEntity<ResponseDto<List<Object>>> createForm(@RequestBody FormDto formDto){
        Form form=formMapStruct.toEntity(formDto);
        ResponseDto<List<Object>> formResponseDto = formService.saveOrUpdateForm(form,null);
        return ResponseEntity.ok(formResponseDto);
    }
    @PutMapping("/update/{title}")
    public ResponseEntity<ResponseDto<List<Object>>> updateForm(@RequestBody FormDto formDto,@PathVariable String title){
        Form form=formMapStruct.toEntity(formDto);
        ResponseDto<List<Object>> formResponseDto = formService.saveOrUpdateForm(form,title);
        return ResponseEntity.ok(formResponseDto);
    }


    @GetMapping("/{title}")
    public ResponseEntity<ResponseDto<Form>> getFormByTitle(@PathVariable String title)
    {
        ResponseDto<Form> formByTitle = formService.getFormByTitle(title);

        return ResponseEntity.ok(formByTitle);
    }

    @DeleteMapping("/delete/{title}")
    public ResponseEntity<ResponseDto<Form>> deleteFormByTitle(@PathVariable String title)
    {
        ResponseDto<Form> formByTitle = formService.deleteFormByTitle(title);

        return ResponseEntity.ok(formByTitle);
    }


}
