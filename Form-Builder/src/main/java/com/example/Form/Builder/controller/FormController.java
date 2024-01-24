package com.example.Form.Builder.controller;

import com.example.Form.Builder.config.FormMapStruct;
import com.example.Form.Builder.dto.request.FormDto;
import com.example.Form.Builder.dto.response.ResponseDto;
import com.example.Form.Builder.entities.entity.Form;
import com.example.Form.Builder.service.FormService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/form")
public class FormController {
    private final FormMapStruct formMapStruct;
    private final FormService formService;




    public FormController(FormMapStruct formMapStruct, FormService formService) {
        this.formMapStruct = formMapStruct;
        this.formService = formService;

    }





    @PostMapping("/create")
  public ResponseEntity<ResponseDto<Object>> createForm(@RequestBody FormDto formDto){
        Form form=formMapStruct.toEntity(formDto);
        ResponseDto<Object> formResponseDto = formService.saveOrUpdateForm(form,null);
        return ResponseEntity.ok(formResponseDto);
    }
    @PutMapping("/update/{title}")
    public  ResponseEntity<ResponseDto<Object>> updateForm(@RequestBody FormDto formDto,@PathVariable String title){
        Form form=formMapStruct.toEntity(formDto);
        ResponseDto<Object> formResponseDto = formService.saveOrUpdateForm(form,title);
        return ResponseEntity.ok(formResponseDto);
    }


    @GetMapping("/{title}")
    public ResponseEntity<ResponseDto<Object>> getFormByTitle(@PathVariable String title)
    {
        ResponseDto<Object> formByTitle = formService.getFormByTitle(title);

        return ResponseEntity.ok(formByTitle);
    }

    @DeleteMapping("/delete/{title}")
    public ResponseEntity<ResponseDto<Void>> deleteFormByTitle(@PathVariable String title)
    {
        ResponseDto<Void> formByTitle = formService.deleteFormByTitle(title);

        return ResponseEntity.ok(formByTitle);
    }


    @GetMapping("/title/{letter}")
    public ResponseEntity<ResponseDto<List<Form>>> findFormWithTile (@PathVariable String letter)
    {
        ResponseDto<List<Form>> allFormComponents = formService.findByTitleStartingWithLetter(letter);
        return ResponseEntity.ok(allFormComponents);
    }

    @GetMapping("/empty/components")
    public ResponseEntity<ResponseDto<Object>> getEmptyComponent()
    {
        ResponseDto<Object> emptyComponentsForm = formService.getFormsWithEmptyComponents();
        return ResponseEntity.ok(emptyComponentsForm);

    }

}
