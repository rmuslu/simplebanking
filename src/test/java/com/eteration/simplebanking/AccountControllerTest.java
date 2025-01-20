package com.eteration.simplebanking;

import com.eteration.simplebanking.controller.AccountController;
import com.eteration.simplebanking.dto.AccountDTO;
import com.eteration.simplebanking.mapper.Mapper;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @Mock
    private Mapper mapper;

    @InjectMocks
    private AccountController accountController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    void testGetAccountDetails() throws Exception {
        // Mock Account ve DTO
        Account account = new Account("Rumeysa Muslu", "12345");
        account.setBalance(500.0);

        AccountDTO accountDTO = new AccountDTO(account);
        accountDTO.setAccountNumber("12345");
        accountDTO.setOwner("Rumeysa Muslu");
        accountDTO.setBalance(500.0);

        when(accountService.getAccount("12345")).thenReturn(account);
        when(mapper.toDTO(account)).thenReturn(accountDTO);

        mockMvc.perform(get("/api/account/v1/12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountNumber").value("12345"))
                .andExpect(jsonPath("$.owner").value("Rumeysa Muslu"))
                .andExpect(jsonPath("$.balance").value(500.0));

        verify(accountService, times(1)).getAccount("12345");
        verify(mapper, times(1)).toDTO(account);
    }

    @Test
    void testCreateAccount() throws Exception {
        Account account = new Account("Rumeysa Muslu", "12345");
        AccountDTO accountDTO = new AccountDTO(account);
        accountDTO.setAccountNumber("12345");
        accountDTO.setOwner("Rumeysa Muslu");
        accountDTO.setBalance(0.0);

        when(accountService.createAccount(any(Account.class))).thenReturn(account);
        when(mapper.toDTO(account)).thenReturn(accountDTO);

        mockMvc.perform(post("/api/account/v1/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"accountNumber\":\"12345\", \"owner\":\"Rumeysa Muslu\", \"balance\":0.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountNumber").value("12345"))
                .andExpect(jsonPath("$.owner").value("Rumeysa Muslu"))
                .andExpect(jsonPath("$.balance").value(0.0));

        verify(accountService, times(1)).createAccount(any(Account.class));
        verify(mapper, times(1)).toDTO(account);
    }
}
