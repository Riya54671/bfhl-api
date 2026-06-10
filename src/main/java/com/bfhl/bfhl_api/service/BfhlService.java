package com.bfhl.bfhl_api.service;

import com.bfhl.bfhl_api.dto.BfhlRequest;
import com.bfhl.bfhl_api.dto.BfhlResponse;

public interface BfhlService {
    BfhlResponse process(BfhlRequest request);
}
