package com.bfhl.bfhl_api.controller;

import com.bfhl.bfhl_api.dto.BfhlRequest;
import com.bfhl.bfhl_api.dto.BfhlResponse;
import com.bfhl.bfhl_api.service.BfhlService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BfhlController {

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    @PostMapping("/bfhl")
    public ResponseEntity<BfhlResponse> handle(@Valid @RequestBody BfhlRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(bfhlService.process(request));
    }

    // graceful exception handling -> is_success: false
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BfhlResponse> handleError(Exception e) {
        BfhlResponse error = new BfhlResponse();
        error.setSuccess(false);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
